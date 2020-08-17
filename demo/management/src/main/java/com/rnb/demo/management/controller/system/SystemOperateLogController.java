package com.rnb.demo.management.controller.system;

import com.rnb.demo.entity.po.system.SystemOperateLog;
import com.rnb.demo.management.controller.AbstractController;
import com.rnb.demo.service.service.system.SystemOperateLogService;
import com.rnb.newbase.controller.api.HttpFrontRequest;
import com.rnb.newbase.controller.api.HttpPaginationCondition;
import com.rnb.newbase.controller.api.HttpPaginationRepertory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "系统管理 - 操作日志类")
@RestController
@RequestMapping("system")
public class SystemOperateLogController extends AbstractController {
    @Resource
    private SystemOperateLogService systemOperateLogService;

    @ApiOperation(value = "分页查询操作日志")
    @PostMapping("queryOperateLogs")
    public HttpPaginationRepertory<SystemOperateLog> queryOperateLogs(@RequestBody HttpFrontRequest<HttpPaginationCondition<SystemOperateLog>> request) {
        int pageNum = request.getBody().getCurrentPage();
        int pageSize = request.getBody().getPageSize();
        SystemOperateLog condition = request.getBody().getCondition();
        List<SystemOperateLog> systemOperateLogs = systemOperateLogService.findOperateLogs(pageNum, pageSize, condition);
        return new HttpPaginationRepertory<>(systemOperateLogs);
    }
}
