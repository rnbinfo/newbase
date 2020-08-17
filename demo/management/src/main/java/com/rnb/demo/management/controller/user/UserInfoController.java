package com.rnb.demo.management.controller.user;

import com.rnb.demo.entity.po.user.UserInfo;
import com.rnb.demo.management.controller.AbstractController;
import com.rnb.demo.service.service.user.UserInfoService;
import com.rnb.newbase.controller.api.HttpFrontRequest;
import com.rnb.newbase.controller.api.HttpPaginationCondition;
import com.rnb.newbase.controller.api.HttpPaginationRepertory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "用户管理 - 用户管理")
@RestController
@RequestMapping("user")
public class UserInfoController extends AbstractController {
    @Resource
    private UserInfoService userInfoService;

    @ApiOperation(value = "分页查询用户信息")
    @RequestMapping("queryUsersInfo")
    public HttpPaginationRepertory<UserInfo> queryUsersInfo(@Valid @RequestBody HttpFrontRequest<HttpPaginationCondition<UserInfo>> request) {
        List<UserInfo> usersInfo = userInfoService.findUsersInfo(request.getBody().getCurrentPage(), request.getBody().getPageSize(), request.getBody().getCondition());
        return new HttpPaginationRepertory<>(usersInfo);
    }
}
