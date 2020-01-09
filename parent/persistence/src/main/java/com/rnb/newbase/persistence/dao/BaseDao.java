package com.rnb.newbase.persistence.dao;

import com.rnb.newbase.persistence.mapper.BaseMapper;

import java.math.BigInteger;
import java.util.List;

public abstract class BaseDao<T> {
    public abstract BaseMapper<T> getBaseMapper();

    public int insert(T t) {
        return getBaseMapper().insert(t);
    }

    public int update(T t) {
        return getBaseMapper().update(t);
    }

    public T queryById(BigInteger id) {
        return getBaseMapper().queryById(id);
    }

    public List<T> queryListByCondition(T condition) {
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
