package com.rnb.demo.accessor.controller.accessor;

import com.rnb.demo.accessor.controller.AbstractController;
import com.rnb.demo.entity.api.noauth.noauth.LoginRequest;
import com.rnb.demo.entity.api.noauth.noauth.LoginResponse;
import com.rnb.demo.entity.constants.DataDictionaryType;
import com.rnb.demo.entity.enums.noauth.NoauthOperatorStatus;
import com.rnb.demo.entity.po.noauth.NoauthOperator;
import com.rnb.demo.service.constant.ExceptionInfo;
import com.rnb.demo.service.service.noauth.NoauthOperatorService;
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
    private NoauthOperatorService noauthOperatorService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody HttpFrontRequest<LoginRequest> request, HttpServletRequest httpServletRequest) {
        NoauthOperator operator = noauthOperatorService.findByAccount(request.getBody().getAccessorId(), request.getBody().getAccount());
        if (!operator.getStatus().equals(dataDictionaryCache.findKeyByTypeValue(DataDictionaryType.ACCESSOR_OPERATOR_STATUS, NoauthOperatorStatus.NORMAL.getValue()))) {
            logger.error("Login failed! Operator status not normal");
            throw new RnbRuntimeException(ExceptionInfo.OPERATOR_STATUS_ABNORMAL);
        }
        String loginToken = noauthOperatorService.login(request.getBody().getAccount() + "@" + request.getBody().getAccessorId().toString(),
                request.getBody().getPassword(), httpServletRequest.getSession().getId());
        return LoginResponse.generateResponse(loginToken, operator);
    }

    @PostMapping("/logout")
    public void logout(@Valid @RequestBody HttpFrontRequest request, HttpServletRequest httpServletRequest) {
        noauthOperatorService.logout(httpServletRequest.getSession().getId());
    }
}
