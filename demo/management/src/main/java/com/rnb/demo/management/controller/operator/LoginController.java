package com.rnb.demo.management.controller.operator;

import com.rnb.demo.entity.api.management.operator.LoginRequest;
import com.rnb.demo.entity.api.management.operator.LoginResponse;
import com.rnb.demo.entity.constants.DataDictionaryType;
import com.rnb.demo.entity.enums.operator.OperatorInfoStatus;
import com.rnb.demo.entity.po.operator.OperatorInfo;
import com.rnb.demo.management.controller.AbstractController;
import com.rnb.demo.service.constant.ExceptionInfo;
import com.rnb.demo.service.service.operator.OperatorInfoService;
import com.rnb.newbase.controller.api.HttpFrontRequest;
import com.rnb.newbase.exception.RnbRuntimeException;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(tags = "操作员登录登出接口")
@RestController
public class LoginController extends AbstractController {
    @Resource
    private OperatorInfoService operatorInfoService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody HttpFrontRequest<LoginRequest> request, HttpServletRequest httpServletRequest) {
        OperatorInfo operatorInfo = operatorInfoService.findByAccount(request.getBody().getAccount());
        if (!operatorInfo.getStatus().equals(dataDictionaryCache.findKeyByTypeValue(DataDictionaryType.MANAGEMENT_OPERATOR_STATUS, OperatorInfoStatus.NORMAL.getValue()))) {
            logger.error("Login failed! Operator status not normal");
            throw new RnbRuntimeException(ExceptionInfo.OPERATOR_STATUS_ABNORMAL);
        }
        String loginToken = operatorInfoService.login(request.getBody().getAccount() + operatorInfoService.MANAGE_OPERATOR_SUFFIX, request.getBody().getPassword(), httpServletRequest.getSession().getId());
        return LoginResponse.generateResponse(loginToken, operatorInfo);
    }

    @PostMapping("/logout")
    public void logout(@Valid @RequestBody HttpFrontRequest request, HttpServletRequest httpServletRequest) {
        operatorInfoService.logout(httpServletRequest.getSession().getId());
    }
}
