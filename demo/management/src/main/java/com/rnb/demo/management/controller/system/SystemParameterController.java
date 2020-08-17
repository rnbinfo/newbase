package com.rnb.demo.management.controller.system;

import com.rnb.demo.entity.api.management.system.CreateParameterRequest;
import com.rnb.demo.entity.api.management.system.QueryValidParameterRequest;
import com.rnb.demo.entity.api.management.system.UpdateParameterRequest;
import com.rnb.demo.entity.constants.ParameterType;
import com.rnb.demo.entity.po.system.SystemParameter;
import com.rnb.demo.management.controller.AbstractController;
import com.rnb.demo.management.remote.accessor.SyncConfigAccessorService;
import com.rnb.demo.management.remote.server.SyncConfigService;
import com.rnb.demo.service.constant.ExceptionInfo;
import com.rnb.demo.service.service.system.SystemParameterService;
import com.rnb.newbase.controller.api.HttpFrontRequest;
import com.rnb.newbase.controller.api.HttpPaginationCondition;
import com.rnb.newbase.controller.api.HttpPaginationRepertory;
import com.rnb.newbase.exception.RnbRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "系统管理 - 系统参数管理")
@RestController
@RequestMapping("system")
public class SystemParameterController extends AbstractController {
    @Resource
    private SystemParameterService systemParameterService;
    @Resource
    private SyncConfigService syncConfigService;
    @Resource
    private SyncConfigAccessorService syncConfigAccessorService;

    @ApiOperation(value = "查询使用中系统参数")
    @PostMapping("queryValidParameters")
    public List<SystemParameter> queryValidParameters(@Valid @RequestBody HttpFrontRequest<QueryValidParameterRequest> request) {
        if (request.getBody().getTypes() == null || request.getBody().getTypes().size() == 0) {
            return parameterCache.findByType(null);
        } else {
            List<SystemParameter> dataDictionaries = new ArrayList<>();
            for(ParameterType type : request.getBody().getTypes()) {
                dataDictionaries.addAll(parameterCache.findByType(type));
            }
            return dataDictionaries;
        }
    }

    @ApiOperation(value = "分页查询数据字典")
    @PostMapping("queryParameters")
    public HttpPaginationRepertory<SystemParameter> queryParameters(@Valid @RequestBody HttpFrontRequest<HttpPaginationCondition<SystemParameter>> request) {
        int pageNum = request.getBody().getCurrentPage();
        int pageSize = request.getBody().getPageSize();
        SystemParameter condition = request.getBody().getCondition();
        List<SystemParameter> systemDataDictionaries = systemParameterService.findParameters(pageNum, pageSize, condition);
        return new HttpPaginationRepertory<>(systemDataDictionaries);
    }

    @ApiOperation(value = "新增数据字典")
    @PostMapping("createParameter")
    public SystemParameter createParameter(@Valid @RequestBody HttpFrontRequest<CreateParameterRequest> request) {
        ParameterType type = request.getBody().getType();
        String code= request.getBody().getCode();
        String key = request.getBody().getKey();
        // 检查对应的type, code和key在系统内是否存在
        SystemParameter condition = new SystemParameter();
        condition.setType(type);
        condition.setCode(code);
        condition.setKey(key);
        SystemParameter existedParameter = systemParameterService.queryOneByCondition(condition);
        if (existedParameter != null) {
            logger.error("Create system parameter FAILED! Parameter existed, request[{}]", request);
            throw new RnbRuntimeException(ExceptionInfo.SYSTEM_PARAMETER_EXISTED);
        }
        // 新增
        SystemParameter newSystemParameter = new SystemParameter();
        newSystemParameter.setType(type);
        newSystemParameter.setCode(code);
        newSystemParameter.setDescription(request.getBody().getDescription());
        newSystemParameter.setKey(key);
        newSystemParameter.setValue(request.getBody().getValue());
        SystemParameter createdSystemParameter = systemParameterService.createParameter(newSystemParameter);
        // 同步cache
        parameterCache.createParameter(createdSystemParameter);
        // 通知前端服务同步
        syncConfigService.sendRequest("refreshParameter", null);
        syncConfigAccessorService.sendRequest("refreshParameter", null);
        return createdSystemParameter;
    }

    @ApiOperation(value = "更新数据字典")
    @PostMapping("updateParameter")
    public SystemParameter updateParameter(@Valid @RequestBody HttpFrontRequest<UpdateParameterRequest> request) {
        // 查询数据库记录
        SystemParameter existedParameter = systemParameterService.queryById(request.getBody().getId());
        // 判断是否存在是否是系统锁定类型
        if (existedParameter == null) {
            logger.error("Update system data dictionary FAILED! Data dictionary not existed, request[{}]", request);
            throw new RnbRuntimeException(ExceptionInfo.SYSTEM_PARAMETER_NOT_EXISTED);
        }
        // 更新
        existedParameter.setValue(request.getBody().getValue());
        SystemParameter updatedSystemParameter = systemParameterService.updateParameter(existedParameter);
        // 同步cache
        parameterCache.updateParameter(updatedSystemParameter);
        // 通知前端服务同步
        syncConfigService.sendRequest("refreshParameter", null);
        syncConfigAccessorService.sendRequest("refreshParameter", null);
        return updatedSystemParameter;
    }
}
