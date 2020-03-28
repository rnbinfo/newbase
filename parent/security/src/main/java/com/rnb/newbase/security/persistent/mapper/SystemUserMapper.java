package com.rnb.newbase.security.persistent.mapper;

import com.rnb.newbase.persistence.mapper.BaseMapper;
import com.rnb.newbase.security.persistent.entity.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SystemUserMapper extends BaseMapper<SystemUser> {
    SystemUser findByUsername(@Param("username") String username);
}
