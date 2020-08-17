package com.rnb.demo.service.cache;

import com.rnb.demo.entity.constants.DataDictionaryType;
import com.rnb.demo.entity.po.system.SystemDataDictionary;
import com.rnb.demo.service.service.system.SystemDataDictionaryService;
import com.rnb.newbase.toolkit.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
@Lazy(false)
public class DataDictionaryCache implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<SystemDataDictionary> dataDictionaries;

    @Resource
    private SystemDataDictionaryService systemDataDictionaryService;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Start to initial system data dictionary");
        refreshAllDataDictionaries();
    }

    /**
     * 刷新缓存数据字典
     */
    public void refreshAllDataDictionaries() {
        SystemDataDictionary condition = new SystemDataDictionary();
        List<SystemDataDictionary> dataDictionaries = systemDataDictionaryService.queryListByCondition(condition);
        logger.info("Refresh all normal dataDictionaries[{}]", dataDictionaries.size());
        this.dataDictionaries = dataDictionaries;
    }

    /**
     * 按类型查询所有数据字典
     * @param type
     * @return
     */
    public List<SystemDataDictionary> findByType(DataDictionaryType type) {
        if (type == null) {
            return dataDictionaries;
        }
        List<SystemDataDictionary> foundDataDictionaries = new ArrayList<>();
        for(SystemDataDictionary dataDictionary : dataDictionaries) {
            if (type.equals(dataDictionary.getType())) {
                foundDataDictionaries.add(dataDictionary);
            }
        }
        return foundDataDictionaries;
    }

    /**
     * 按类型和健找到对应的数据字典
     * @param type
     * @param key
     * @return
     */
    public SystemDataDictionary findByTypeKey(DataDictionaryType type, String key) {
        if (type == null || StringUtil.isBlank(key)) {
            return null;
        }
        for(SystemDataDictionary dataDictionary : dataDictionaries) {
            if (type.equals(dataDictionary.getType()) && key.equals(dataDictionary.getKey())) {
                return dataDictionary;
            }
        }
        return null;
    }

    /**
     * 按类型和健找到对应数据字典值
     * @param type
     * @param key
     * @return
     */
    public String findValueByTypeKey(DataDictionaryType type, String key) {
        SystemDataDictionary dataDictionary = findByTypeKey(type, key);
        if (dataDictionary != null) {
            return dataDictionary.getValue();
        }
        return null;
    }

    /**
     * 按类型和值找到对应数据字典的键
     * @param type
     * @param value
     * @return
     */
    public String findKeyByTypeValue(DataDictionaryType type, String value) {
        if (type == null || StringUtil.isBlank(value)) {
            return null;
        }
        for(SystemDataDictionary dataDictionary : dataDictionaries) {
            if (type.equals(dataDictionary.getType()) && value.equals(dataDictionary.getValue())) {
                return dataDictionary.getKey();
            }
        }
        return null;
    }

    /**
     * 更新参数数据字典
     */
    public void updateDataDictionary(SystemDataDictionary dataDictionary) {
        logger.debug("Update system data dictionary from cache, data dictionary[{}]", dataDictionary);
        // 从list中删除原数据
        deleteDataDictionary(dataDictionary);
        // 如果状态为正常，则将新数据回入list
        dataDictionaries.add(dataDictionary);
    }

    /**
     * 新增参数数据字典
     */
    public void createDataDictionary(SystemDataDictionary dataDictionary) {
        logger.debug("Create system data dictionary from cache, data dictionary[{}]", dataDictionary);
        dataDictionaries.add(dataDictionary);
    }

    /**
     * 删除参数数据字典
     */
    public void deleteDataDictionary(SystemDataDictionary dataDictionary) {
        logger.debug("Delete system data dictionary from cache, data dictionary[{}]", dataDictionary);
        for(SystemDataDictionary dataDictionaryByList : dataDictionaries) {
            if (dataDictionaryByList.getType().equals(dataDictionary.getType())) {
                if (dataDictionaryByList.getKey().equals(dataDictionary.getKey())) {
                    logger.debug("Find system data dictionary need to be deleted, cachedDataDictionary[{}]", dataDictionaryByList);
                    dataDictionaries.remove(dataDictionaryByList);
                    break;
                }
            }
        }
    }
}
