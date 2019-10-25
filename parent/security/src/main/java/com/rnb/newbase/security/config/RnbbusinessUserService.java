package com.rnb.newbase.security.config;


import com.rnb.newbase.security.dao.api.UserDao;
import com.rnb.newbase.security.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RnbbusinessUserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.findByUsername(username); //根据用户名查询用户
        if (user == null) {
            throw new UsernameNotFoundException("can not found username " + username);
        }
        return new UserPrincipal(user, user.getRoleCodes());
    }

}
