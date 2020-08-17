package com.rnb.demo.persistence.dao.system;

import com.github.pagehelper.PageHelper;
import com.rnb.demo.entity.po.system.SystemParameter;
import com.rnb.demo.persistence.mapper.system.SystemParameterMapper;
import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class SystemParameterDao extends BaseDao<SystemParameter> {
    @Resource
    private SystemParameterMapper systemParameterMapper;
    @Override
    public BaseMapper<SystemParameter> getBaseMapper() {
        return systemParameterMapper;
    }

    public List<SystemParameter> findParameters(int pageNum, int pageSize, SystemParameter condition) {
        PageHelper.startPage(pageNum, pageSize);
        List<SystemParameter> parameters = systemParameterMapper.queryListByCondition(condition);
        return parameters;
    }
}
