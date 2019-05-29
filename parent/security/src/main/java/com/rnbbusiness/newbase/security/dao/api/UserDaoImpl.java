package com.rnbbusiness.newbase.security.dao.api;

import com.rnbbusiness.newbase.security.dao.entity.User;
import com.rnbbusiness.newbase.security.dao.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMapper userMapper;

    public User findByUsername(String username) {
        User user = userMapper.findByName(username);
        List<String> roleCodes = userMapper.findRoles(user.getId());
        user.setRoleCodes(roleCodes);
        return user;
    }
}
