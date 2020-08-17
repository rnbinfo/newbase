package com.rnb.demo.accessor.controller;

import com.rnb.demo.entity.po.noauth.NoauthOperator;
import com.rnb.demo.service.cache.DataDictionaryCache;
import com.rnb.demo.service.cache.ParameterCache;
import com.rnb.demo.service.constant.ExceptionInfo;
import com.rnb.demo.service.service.noauth.NoauthOperatorService;
import com.rnb.newbase.controller.base.BaseController;
import com.rnb.newbase.exception.RnbRuntimeException;
import com.rnb.newbase.security.persistent.entity.SystemUser;

import javax.annotation.Resource;

public class AbstractController extends BaseController {
    @Resource
    protected DataDictionaryCache dataDictionaryCache;
    @Resource
    protected ParameterCache parameterCache;
    @Resource
    private NoauthOperatorService noauthOperatorService;

    public NoauthOperator findOperatorInfo(SystemUser systemUser) {
        if (systemUser == null) {
            logger.error("User not login");
            throw new RnbRuntimeException(ExceptionInfo.USER_NOT_LOGIN);
        }
        NoauthOperator condition = new NoauthOperator();
        condition.setSystemUserId(systemUser.getId());
        NoauthOperator accessorOperator = noauthOperatorService.queryOneByCondition(condition);
        return accessorOperator;
    }
}
