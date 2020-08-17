package com.rnb.demo.service.service.noauth;

import com.rnb.demo.entity.constants.DataDictionaryType;
import com.rnb.demo.entity.enums.noauth.NoauthOperatorStatus;
import com.rnb.demo.entity.po.noauth.NoauthOperator;
import com.rnb.demo.persistence.dao.noauth.NoauthOperatorDao;
import com.rnb.demo.service.cache.DataDictionaryCache;
import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.security.persistent.entity.SystemUser;
import com.rnb.newbase.security.service.SystemUserService;
import com.rnb.newbase.service.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;

@Service
public class NoauthOperatorService extends BaseService<NoauthOperator> {
    @Resource
    private NoauthOperatorDao accessorOperatorDao;
    @Override
    public BaseDao<NoauthOperator> getBaseDao() {
        return accessorOperatorDao;
    }

    @Resource
    private DataDictionaryCache dataDictionaryCache;
    @Resource
    private SystemUserService systemUserService;

    public static final String NOAUTH_OPERATOR_SUFFIX = "@noauth";

    /**
     * 新增接入方操作员
     * @param newAccessorOperator
     * @param password
     * @return
     */
    @Transactional
    public NoauthOperator insertAccessorOperator(NoauthOperator newAccessorOperator, String password) {
        //新增接入方操作员核心用户
        SystemUser systemUser = systemUserService.createUser(newAccessorOperator.getAccount() + NOAUTH_OPERATOR_SUFFIX, password);
        newAccessorOperator.setSystemUserId(systemUser.getId());
        //新拉接入操作员
        newAccessorOperator.setStatus(dataDictionaryCache.findKeyByTypeValue(DataDictionaryType.ACCESSOR_OPERATOR_STATUS, NoauthOperatorStatus.NORMAL.getValue()));
        NoauthOperator insertedOperator = insertReturnObject(newAccessorOperator);
        return insertedOperator;
    }

    /**
     * 按账号查询操作员
     * @param account
     * @return
     */
    public NoauthOperator findByAccount(String account) {
        NoauthOperator condition = new NoauthOperator();
        condition.setAccount(account);
        NoauthOperator existedAccessorOperator = accessorOperatorDao.queryOneByCondition(condition);
        return existedAccessorOperator;
    }

    /**
     * 操作员登录
     * @param username
     * @param password
     * @param sessionId
     * @return
     */
    public String login(String username, String password, String sessionId) {
        String LoginToken = systemUserService.login(username, password, sessionId);
        return LoginToken;
    }

    /**
     * 操作员登出
     * @param sessionId
     */
    public void logout(String sessionId) {
        systemUserService.logout(sessionId);
    }
}
