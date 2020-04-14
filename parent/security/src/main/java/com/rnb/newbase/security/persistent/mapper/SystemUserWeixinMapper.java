package com.rnb.newbase.security.persistent.mapper;

import com.rnb.newbase.persistence.mapper.BaseMapper;
import com.rnb.newbase.security.persistent.entity.SystemUserWeixin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SystemUserWeixinMapper extends BaseMapper<SystemUserWeixin> {
}
