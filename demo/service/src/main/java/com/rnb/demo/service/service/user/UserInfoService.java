package com.rnb.demo.service.service.user;

import com.rnb.demo.entity.constants.DataDictionaryType;
import com.rnb.demo.entity.constants.ParameterType;
import com.rnb.demo.entity.enums.user.UserParameter;
import com.rnb.demo.entity.enums.user.UserStatus;
import com.rnb.demo.entity.po.user.UserInfo;
import com.rnb.demo.persistence.dao.user.UserInfoDao;
import com.rnb.demo.service.cache.DataDictionaryCache;
import com.rnb.demo.service.cache.ParameterCache;
import com.rnb.demo.service.constant.ExceptionInfo;
import com.rnb.demo.service.message.sms.SmsSendService;
import com.rnb.newbase.exception.RnbRuntimeException;
import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.security.persistent.entity.SystemUser;
import com.rnb.newbase.security.service.SystemUserService;
import com.rnb.newbase.service.base.BaseService;
import com.rnb.newbase.toolkit.util.RandomUtil;
import com.rnb.newbase.toolkit.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserInfoService extends BaseService<UserInfo> {
    @Resource
    private UserInfoDao userInfoDao;
    @Override
    public BaseDao<UserInfo> getBaseDao() {
        return userInfoDao;
    }

    @Resource
    private DataDictionaryCache dataDictionaryCache;
    @Resource
    private ParameterCache parameterCache;

    /**
     * 根据接入号和手机号，查询用户，没有则新增
     * @param accessorId
     * @param mobile
     * @return
     */
    public UserInfo getUserInfo(BigInteger accessorId, String mobile) {
        UserInfo condition = new UserInfo();
        condition.setAccessorId(accessorId);
        condition.setMobile(mobile);
        UserInfo existedUserInfo = userInfoDao.queryOneByCondition(condition);
        if (existedUserInfo == null) {
            logger.warn("User info not existed, start to create user! AccessorId[{}], mobile[{}]", accessorId, mobile);
            UserInfo newUserInfo = new UserInfo();
            newUserInfo.setAccessorId(accessorId);
            newUserInfo.setMobile(mobile);
            newUserInfo.setAvatar(parameterCache.findKeyByTypeCode(ParameterType.USER_DEFAULT_PARAMETER, UserParameter.DEFAULT_AVATAR.getValue()));
            newUserInfo.setStatus(dataDictionaryCache.findKeyByTypeValue(DataDictionaryType.USER_STATUS, UserStatus.NORMAL.getValue()));
            existedUserInfo = insertReturnObject(newUserInfo);
        } else if (!existedUserInfo.getStatus().equals(dataDictionaryCache.findKeyByTypeValue(DataDictionaryType.USER_STATUS, UserStatus.NORMAL.getValue()))) {
            logger.error("User info status abnormal! AccessorId[{}], mobile[{}]", accessorId, mobile);
            throw new RnbRuntimeException(ExceptionInfo.USER_STATUS_ABNORMAL);
        }
        return existedUserInfo;
    }

    /**
     * 分页查询用户信息
     * @param pageNum
     * @param pageSize
     * @param condition
     * @return
     */
    public List<UserInfo> findUsersInfo(int pageNum, int pageSize, UserInfo condition) {
        List<UserInfo> usersInfo = userInfoDao.findUsersInfo(pageNum, pageSize, condition);
        return usersInfo;
    }
}
