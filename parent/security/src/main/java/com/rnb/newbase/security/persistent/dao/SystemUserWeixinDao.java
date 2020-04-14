package com.rnb.newbase.security.persistent.dao;

import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import com.rnb.newbase.security.persistent.entity.SystemUserWeixin;
import com.rnb.newbase.security.persistent.mapper.SystemUserWeixinMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class SystemUserWeixinDao extends BaseDao<SystemUserWeixin> {
    @Resource
    private SystemUserWeixinMapper systemUserWeixinMapper;
    @Override
    public BaseMapper<SystemUserWeixin> getBaseMapper() {
        return systemUserWeixinMapper;
    }
}
