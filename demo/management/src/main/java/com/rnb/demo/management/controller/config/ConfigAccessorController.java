package com.rnb.demo.management.controller.config;

import com.rnb.demo.entity.api.management.config.CreateConfigAccessorRequest;
import com.rnb.demo.entity.api.management.config.UpdateConfigAccessorRequest;
import com.rnb.demo.entity.constants.CommonStatus;
import com.rnb.demo.entity.po.noauth.NoauthOperator;
import com.rnb.demo.entity.po.config.ConfigAccessor;
import com.rnb.demo.management.controller.AbstractController;
import com.rnb.demo.management.schedule.FuelStationSchedule;
import com.rnb.demo.service.constant.ExceptionInfo;
import com.rnb.demo.service.service.noauth.NoauthOperatorService;
import com.rnb.demo.service.service.config.ConfigAccessorService;
import com.rnb.newbase.controller.api.HttpFrontRequest;
import com.rnb.newbase.controller.api.HttpPaginationCondition;
import com.rnb.newbase.controller.api.HttpPaginationRepertory;
import com.rnb.newbase.exception.RnbRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "系统配置类 - 接入方管理类")
@RestController
@RequestMapping("config")
public class ConfigAccessorController extends AbstractController {
    @Resource
    private ConfigAccessorService configAccessorsService;
    @Resource
    private NoauthOperatorService noauthOperatorService;
    @Resource
    private FuelStationSchedule fuelStationSchedule;

    @ApiOperation(value = "分页查询接入方")
    @PostMapping("queryConfigAccessors")
    public HttpPaginationRepertory<ConfigAccessor> QueryConfigBusinessSystems(@Valid @RequestBody HttpFrontRequest<HttpPaginationCondition> request) {
        int pageNum = request.getBody().getCurrentPage();
        int pageSize = request.getBody().getPageSize();
        List<ConfigAccessor> configAccessors = configAccessorsService.findAccessors(pageNum, pageSize, null);
        return new HttpPaginationRepertory<>(configAccessors);
    }

    @ApiOperation(value = "新增接入方")
    @PostMapping("createConfigAccessor")
    public ConfigAccessor createConfigAccessor(@Valid @RequestBody HttpFrontRequest<CreateConfigAccessorRequest> request) {
        ConfigAccessor condition = new ConfigAccessor();
        condition.setCode(request.getBody().getCode());
        ConfigAccessor existedAccessor = configAccessorsService.queryOneByCondition(condition);
        if (existedAccessor != null) {
            logger.error("Create config accessor FAILED! Accessor existed, request[{}]", request);
            throw new RnbRuntimeException(ExceptionInfo.CONFIG_ACCESSOR_EXISTED);
        }
        ConfigAccessor newConfigAccessor = new ConfigAccessor();
        newConfigAccessor.setCode(request.getBody().getCode());
        newConfigAccessor.setName(request.getBody().getName());
        newConfigAccessor.setStatus(CommonStatus.NORMAL);
        ConfigAccessor createdConfigAccessor = configAccessorsService.insertReturnObject(newConfigAccessor);
        // 新增操作员
        NoauthOperator newAccessorOperator = new NoauthOperator();
        newAccessorOperator.setAccessorId(createdConfigAccessor.getId());
        newAccessorOperator.setAccount(request.getBody().getAccount());
        newAccessorOperator.setName(request.getBody().getOperatorName());
        noauthOperatorService.insertAccessorOperator(newAccessorOperator, request.getBody().getPassword());
        return createdConfigAccessor;
    }

    @ApiOperation(value = "更新接入方")
    @PostMapping("updateConfigAccessor")
    public ConfigAccessor updateConfigAccessor(@Valid @RequestBody HttpFrontRequest<UpdateConfigAccessorRequest> request) {
        ConfigAccessor condition = new ConfigAccessor();
        condition.setId(request.getBody().getId());
        ConfigAccessor existedAccessor = configAccessorsService.queryOneByCondition(condition);
        if (existedAccessor == null) {
            logger.error("Update config accessor FAILED! Accessor not existed, request[{}]", request);
            throw new RnbRuntimeException(ExceptionInfo.CONFIG_ACCESSOR_NOT_EXISTED);
        }
        existedAccessor.setStatus(request.getBody().getStatus());
        ConfigAccessor updatedAccessor = configAccessorsService.updateReturnObject(existedAccessor);
        return updatedAccessor;
    }

    @GetMapping("syncStations")
    public void syncStations() {
        fuelStationSchedule.syncStations();
    }
}
