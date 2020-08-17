package com.rnb.demo.service.service.operator;

import com.rnb.demo.entity.po.operator.OperatorInfo;
import com.rnb.demo.persistence.dao.operator.OperatorInfoDao;
import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.security.service.SystemUserService;
import com.rnb.newbase.service.base.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OperatorInfoService extends BaseService<OperatorInfo> {
    @Resource
    private OperatorInfoDao operatorInfoDao;
    @Override
    public BaseDao<OperatorInfo> getBaseDao() {
        return operatorInfoDao;
    }

    @Resource
    private SystemUserService systemUserService;

    public static final String MANAGE_OPERATOR_SUFFIX="@manage";

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

    /**
     * 按账号查找操作员账号
     * @param account
     * @return
     */
    public OperatorInfo findByAccount(String account) {
        OperatorInfo condition = new OperatorInfo();
        condition.setAccount(account);
        OperatorInfo existedOperatorInfo = operatorInfoDao.queryOneByCondition(condition);
        return existedOperatorInfo;
    }
}
