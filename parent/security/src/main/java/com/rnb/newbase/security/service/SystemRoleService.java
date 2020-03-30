package com.rnb.newbase.security.service;

import com.rnb.newbase.exception.NewbaseExceptionConstants;
import com.rnb.newbase.exception.RnbRuntimeException;
import com.rnb.newbase.persistence.dao.BaseDao;
import com.rnb.newbase.security.persistent.dao.SystemRoleDao;
import com.rnb.newbase.security.persistent.entity.SystemResource;
import com.rnb.newbase.security.persistent.entity.SystemRole;
import com.rnb.newbase.service.base.BaseService;
import com.rnb.newbase.toolkit.util.ListUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class SystemRoleService extends BaseService<SystemRole> {
    @Resource
    private SystemRoleDao systemRoleDao;
    @Resource
    private SystemResourceService systemResourceService;
    @Override
    public BaseDao<SystemRole> getBaseDao() {
        return systemRoleDao;
    }

    /**
     * 查询用户对应的角色及资源权限
     * @param userId
     * @return
     */
    public List<SystemRole> findUserRolesWithResource(BigInteger userId) {
        List<SystemRole> userRoles = systemRoleDao.findUserRoles(userId);
        List<SystemRole> userRolesWithResource = new ArrayList<>();
        for (SystemRole userRole : userRoles) {
            List<SystemResource> roleResources = systemResourceService.findRoleResources(userRole.getId());
            userRole.setResources(roleResources);
            userRolesWithResource.add(userRole);
        }
        return userRolesWithResource;
    }

    /**
     * 查询指定条件的角色及资源权限
     * @param condition
     * @return
     */
    public List<SystemRole> findRolesWithResource(SystemRole condition) {
        List<SystemRole> roles = systemRoleDao.queryListByCondition(condition);
        List<SystemRole> rolesWithResource = new ArrayList<>();
        for (SystemRole role : roles) {
            List<SystemResource> roleResources = systemResourceService.findRoleResources(role.getId());
            role.setResources(roleResources);
            rolesWithResource.add(role);
        }
        return rolesWithResource;
    }

    /**
     * 按id查询角色及资源信息
     * @param roleId
     * @return
     */
    public SystemRole findRoleWithResource(BigInteger roleId) {
        SystemRole condition = new SystemRole();
        condition.setId(roleId);
        List<SystemRole> rolesWithResource  = findRolesWithResource(condition);
        if (ListUtil.isNotEmpty(rolesWithResource) && rolesWithResource.size() == 1) {
            return rolesWithResource.get(0);
        } else {
            throw new RnbRuntimeException(NewbaseExceptionConstants.SECURITY_ROLE_NOT_ONLY_ONE);
        }
    }

    /**
     * 新增用户角色
     * @param newSystemRole
     * @param accessResourcesId
     * @return
     */
    @Transactional
    public SystemRole createRole(SystemRole newSystemRole, List<BigInteger> accessResourcesId) {
        systemRoleDao.insert(newSystemRole);
        insertRoleResource(newSystemRole.getId(), accessResourcesId);
        SystemRole newRole = findRoleWithResource(newSystemRole.getId());
        return newRole;
    }

    /**
     * 更新用户角色
     * @param systemRole
     * @param accessResourcesId
     * @return
     */
    @Transactional
    public SystemRole updateRole(SystemRole systemRole, List<BigInteger> accessResourcesId) {
        systemRoleDao.update(systemRole);
        //删除原有所有用户角色
        systemRoleDao.deleteRoleResource(systemRole.getId());
        //插入新资源
        insertRoleResource(systemRole.getId(), accessResourcesId);
        //返回对象
        SystemRole newRole = findRoleWithResource(systemRole.getId());
        return newRole;
    }

    private void insertRoleResource(BigInteger roleId, List<BigInteger> accessResourcesId) {
        for(BigInteger accessResourceId : accessResourcesId) {
            // 检查对应的权限是否存在
            if (systemResourceService.checkResourceExisted(accessResourceId)) {
                systemRoleDao.insertRoleResource(roleId, accessResourceId);
            } else {
                logger.error("Create role failed ! ResourceId[{}] not existed!", accessResourceId);
                throw new RnbRuntimeException(NewbaseExceptionConstants.SECURITY_RESOURCE_NOT_EXISTED);
            }
        }
    }

    /**
     * 删除角色，角色对应资源配置，用户对应角色配置
     * @param id
     */
    @Transactional
    public void deleteRole(BigInteger id) {
        systemRoleDao.deleteRoleResource(id);
        systemRoleDao.deleteUserRole(id);
        systemRoleDao.delete(id);
    }

    /**
     * 检查对应的roleId是否存在
     * @param roleId
     * @return
     */
    public Boolean checkRoleExisted(BigInteger roleId) {
        SystemRole systemRole = systemRoleDao.queryById(roleId);
        if (systemRole != null) {
            return true;
        }
        return false;
    }
}
