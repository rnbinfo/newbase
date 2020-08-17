package com.rnb.demo.persistence.dao.system;

import com.github.pagehelper.PageHelper;
import com.rnb.demo.entity.po.system.SystemDataDictionary;
import com.rnb.demo.persistence.mapper.system.SystemDataDictionaryMapper;
import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class SystemDataDictionaryDao extends BaseDao<SystemDataDictionary> {
    @Resource
    private SystemDataDictionaryMapper systemDataDictionaryMapper;
    @Override
    public BaseMapper<SystemDataDictionary> getBaseMapper() {
        return systemDataDictionaryMapper;
    }

    public List<SystemDataDictionary> findDataDictionaries(int pageNum, int pageSize, SystemDataDictionary condition) {
        PageHelper.startPage(pageNum, pageSize);
        List<SystemDataDictionary> systemDataDictionaries = systemDataDictionaryMapper.queryListByCondition(condition);
        return systemDataDictionaries;
    }
}
