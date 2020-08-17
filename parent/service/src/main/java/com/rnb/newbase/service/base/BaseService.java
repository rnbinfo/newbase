package com.rnb.newbase.service.base;

import com.rnb.newbase.entity.AbstractEntity;
import com.rnb.newbase.persistence.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.List;

public abstract class BaseService<T extends AbstractEntity> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public abstract BaseDao<T> getBaseDao();

    public int insert(T t) {
        return getBaseDao().insert(t);
    }

    public T insertReturnObject(T t) {
        getBaseDao().insert(t);
        T insertedT = queryById(t.getId());
        return insertedT;
    }

    public int update(T t) {
        return getBaseDao().update(t);
    }

    public T updateReturnObject(T t) {
        getBaseDao().update(t);
        T updatedT = queryById(t.getId());
        return updatedT;
    }

    public T queryById(BigInteger id) {
        return getBaseDao().queryById(id);
    }

    public List<T> queryListByCondition(T condition) {
        return getBaseDao().queryListByCondition(condition);
    }

    public List<T> queryPagesByCondition(int pageNum, int pageSize, T condition) {
        return getBaseDao().queryPagesByCondition(pageNum, pageSize, condition);
    }

    public T queryOneByCondition(T condition) {
        return getBaseDao().queryOneByCondition(condition);
    }

    public int insertOrUpdate(T t) {
        int updateCount = 0;
        if (t.getId() == null) {
            updateCount = insert(t);
        } else {
            updateCount = update(t);
        }
        return updateCount;
    }
}
