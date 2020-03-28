package com.rnb.newbase.security.persistent.mapper;

import com.rnb.newbase.persistence.mapper.BaseMapper;
import com.rnb.newbase.security.persistent.entity.SystemRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Mapper
@Repository
public interface SystemRoleMapper extends BaseMapper<SystemRole> {
    List<SystemRole> findUserRoles(@Param("userId") BigInteger userId);

    void insertRoleResource(@Param("roleId") BigInteger roleId, @Param("resourceId") BigInteger accessResourceId);

    void deleteRoleResource(@Param("roleId") BigInteger roleId);

    void deleteUserRole(@Param("roleId") BigInteger roleId);

    void delete(@Param("id") BigInteger id);
}
