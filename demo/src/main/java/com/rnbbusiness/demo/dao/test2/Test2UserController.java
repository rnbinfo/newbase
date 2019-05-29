package com.rnbbusiness.demo.dao.test2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Test2UserController {
    @Autowired
    private Test2UserMapper test2UserMapper;

    @RequestMapping("/findTest2Users")
    public List<Test2User> findAll() {
        return test2UserMapper.selectAll();
    }
}
