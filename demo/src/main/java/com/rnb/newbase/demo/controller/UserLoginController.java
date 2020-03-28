package com.rnb.newbase.demo.controller;

import com.rnb.newbase.controller.api.HttpFrontRequest;
import com.rnb.newbase.demo.api.LoginRequest;
import com.rnb.newbase.demo.api.LoginResponse;
import com.rnb.newbase.demo.api.SignUpRequest;
import com.rnb.newbase.security.persistent.entity.SystemResource;
import com.rnb.newbase.security.persistent.entity.SystemUser;
import com.rnb.newbase.security.resolver.GetUser;
import com.rnb.newbase.security.service.SystemResourceService;
import com.rnb.newbase.security.service.SystemUserService;
import com.rnb.newbase.toolkit.util.RandomUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class UserLoginController {
    @Resource
    private SystemUserService systemUserService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody HttpFrontRequest<LoginRequest> request, HttpServletRequest httpServletRequest) {
        SystemUser systemUser = systemUserService.login(request.getBody().getUsername(), request.getBody().getPassword());
        // 设置userId，生成loginToken放入redis
        String httpSessionId = setSessionUserId(systemUser.getId(), httpServletRequest);
        String loginToken = RandomUtil.generateNoSymbleString(32);
        stringRedisTemplate.opsForValue().set(httpSessionId, loginToken, 60 * 30, TimeUnit.SECONDS);
        return LoginResponse.generateLoginResponse(loginToken);
    }

    @PostMapping("signUp")
    public LoginResponse signUp(@Valid @RequestBody HttpFrontRequest<SignUpRequest> request, HttpServletRequest httpServletRequest) {
        SystemUser systemUser = systemUserService.signUp(request.getBody().getUsername(), request.getBody().getPassword());
        // 设置userId，生成loginToken放入redis
        String httpSessionId = setSessionUserId(systemUser.getId(), httpServletRequest);
        String loginToken = RandomUtil.generateNoSymbleString(32);
        stringRedisTemplate.opsForValue().set(httpSessionId, loginToken, 60 * 30, TimeUnit.SECONDS);
        return LoginResponse.generateLoginResponse(loginToken);
    }

    @PostMapping("logout")
    public void logout(@Valid @RequestBody HttpFrontRequest request, HttpServletRequest httpServletRequest) {
        String httpSessionId = httpServletRequest.getRequestedSessionId();
        httpServletRequest.getSession().removeAttribute("userId");
        // 将数据放入redis
        stringRedisTemplate.delete(httpSessionId);
    }

    // 将用户id设置在session中，并返回sessionId
    private String setSessionUserId(BigInteger userId, HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("userId", userId);
        return httpSession.getId();
    }
}
