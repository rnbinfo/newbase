package com.rnb.demo.service.service.system;

import com.rnb.demo.entity.po.system.SystemParameter;
import com.rnb.demo.persistence.dao.system.SystemParameterDao;
import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.service.base.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SystemParameterService extends BaseService<SystemParameter> {
    @Resource
    private SystemParameterDao systemParameterDao;
    @Override
    public BaseDao<SystemParameter> getBaseDao() {
        return systemParameterDao;
    }

    /**
     * 分页查询系统参数
     * @param pageNum
     * @param pageSize
     * @param condition
     * @return
     */
    public List<SystemParameter> findParameters(int pageNum, int pageSize, SystemParameter condition) {
        List<SystemParameter> systemParameters = systemParameterDao.findParameters(pageNum, pageSize, condition);
        return systemParameters;
    }

    /**
     * 新增参数
     * @param newSystemParameter
     * @return
     */
    public SystemParameter createParameter(SystemParameter newSystemParameter) {
        systemParameterDao.insert(newSystemParameter);
        SystemParameter createdSystemParameter = systemParameterDao.queryById(newSystemParameter.getId());
        return createdSystemParameter;
    }

    /**
     * 更新参数
     * @param systemParameter
     * @return
     */
    public SystemParameter updateParameter(SystemParameter systemParameter) {
        systemParameterDao.update(systemParameter);
        SystemParameter updatedSystemParameter = systemParameterDao.queryById(systemParameter.getId());
        return updatedSystemParameter;
    }
}
