package com.rnb.demo.persistence.dao.user;

import com.github.pagehelper.PageHelper;
import com.rnb.demo.entity.po.user.UserInfo;
import com.rnb.demo.persistence.mapper.user.UserInfoMapper;
import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.persistence.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserInfoDao extends BaseDao<UserInfo> {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Override
    public BaseMapper<UserInfo> getBaseMapper() {
        return userInfoMapper;
    }
}
