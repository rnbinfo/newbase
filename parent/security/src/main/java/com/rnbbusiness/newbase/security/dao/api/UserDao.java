package com.rnbbusiness.newbase.security.dao.api;

import com.rnbbusiness.newbase.security.dao.entity.User;

public interface UserDao {
    User findByUsername(String username);
}
