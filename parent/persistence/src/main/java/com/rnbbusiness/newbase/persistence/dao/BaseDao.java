package com.rnbbusiness.newbase.persistence.dao;

import com.rnbbusiness.newbase.persistence.mapper.BaseMapper;

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
}
