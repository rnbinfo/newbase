package com.rnb.demo.persistence.dao.noauth;

import com.rnb.demo.entity.po.noauth.NoauthOperator;
import com.rnb.demo.persistence.mapper.noauth.NoauthOperatorMapper;
import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class NoauthOperatorDao extends BaseDao<NoauthOperator> {
    @Resource
    private NoauthOperatorMapper noauthOperatorMapper;
    @Override
    public BaseMapper<NoauthOperator> getBaseMapper() {
        return noauthOperatorMapper;
    }
}
