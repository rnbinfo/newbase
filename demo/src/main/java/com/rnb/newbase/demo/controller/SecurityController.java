package com.rnb.newbase.demo.controller;

import com.rnb.newbase.controller.api.HttpFrontRequest;
import com.rnb.newbase.security.persistent.entity.SystemResource;
import com.rnb.newbase.security.persistent.entity.SystemUser;
import com.rnb.newbase.security.resolver.GetUser;
import com.rnb.newbase.security.service.SystemResourceService;
import com.rnb.newbase.security.service.SystemUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class SecurityController {
    @Resource
    private SystemUserService systemUserService;
    @Resource
    private SystemResourceService systemResourceService;

    @PostMapping("getAuthorization")
    public SystemUser getAuthorization(@Valid @RequestBody HttpFrontRequest request, @GetUser SystemUser systemUser) {
        SystemUser systemUserWithAuthorization = systemUserService.findUserWithAuthorization(systemUser);
        systemUserWithAuthorization.setSecret(null);
        return systemUserWithAuthorization;
    }

    @PostMapping("getResources")
    public List<SystemResource> getResources(@Valid @RequestBody HttpFrontRequest request) {
        List<SystemResource> treeResources = systemResourceService.findResourceTree();
        return treeResources;
    }


}
