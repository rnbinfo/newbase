package com.rnb.newbase.security.service;

import com.rnb.newbase.exception.RnbRuntimeException;
import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.security.config.constant.LoginConstant;
import com.rnb.newbase.security.persistent.dao.SystemUserDao;
import com.rnb.newbase.security.persistent.entity.SystemResource;
import com.rnb.newbase.security.persistent.entity.SystemRole;
import com.rnb.newbase.security.persistent.entity.SystemUser;
import com.rnb.newbase.service.base.BaseService;
import com.rnb.newbase.toolkit.security.BCryptPasswordEncoder;
import com.rnb.newbase.toolkit.util.RandomUtil;
import com.rnb.newbase.toolkit.util.StringUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
    private StringRedisTemplate stringRedisTemplate;

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
        SystemUser condition = new SystemUser();
        condition.setUsername(username);
        SystemUser systemUser = systemUserDao.queryOneByCondition(condition);
        if (systemUser != null) {
            throw new RnbRuntimeException("900004", "user.existed");
        }
        SystemUser newSystemUser = new SystemUser();
        newSystemUser.setUsername(username);
        newSystemUser.setSecret(bCryptPasswordEncoder.encode(password));
        systemUserDao.insert(newSystemUser);
        systemUser = systemUserDao.queryOneByCondition(condition);
        String loginToken = RandomUtil.generateNoSymbleString(32);
        updateRedis(sessionId, loginToken, systemUser.getId());
        return loginToken;
    }

    /**
     * 更新会话，用户id，登录token
     * @param sessionId
     * @param loginToken
     * @param userId
     */
    public void updateRedis(String sessionId, String loginToken, BigInteger userId) {
        stringRedisTemplate.opsForValue().set(LoginConstant.REDIS_SESSION_PREFIX + sessionId, loginToken, 60 * 30, TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set(LoginConstant.REDIS_LOGIN_TOKEN_PREFIX + loginToken, userId.toString(), 60*30, TimeUnit.SECONDS);
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
}
