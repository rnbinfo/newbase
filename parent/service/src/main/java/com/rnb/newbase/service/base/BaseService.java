package com.rnb.newbase.service.base;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.rnb.newbase.persistence.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public abstract class BaseService<T> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public abstract BaseDao<T> getBaseDao();

    public int insert(T t) {
        return getBaseDao().insert(t);
    }

    public int update(T t) {
        return getBaseDao().update(t);
    }

    public T queryById(BigInteger id) {
        return getBaseDao().queryById(id);
    }

    public List<T> queryListByCondition(T condition) {
        return getBaseDao().queryListByCondition(condition);
    }

    public T queryOneByCondition(T condition) {
        return getBaseDao().queryOneByCondition(condition);
    }

    // 将对象转换成map
    protected Map<String, Object> convertConditionToMap(Object conditionObject) {
        String conditionString = JSONObject.toJSONString(conditionObject);
        Map<String, Object> condition = JSONObject.parseObject(conditionString, new TypeReference<Map<String, Object>>(){});
        return condition;
    }
}
