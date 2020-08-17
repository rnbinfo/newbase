package com.rnb.demo.persistence.dao.system;

import com.github.pagehelper.PageHelper;
import com.rnb.demo.entity.po.system.SystemOperateLog;
import com.rnb.demo.persistence.mapper.system.SystemOperateLogMapper;
import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class SystemOperateLogDao extends BaseDao<SystemOperateLog> {
    @Resource
    private SystemOperateLogMapper systemOperateLogMapper;
    @Override
    public BaseMapper<SystemOperateLog> getBaseMapper() {
        return systemOperateLogMapper;
    }

    public List<SystemOperateLog> findOperateLogs(int pageNum, int pageSize, SystemOperateLog condition) {
        PageHelper.startPage(pageNum, pageSize);
        List<SystemOperateLog> systemOperateLogs = systemOperateLogMapper.queryListByCondition(condition);
        return systemOperateLogs;
    }
}
