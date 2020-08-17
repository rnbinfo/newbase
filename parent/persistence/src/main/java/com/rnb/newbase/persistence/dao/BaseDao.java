package com.rnb.newbase.persistence.dao;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.List;

public abstract class BaseDao<T> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract BaseMapper<T> getBaseMapper();

    public int insert(T t) {
        int insertResult = 0;
        try {
            insertResult = getBaseMapper().insert(t);
        } catch (Exception e) {
            logger.error("Newbase Persistence Insert Failed! Object[{}]=>[{}]", t.getClass().getName(), JSON.toJSONString(t));
            throw e;
        }
        return insertResult;
    }

    public int update(T t) {
        int updateResult = 0;
        try {
            updateResult = getBaseMapper().update(t);
        } catch (Exception e) {
            logger.error("Newbase Persistence Update Failed! Object[{}]=>[{}]", t.getClass().getName(), JSON.toJSONString(t));
            throw e;
        }
        return updateResult;
    }

    public T queryById(BigInteger id) {
        return getBaseMapper().queryById(id);
    }

    public List<T> queryListByCondition(T condition) {
        return getBaseMapper().queryListByCondition(condition);
    }

    public List<T> queryPagesByCondition(int pageNum, int pageSize,T condition) {
        PageHelper.startPage(pageNum,pageSize);
        return getBaseMapper().queryListByCondition(condition);
    }

    public T queryOneByCondition(T condition) {
        List<T> objects = getBaseMapper().queryListByCondition(condition);
        if (objects != null && objects.size() == 1) {
            return objects.get(0);
        } else if (objects != null && objects.size() > 1) {
            throw new RuntimeException("Query result is not only one!");
        } else {
            return null;
        }
    }
}
