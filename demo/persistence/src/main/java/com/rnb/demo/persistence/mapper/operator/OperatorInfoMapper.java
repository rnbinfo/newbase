package com.rnb.demo.persistence.mapper.operator;

import com.rnb.demo.entity.po.operator.OperatorInfo;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OperatorInfoMapper extends BaseMapper<OperatorInfo> {
}