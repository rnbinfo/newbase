package com.rnb.demo.service.service.system;

import com.rnb.demo.entity.po.system.SystemOperateLog;
import com.rnb.demo.persistence.dao.system.SystemOperateLogDao;
import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.service.base.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SystemOperateLogService extends BaseService<SystemOperateLog> {
    @Resource
    private SystemOperateLogDao systemOperateLogDao;
    @Override
    public BaseDao<SystemOperateLog> getBaseDao() {
        return systemOperateLogDao;
    }

    /**
     * 分页查询操作日志
     * @param pageNum
     * @param pageSize
     * @param condition
     * @return
     */
    public List<SystemOperateLog> findOperateLogs(int pageNum, int pageSize, SystemOperateLog condition) {
        List<SystemOperateLog> systemOperateLogs = systemOperateLogDao.findOperateLogs(pageNum, pageSize, condition);
        return systemOperateLogs;
    }
}
