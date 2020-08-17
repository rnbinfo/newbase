package com.rnb.demo.persistence.mapper.noauth;

import com.rnb.demo.entity.po.noauth.NoauthOperator;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NoauthOperatorMapper extends BaseMapper<NoauthOperator> {
}
