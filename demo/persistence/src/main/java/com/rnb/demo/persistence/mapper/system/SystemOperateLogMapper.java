package com.rnb.demo.persistence.mapper.system;

import com.rnb.demo.entity.po.system.SystemOperateLog;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SystemOperateLogMapper extends BaseMapper<SystemOperateLog> {
}