package com.rnb.newbase.security.service;

import com.rnb.newbase.exception.NewbaseExceptionConstants;
import com.rnb.newbase.exception.RnbRuntimeException;
import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.security.persistent.dao.SystemResourceDao;
import com.rnb.newbase.security.persistent.entity.SystemResource;
import com.rnb.newbase.service.base.BaseService;
import com.rnb.newbase.toolkit.util.ListUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class SystemResourceService extends BaseService<SystemResource> {
    @Resource
    private SystemResourceDao systemResourceDao;
    @Override
    public BaseDao<SystemResource> getBaseDao() {
        return systemResourceDao;
    }

    /**
     * 查询指定角色下权限资源列表
     * @param roleId
     * @return
     */
    public List<SystemResource> findRoleResources(BigInteger roleId) {
        List<SystemResource> roleResources = systemResourceDao.findRoleResources(roleId);
        return roleResources;
    }

    /**
     * 查询所有权限资源树
     * @return
     */
    public List<SystemResource> findResourceTree() {
        List<SystemResource> allResources = systemResourceDao.queryListByCondition(new SystemResource());
        return recursionResources(allResources);
    }

    private List<SystemResource> recursionResources(List<SystemResource> allResources) {
        List<SystemResource> treeResources = new ArrayList<>();
        for(SystemResource systemResource : allResources) {
            if (systemResource.getParentId() == null) {
                treeResources.add(addChildResources(systemResource, allResources));
            }
        }
        return treeResources;
    }

    private SystemResource addChildResources(SystemResource parentResource, List<SystemResource> allResources) {
        List<SystemResource> childResources = new ArrayList<>();
        for(SystemResource systemResource : allResources) {
            if (systemResource.getParentId() != null && systemResource.getParentId().equals(parentResource.getId())) {
                childResources.add(systemResource);
                addChildResources(systemResource, allResources);
            }
        }
        parentResource.setChildSystemResources(childResources);
        return parentResource;
    }

    /**
     * 检查对应的资源id是否存在
     * @param resourceId
     * @return
     */
    public Boolean checkResourceExisted(BigInteger resourceId) {
        SystemResource systemResource = systemResourceDao.queryById(resourceId);
        if (systemResource != null) {
            return true;
        }
        return false;
    }

    /**
     * 更新父结点包含子信息
     * @param parentId
     */
    public void updateParentHasChild(BigInteger parentId) {
        SystemResource systemResource = systemResourceDao.queryById(parentId);
        systemResource.setHasChild(true);
        update(systemResource);
    }

    /**
     * 更新父结点不包含子信息
     * @param parentId
     */
    public void updateParentHasNotChild(BigInteger parentId) {
        SystemResource systemResource = systemResourceDao.queryById(parentId);
        systemResource.setHasChild(false);
        update(systemResource);
    }

    /**
     * 更新父结点子信息状态
     * @param parentId
     */
    public void updateParentChildStatus(BigInteger parentId) {
        if (checkHasChild(parentId)) {
            updateParentHasChild(parentId);
        } else {
            updateParentHasNotChild(parentId);
        }
    }

    /**
     * 删除资源结点
     * @param id
     */
    @Transactional
    public void deleteResource(BigInteger id) {
        SystemResource systemResource = systemResourceDao.queryById(id);
        //检查是否有下级节点
        SystemResource condition = new SystemResource();
        condition.setParentId(id);
        List<SystemResource> childResources = systemResourceDao.queryListByCondition(condition);
        if (ListUtil.isEmpty(childResources)) {
            systemResourceDao.delete(id);
            BigInteger parentId = systemResource.getParentId();
            if(parentId != null) {
                updateParentChildStatus(parentId);
            }
        } else {
            throw new RnbRuntimeException(NewbaseExceptionConstants.SECURITY_RESOURCE_NOT_EXISTED);
        }
    }

    private Boolean checkHasChild(BigInteger id) {
        SystemResource condition = new SystemResource();
        condition.setParentId(id);
        List<SystemResource> childResources = systemResourceDao.queryListByCondition(condition);
        if(ListUtil.isNotEmpty(childResources)) {
            return true;
        } else {
            return false;
        }
    }
}
