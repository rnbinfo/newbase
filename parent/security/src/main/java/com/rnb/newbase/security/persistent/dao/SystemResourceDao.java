package com.rnb.newbase.security.persistent.dao;

import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import com.rnb.newbase.security.persistent.entity.SystemResource;
import com.rnb.newbase.security.persistent.mapper.SystemResourceMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Repository
public class SystemResourceDao extends BaseDao<SystemResource> {
    @Resource
    private SystemResourceMapper systemResourceMapper;
    @Override
    public BaseMapper<SystemResource> getBaseMapper() {
        return systemResourceMapper;
    }

    public void delete(BigInteger id) {
        systemResourceMapper.delete(id);
    }

    public List<SystemResource> findRoleResources(BigInteger roleId) {
        List<SystemResource> roleResources = systemResourceMapper.findRoleResources(roleId);
        return roleResources;
    }
}
