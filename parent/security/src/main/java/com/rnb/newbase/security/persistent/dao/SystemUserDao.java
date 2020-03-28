package com.rnb.newbase.security.persistent.dao;

import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import com.rnb.newbase.security.persistent.entity.SystemUser;
import com.rnb.newbase.security.persistent.mapper.SystemUserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class SystemUserDao extends BaseDao<SystemUser> {
    @Resource
    private SystemUserMapper systemUserMapper;
    @Override
    public BaseMapper<SystemUser> getBaseMapper() {
        return systemUserMapper;
    }

    public SystemUser findByUsername(String username) {
        SystemUser systemUser = systemUserMapper.findByUsername(username);
        return systemUser;
    }
}
