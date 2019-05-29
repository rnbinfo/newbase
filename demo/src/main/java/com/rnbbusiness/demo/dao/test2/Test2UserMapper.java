package com.rnbbusiness.demo.dao.test2;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Test2UserMapper {
    List<Test2User> selectAll();
}
