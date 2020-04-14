package com.rnb.newbase.security.service;

import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.security.persistent.dao.SystemUserWeixinDao;
import com.rnb.newbase.security.persistent.entity.SystemUserWeixin;
import com.rnb.newbase.service.base.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;

@Service
public class SystemUserWeixinService extends BaseService<SystemUserWeixin> {
    @Resource
    private SystemUserWeixinDao systemUserWeixinDao;
    @Override
    public BaseDao<SystemUserWeixin> getBaseDao() {
        return systemUserWeixinDao;
    }

    public SystemUserWeixin findByUserId(BigInteger userId) {
        SystemUserWeixin condition = new SystemUserWeixin();
        condition.setUserId(userId);
        SystemUserWeixin systemUserWeixin = queryOneByCondition(condition);
        return systemUserWeixin;
    }
}
