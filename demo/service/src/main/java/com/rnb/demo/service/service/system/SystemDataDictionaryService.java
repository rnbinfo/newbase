package com.rnb.demo.service.service.system;

import com.rnb.demo.entity.po.system.SystemDataDictionary;
import com.rnb.demo.persistence.dao.system.SystemDataDictionaryDao;
import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.service.base.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SystemDataDictionaryService extends BaseService<SystemDataDictionary> {
    @Resource
    private SystemDataDictionaryDao systemDataDictionaryDao;
    @Override
    public BaseDao<SystemDataDictionary> getBaseDao() {
        return systemDataDictionaryDao;
    }

    /**
     * 分页查询数据字典记录
     * @param pageNum
     * @param pageSize
     * @param condition
     * @return
     */
    public List<SystemDataDictionary> findDataDictionaries(int pageNum, int pageSize, SystemDataDictionary condition) {
        List<SystemDataDictionary> systemDataDictionaries = systemDataDictionaryDao.findDataDictionaries(pageNum, pageSize, condition);
        return systemDataDictionaries;
    }

    /**
     * 新增数据字典
     * @param newSystemDataDictionary
     * @return
     */
    public SystemDataDictionary createDataDictionary(SystemDataDictionary newSystemDataDictionary) {
        systemDataDictionaryDao.insert(newSystemDataDictionary);
        SystemDataDictionary createdSystemDataDictionary = systemDataDictionaryDao.queryById(newSystemDataDictionary.getId());
        return createdSystemDataDictionary;
    }

    /**
     * 更新数据字典
     * @param systemDataDictionary
     * @return
     */
    public SystemDataDictionary updateDataDictionary(SystemDataDictionary systemDataDictionary) {
        systemDataDictionaryDao.update(systemDataDictionary);
        SystemDataDictionary updatedSystemDataDictionary = systemDataDictionaryDao.queryById(systemDataDictionary.getId());
        return updatedSystemDataDictionary;
    }
}
