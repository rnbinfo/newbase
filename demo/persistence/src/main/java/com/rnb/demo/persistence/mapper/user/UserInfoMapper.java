package com.rnb.demo.persistence.mapper.user;

import com.rnb.demo.entity.po.user.UserInfo;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}