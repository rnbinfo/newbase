package com.rnbbusiness.newbase.security.dao.api;

import com.rnbbusiness.newbase.security.dao.entity.Resource;
import com.rnbbusiness.newbase.security.dao.mapper.ResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResourceDaoImpl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ResourceMapper resourceMapper;

    public List<String> findRoleCodes(String url) {
        Resource resource = resourceMapper.findByUrl(url);
        if (resource != null) {
            List<String> roleCodes = resourceMapper.findRoles(resource.getId());
            return roleCodes;
        }
        return null;
    }
}
