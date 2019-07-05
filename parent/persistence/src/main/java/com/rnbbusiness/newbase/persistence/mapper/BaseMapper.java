package com.rnbbusiness.newbase.persistence.mapper;

import java.math.BigInteger;
import java.util.List;

public interface BaseMapper<T> {
    public int insert(T t);
    public int update(T t);
    public T queryById(BigInteger id);
    public List<T> queryListByCondition(T Condition);
}
