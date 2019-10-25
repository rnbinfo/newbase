package com.rnb.newbase.security.dao.api;

import com.rnb.newbase.security.dao.entity.User;

public interface UserDao {
    User findByUsername(String username);
}
