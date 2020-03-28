package com.rnb.newbase.security.persistent.dao;

import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import com.rnb.newbase.security.persistent.entity.SystemRole;
import com.rnb.newbase.security.persistent.mapper.SystemRoleMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Repository
public class SystemRoleDao extends BaseDao<SystemRole> {
    @Resource
    private SystemRoleMapper systemRoleMapper;
    @Override
    public BaseMapper<SystemRole> getBaseMapper() {
        return systemRoleMapper;
    }

    public List<SystemRole> findUserRoles(BigInteger userId) {
        return systemRoleMapper.findUserRoles(userId);
    }

    public void insertRoleResource(BigInteger roleId, BigInteger accessResourceId) {
        systemRoleMapper.insertRoleResource(roleId, accessResourceId);
    }

    public void deleteRoleResource(BigInteger roleId) {
        systemRoleMapper.deleteRoleResource(roleId);
    }

    public void deleteUserRole(BigInteger roleId) {
        systemRoleMapper.deleteUserRole(roleId);
    }

    public void delete(BigInteger id) {
        systemRoleMapper.delete(id);
    }
}
