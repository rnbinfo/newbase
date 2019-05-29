package com.rnbbusiness.newbase.security.dao.mapper;

import com.rnbbusiness.newbase.security.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    User findByName(@Param("username") String username);

    List<String> findRoles(@Param("userId") BigInteger userId);
}
