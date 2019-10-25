package com.rnb.newbase.security.service;

import com.rnb.newbase.security.dao.api.UserDao;
import com.rnb.newbase.security.config.UserPrincipal;
import com.rnb.newbase.security.dao.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserPrincipalService.class);
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //SysUser对应数据库中的用户表，是最终存储用户和密码的表，可自定义
        //本例使用SysUser中的name作为用户名:
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + username + " not found");
        }
        // SecurityUser实现UserDetails并将SysUser的name映射为username
        UserPrincipal userPrincipal = new UserPrincipal(user, user.getRoleCodes());
        return userPrincipal;
    }
}
