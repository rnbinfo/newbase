package com.rnb.newbase.persistence.mapper;

import java.math.BigInteger;
import java.util.List;

public interface BaseMapper<T> {
    int insert(T t);
    int update(T t);
    T queryById(BigInteger id);
    List<T> queryListByCondition(T Condition);
}
