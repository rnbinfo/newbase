package com.rnbbusiness.demo.dao.test1;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Test1UserMapper {
    List<Test1User> selectAll();
}
