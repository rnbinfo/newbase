package com.rnb.demo.persistence.dao.operator;

import com.rnb.demo.entity.po.operator.OperatorInfo;
import com.rnb.demo.persistence.mapper.operator.OperatorInfoMapper;
import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class OperatorInfoDao extends BaseDao<OperatorInfo> {
    @Resource
    private OperatorInfoMapper operatorInfoMapper;
    @Override
    public BaseMapper<OperatorInfo> getBaseMapper() {
        return operatorInfoMapper;
    }
}
