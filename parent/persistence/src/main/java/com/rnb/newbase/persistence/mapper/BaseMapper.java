package com.rnb.newbase.persistence.mapper;

import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {
    int insert(T t);
    int update(T t);
    T queryById(BigInteger id);
    List<T> queryListByCondition(@Param("condition") T Condition);
    List<T> querySortedListByCondition(@Param("condition") T condition, @Param("sorts") Map<String, String> sorts);
}
