package com.rnbbusiness.demo.dao.test1;

import com.rnbbusiness.newbase.security.dao.api.UserDao;
import com.rnbbusiness.newbase.security.dao.api.UserDaoImpl;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityUserDao extends UserDaoImpl implements UserDao {
}
