package com.rnb.demo.persistence.mapper.system;

import com.rnb.demo.entity.po.system.SystemDataDictionary;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SystemDataDictionaryMapper extends BaseMapper<SystemDataDictionary> {
}