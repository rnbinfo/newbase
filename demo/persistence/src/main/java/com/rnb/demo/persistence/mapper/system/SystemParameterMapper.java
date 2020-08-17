package com.rnb.demo.persistence.mapper.system;

import com.rnb.demo.entity.po.system.SystemParameter;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SystemParameterMapper extends BaseMapper<SystemParameter> {
}