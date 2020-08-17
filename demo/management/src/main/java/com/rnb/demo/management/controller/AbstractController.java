package com.rnb.demo.management.controller;

import com.rnb.demo.entity.po.operator.OperatorInfo;
import com.rnb.demo.service.cache.DataDictionaryCache;
import com.rnb.demo.service.cache.ParameterCache;
import com.rnb.demo.service.constant.ExceptionInfo;
import com.rnb.demo.service.service.operator.OperatorInfoService;
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
    private OperatorInfoService operatorInfoService;

    public OperatorInfo findOperatorInfo(SystemUser systemUser) {
        if (systemUser == null) {
            logger.error("User not login");
            throw new RnbRuntimeException(ExceptionInfo.USER_NOT_LOGIN);
        }
        OperatorInfo condition = new OperatorInfo();
        condition.setSystemUserId(systemUser.getId());
        OperatorInfo operatorInfo = operatorInfoService.queryOneByCondition(condition);
        return operatorInfo;
    }
}
