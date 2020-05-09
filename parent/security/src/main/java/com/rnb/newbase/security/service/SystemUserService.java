package com.rnb.newbase.security.service;

import com.rnb.newbase.exception.NewbaseExceptionConstants;
import com.rnb.newbase.exception.RnbRuntimeException;
import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.security.config.constant.LoginConstant;
import com.rnb.newbase.security.persistent.dao.SystemUserDao;
import com.rnb.newbase.security.persistent.entity.SystemResource;
import com.rnb.newbase.security.persistent.entity.SystemRole;
import com.rnb.newbase.security.persistent.entity.SystemUser;
import com.rnb.newbase.security.persistent.entity.SystemUserWeixin;
import com.rnb.newbase.service.base.BaseService;
import com.rnb.newbase.toolkit.security.BCryptPasswordEncoder;
import com.rnb.newbase.toolkit.util.ListUtil;
import com.rnb.newbase.toolkit.util.RandomUtil;
import com.rnb.newbase.toolkit.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class SystemUserService extends BaseService<SystemUser> {
    @Resource
    private SystemUserDao systemUserDao;
    @Resource
    private SystemRoleService systemRoleService;
    @Override
    public BaseDao<SystemUser> getBaseDao() {
        return systemUserDao;
    }
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource
    private SystemUserWeixinService systemUserWeixinService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${app.auth.session.duration}")
    private String duration;

    /**
     * 用户登录
     * @param username
     * @param password
     * @param sessionId
     * @return
     */
    public String login(String username, String password, String sessionId) {
        SystemUser condition = new SystemUser();
        condition.setUsername(username);
        SystemUser systemUser = systemUserDao.queryOneByCondition(condition);
        if (systemUser == null) {
            throw new RnbRuntimeException("900001", "user.not.existed.or.password.error");
        }
        if (!systemUser.getEnabled()) {
            throw new RnbRuntimeException("900002", "user.enabled");
        }
        if (systemUser.getSecretExpired()) {
            throw new RnbRuntimeException("900003", "user.password.expired");
        }
        if (systemUser.getSecretLocked()) {
            throw new RnbRuntimeException("900004", "user.password.locked");
        }
        if (!bCryptPasswordEncoder.matches(password, systemUser.getSecret())) {
            throw new RnbRuntimeException("900001", "user.not.existed.or.password.error");
        }
        String loginToken = RandomUtil.generateNoSymbleString(32);
        systemUser.setLastLoginTime(new Date());
        systemUserDao.update(systemUser);
        updateRedis(sessionId, loginToken, systemUser.getId());
        return loginToken;
    }

    /**
     * 用户微信授权登录
     * @param username
     * @param accessToken
     * @param refreshToken
     * @param openId
     * @param sessionId
     * @return
     */
    public String loginByWeixin(String username, String accessToken, String refreshToken, String openId, String sessionId, BigInteger roleId) {
        SystemUser condition = new SystemUser();
        condition.setUsername(username);
        SystemUser systemUser = systemUserDao.queryOneByCondition(condition);
        if (systemUser == null) {
            throw new RnbRuntimeException("900001", "system.user.not.existed");
        }
        if (!systemUser.getEnabled()) {
            throw new RnbRuntimeException("900002", "user.enabled");
        }
        SystemUserWeixin systemUserWeixin = systemUserWeixinService.findByUserId(systemUser.getId());
        if (systemUserWeixin == null) {
            throw new RnbRuntimeException("900003", "weixin.user.not.existed");
        }
        if (!systemUserWeixin.getOpenId().equals(openId)) {
            throw new RnbRuntimeException("900004", "weixin.user.not.match");
        }
        systemUserWeixin.setAccessToken(accessToken);
        systemUserWeixin.setRefreshToken(refreshToken);
        systemUserWeixinService.update(systemUserWeixin);
        String loginToken = RandomUtil.generateNoSymbleString(32);
        systemUser.setLastLoginTime(new Date());
        systemUserDao.update(systemUser);
        updateRedis(sessionId, loginToken, systemUser.getId());
        return loginToken;
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @param  sessionId
     * @return
     */
    public String signUp(String username, String password, String sessionId) {
        SystemUser systemUser = createUser(username, password);
        String loginToken = RandomUtil.generateNoSymbleString(32);
        updateRedis(sessionId, loginToken, systemUser.getId());
        return loginToken;
    }

    /**
     * 新增用户
     * @param username
     * @param password
     * @return
     */
    public SystemUser createUser(String username, String password) {
        SystemUser condition = new SystemUser();
        condition.setUsername(username);
        SystemUser systemUser = systemUserDao.queryOneByCondition(condition);
        if (systemUser != null) {
            throw new RnbRuntimeException(NewbaseExceptionConstants.SECURITY_USER_EXISTED);
        }
        SystemUser newSystemUser = new SystemUser();
        newSystemUser.setUsername(username);
        if (StringUtil.isNotBlank(password)) {
            newSystemUser.setSecret(bCryptPasswordEncoder.encode(password));
        }
        systemUserDao.insert(newSystemUser);
        systemUser = systemUserDao.queryOneByCondition(condition);
        systemUser.setSecret(null);
        return systemUser;
    }

    /**
     * 新增微信登录用户
     * @param username
     * @param accessToken
     * @param refreshToken
     * @return
     */
    public SystemUser createWeixinUser(String username, String accessToken, String refreshToken, String openId) {
        SystemUser systemUser = createUser(username, null);
        // 新增用户微信信息
        SystemUserWeixin systemUserWeixin = new SystemUserWeixin();
        systemUserWeixin.setUserId(systemUser.getId());
        systemUserWeixin.setOpenId(openId);
        systemUserWeixin.setAccessToken(accessToken);
        systemUserWeixin.setRefreshToken(refreshToken);
        systemUserWeixinService.insert(systemUserWeixin);
        return systemUser;
    }

    /**
     * 更新会话，用户id，登录token
     * @param sessionId
     * @param loginToken
     * @param userId
     */
    public void updateRedis(String sessionId, String loginToken, BigInteger userId) {
        // 获取时间周期
        if (StringUtil.isBlank(duration)) {
            duration = "30m";
        }
        Long durationPeriod = new Long(duration.substring(0, duration.length() - 1));
        switch(duration.substring(duration.length() - 1)) {
            case "d":
                durationPeriod = durationPeriod * 24;
            case "h":
                durationPeriod = durationPeriod * 60;
            case "m":
                durationPeriod = durationPeriod * 60;
        }
        stringRedisTemplate.opsForValue().set(LoginConstant.REDIS_SESSION_PREFIX + sessionId, loginToken, durationPeriod, TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set(LoginConstant.REDIS_LOGIN_TOKEN_PREFIX + loginToken, userId.toString(), durationPeriod, TimeUnit.SECONDS);
    }

    /**
     * 根据sessionId 登出系统
     * @param sessionId
     */
    public void logout(String sessionId) {
        String loginToken = stringRedisTemplate.opsForValue().get(LoginConstant.REDIS_SESSION_PREFIX + sessionId);
        if (StringUtil.isNotBlank(loginToken)) {
            stringRedisTemplate.delete(LoginConstant.REDIS_LOGIN_TOKEN_PREFIX + loginToken);
            stringRedisTemplate.delete(LoginConstant.REDIS_SESSION_PREFIX + sessionId);
        }
    }

    /**
     * 根据条件查询用户，并返回带权限信息的用户
     * @param systemUser
     * @return
     */
    public SystemUser findUserWithAuthorization(SystemUser systemUser) {
        List<SystemRole> userRoles = systemRoleService.findUserRolesWithResource(systemUser.getId());
        systemUser.setRoles(userRoles);
        // 取所有roles下的权限并去除重复项
        List<SystemResource> userResources = new ArrayList<>();
        for(SystemRole userRole : userRoles) {
            for(SystemResource roleResource : userRole.getResources())
            if(!userResources.contains(roleResource)) {
                userResources.add(roleResource);
            }
        }
        systemUser.setResources(userResources);
        systemUser.setSecret(null);
        return systemUser;
    }

    /**
     * 根据用户id，返回带权限信息的用户
     * @param id
     * @return
     */
    public SystemUser findUserWithAuthorizationById(BigInteger id) {
        SystemUser systemUser = systemUserDao.queryById(id);
        return findUserWithAuthorization(systemUser);
    }

    /**
     * 更新用户密码、状态及用户对应角色
     * @param updateUser
     * @param rolesId
     * @return
     */
    public SystemUser updateUser(SystemUser updateUser, List<BigInteger> rolesId) {
        // 更新用户
        SystemUser existedUser = systemUserDao.queryById(updateUser.getId());
        if (existedUser == null) {
            throw new RnbRuntimeException(NewbaseExceptionConstants.SECURITY_USER_NOT_EXISTED);
        }
        if (StringUtil.isNotBlank(updateUser.getSecret())) {
            updateUser.setSecret(bCryptPasswordEncoder.encode(updateUser.getSecret()));
        }
        if (updateUser.getEnabled() != null) {
            updateUser.setEnabled(updateUser.getEnabled());
        }
        systemUserDao.update(existedUser);
        // 更新用户对应角色
        updateUserRole(existedUser.getId(), rolesId);
        return findUserWithAuthorizationById(existedUser.getId());
    }

    /**
     * 更新用户角色
     * @param userId
     * @param rolesId
     */
    public void updateUserRole(BigInteger userId, List<BigInteger> rolesId) {
        //删除用户对应所有角色
        systemUserDao.deleteUserRole(userId);
        if (ListUtil.isNotEmpty(rolesId)) {
            //重新设置用户对应角色
            for(BigInteger roleId : rolesId) {
                // 检查对应的权限是否存在
                if (systemRoleService.checkRoleExisted(roleId)) {
                    systemUserDao.insertUserRole(userId, roleId);
                } else {
                    logger.error("Update user role failed ! roleId[{}] not existed!", roleId);
                    throw new RnbRuntimeException(NewbaseExceptionConstants.SECURITY_RESOURCE_NOT_EXISTED);
                }
            }
        }
    }

    /**
     * 分页查询用户列表
     * @param pageNum
     * @param pageSize
     * @param condition
     * @return
     */
    public List<SystemUser> queryUsers(int pageNum, int pageSize, Map<String, Object> condition) {
        List<SystemUser> systemUsers = systemUserDao.queryUsers(pageNum, pageSize, condition);
        List<SystemUser> returnUsers = new ArrayList<>();
        for(SystemUser systemUser : systemUsers) {
            systemUser.setSecret(null);
            returnUsers.add(systemUser);
        }
        return systemUsers;
    }

    /**
     * 冻结用户
     * @param id
     */
    public void freezeUser(BigInteger id) {
        SystemUser systemUser = systemUserDao.queryById(id);
        systemUser.setEnabled(false);
        systemUserDao.update(systemUser);
    }

    /**
     * 解冻用户
     * @param id
     */
    public void unfreezeUser(BigInteger id) {
        SystemUser systemUser = systemUserDao.queryById(id);
        systemUser.setEnabled(true);
        systemUserDao.update(systemUser);
    }

    /**
     * 重置用户密码
     * @param systemUser
     * @param password
     */
    public void resetPassword(SystemUser systemUser, String password) {
        systemUser.setSecret(bCryptPasswordEncoder.encode(password));
        update(systemUser);
    }
}
