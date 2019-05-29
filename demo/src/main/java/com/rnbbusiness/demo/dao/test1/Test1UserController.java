package com.rnbbusiness.demo.dao.test1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Test1UserController {
    @Autowired
    private Test1UserMapper test1UserMapper;

    @RequestMapping("/findTest1Users")
    public List<Test1User> findAll() {
        return test1UserMapper.selectAll();
    }
}
