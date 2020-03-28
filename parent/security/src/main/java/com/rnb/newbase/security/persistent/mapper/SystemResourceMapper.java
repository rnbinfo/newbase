package com.rnb.newbase.security.persistent.mapper;

import com.rnb.newbase.persistence.mapper.BaseMapper;
import com.rnb.newbase.security.persistent.entity.SystemResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Mapper
@Repository
public interface SystemResourceMapper extends BaseMapper<SystemResource> {
    void delete(@Param("id") BigInteger id);

    List<SystemResource> findRoleResources(@Param("roleId") BigInteger roleId);
}
