package com.rnb.newbase.security.persistent.dao;

import com.github.pagehelper.PageHelper;
import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import com.rnb.newbase.security.persistent.entity.SystemUser;
import com.rnb.newbase.security.persistent.mapper.SystemUserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

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

    public void deleteUserRole(BigInteger userId) {
        systemUserMapper.deleteUserRole(userId);
    }

    public void insertUserRole(BigInteger userId, BigInteger roleId) {
        systemUserMapper.insertUserRole(userId, roleId);
    }

    public List<SystemUser> queryUsers(int pageNum, int pageSize, Map<String, Object> condition) {
        PageHelper.startPage(pageNum,pageSize);
        List<SystemUser> systemUsers = systemUserMapper.queryUsers(condition);
        return systemUsers;
    }
}
