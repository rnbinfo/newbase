package com.rnb.newbase.security.persistent.mapper;

import com.rnb.newbase.persistence.mapper.BaseMapper;
import com.rnb.newbase.security.persistent.entity.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SystemUserMapper extends BaseMapper<SystemUser> {
    SystemUser findByUsername(@Param("username") String username);

    void deleteUserRole(@Param("userId") BigInteger userId);

    void insertUserRole(@Param("userId") BigInteger userId, @Param("roleId") BigInteger roleId);

    List<SystemUser> queryUsers(Map<String, Object> condition);
}
