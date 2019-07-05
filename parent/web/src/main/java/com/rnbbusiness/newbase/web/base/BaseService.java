package com.rnbbusiness.newbase.web.base;

import com.rnbbusiness.newbase.persistence.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.List;

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
}
