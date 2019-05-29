package com.rnbbusiness.newbase.security.dao.mapper;

import com.rnbbusiness.newbase.security.dao.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Mapper
@Repository
public interface ResourceMapper {
    Resource findByUrl(@Param("url") String url);

    List<String> findRoles(@Param("resourceId") BigInteger resourceId);
}
