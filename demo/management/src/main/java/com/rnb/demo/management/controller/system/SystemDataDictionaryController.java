package com.rnb.demo.management.controller.system;

import com.rnb.demo.entity.api.management.system.CreateDataDictionaryRequest;
import com.rnb.demo.entity.api.management.system.QueryValidDataDictionaryRequest;
import com.rnb.demo.entity.api.management.system.UpdateDataDictionaryRequest;
import com.rnb.demo.entity.constants.CommonStatus;
import com.rnb.demo.entity.constants.DataDictionaryType;
import com.rnb.demo.entity.po.system.SystemDataDictionary;
import com.rnb.demo.management.controller.AbstractController;
import com.rnb.demo.management.remote.accessor.SyncConfigAccessorService;
import com.rnb.demo.management.remote.server.SyncConfigService;
import com.rnb.demo.service.constant.ExceptionInfo;
import com.rnb.demo.service.service.system.SystemDataDictionaryService;
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

@Api(tags = "系统管理 - 数据字典管理")
@RestController
@RequestMapping("system")
public class SystemDataDictionaryController extends AbstractController {
    @Resource
    private SystemDataDictionaryService systemDataDictionaryService;
    @Resource
    private SyncConfigService syncConfigService;
    @Resource
    private SyncConfigAccessorService syncConfigAccessorService;

    @ApiOperation(value = "查询使用中数据字典")
    @PostMapping("queryValidDataDictionaries")
    public List<SystemDataDictionary> queryValidDataDictionary(@Valid @RequestBody HttpFrontRequest<QueryValidDataDictionaryRequest> request) {
        if (request.getBody().getTypes() == null || request.getBody().getTypes().size() == 0) {
            return dataDictionaryCache.findByType(null);
        } else {
            List<SystemDataDictionary> dataDictionaries = new ArrayList<>();
            for(DataDictionaryType type : request.getBody().getTypes()) {
                dataDictionaries.addAll(dataDictionaryCache.findByType(type));
            }
            return dataDictionaries;
        }
    }

    @ApiOperation(value = "分页查询数据字典")
    @PostMapping("queryDataDictionaries")
    public HttpPaginationRepertory<SystemDataDictionary> queryDataDictionaries(@Valid @RequestBody HttpFrontRequest<HttpPaginationCondition<SystemDataDictionary>> request) {
        int pageNum = request.getBody().getCurrentPage();
        int pageSize = request.getBody().getPageSize();
        SystemDataDictionary condition = request.getBody().getCondition();
        List<SystemDataDictionary> systemDataDictionaries = systemDataDictionaryService.findDataDictionaries(pageNum, pageSize, condition);
        return new HttpPaginationRepertory<>(systemDataDictionaries);
    }

    @ApiOperation(value = "新增数据字典")
    @PostMapping("createDataDictionary")
    public SystemDataDictionary createDataDictionary(@Valid @RequestBody HttpFrontRequest<CreateDataDictionaryRequest> request) {
        DataDictionaryType type = request.getBody().getType();
        String key = request.getBody().getKey();
        // 检查对应的type与系统内存在的type描述是否一致
        SystemDataDictionary condition = new SystemDataDictionary();
        condition.setType(type);
        List<SystemDataDictionary> existedDataDictionaries = systemDataDictionaryService.queryListByCondition(condition);
        if (existedDataDictionaries != null && existedDataDictionaries.size() > 0) {
            if (!existedDataDictionaries.get(0).getDescription().equals(request.getBody().getDescription())) {
                logger.error("Create system data dictionary FAILED! Data dictionary type not match, request[{}]", request);
                throw new RnbRuntimeException(ExceptionInfo.SYSTEM_DATA_DICTIONARY_TYPE_NOT_MATCH);
            }
        }
        // 检查对应的type和key在系统内是否存在
        condition.setKey(key);
        SystemDataDictionary existedDataDictionary = systemDataDictionaryService.queryOneByCondition(condition);
        if (existedDataDictionary != null) {
            logger.error("Create system data dictionary FAILED! Data dictionary existed, request[{}]", request);
            throw new RnbRuntimeException(ExceptionInfo.SYSTEM_DATA_DICTIONARY_EXISTED);
        }
        // 新增
        SystemDataDictionary newSystemDataDictionary = new SystemDataDictionary();
        newSystemDataDictionary.setType(type);
        newSystemDataDictionary.setDescription(request.getBody().getDescription());
        newSystemDataDictionary.setKey(key);
        newSystemDataDictionary.setValue(request.getBody().getValue());
        newSystemDataDictionary.setStatus(CommonStatus.NORMAL);
        newSystemDataDictionary.setSystem(false);
        SystemDataDictionary createdSystemDataDictionary = systemDataDictionaryService.createDataDictionary(newSystemDataDictionary);
        // 同步cache
        dataDictionaryCache.createDataDictionary(createdSystemDataDictionary);
        // 通知前端服务同步
        syncConfigService.sendRequest("refreshDataDictionary", null);
        syncConfigAccessorService.sendRequest("refreshDataDictionary", null);
        return createdSystemDataDictionary;
    }

    @ApiOperation(value = "更新数据字典")
    @PostMapping("updateDataDictionary")
    public SystemDataDictionary updateDataDictionary(@Valid @RequestBody HttpFrontRequest<UpdateDataDictionaryRequest> request) {
        // 查询数据库记录
        SystemDataDictionary existedDataDictionary = systemDataDictionaryService.queryById(request.getBody().getId());
        // 判断是否存在是否是系统锁定类型
        if (existedDataDictionary == null) {
            logger.error("Update system data dictionary FAILED! Data dictionary not existed, request[{}]", request);
            throw new RnbRuntimeException(ExceptionInfo.SYSTEM_DATA_DICTIONARY_NOT_EXISTED);
        }
        if (existedDataDictionary.getSystem()) {
            logger.error("Update system data dictionary FAILED! Data dictionary locked, request[{}]", request);
            throw new RnbRuntimeException(ExceptionInfo.SYSTEM_DATA_DICTIONARY_LOCKED);
        }
        // 更新
        existedDataDictionary.setValue(request.getBody().getValue());
        existedDataDictionary.setStatus(request.getBody().getStatus());
        SystemDataDictionary updatedSystemDataDictionary = systemDataDictionaryService.updateDataDictionary(existedDataDictionary);
        // 同步cache
        if (CommonStatus.NORMAL.getKey().equals(updatedSystemDataDictionary.getStatus())) {
            dataDictionaryCache.updateDataDictionary(updatedSystemDataDictionary);
        } else {
            dataDictionaryCache.deleteDataDictionary(updatedSystemDataDictionary);
        }
        // 通知前端服务同步
        syncConfigService.sendRequest("refreshDataDictionary", null);
        syncConfigAccessorService.sendRequest("refreshDataDictionary", null);
        return updatedSystemDataDictionary;
    }
}
