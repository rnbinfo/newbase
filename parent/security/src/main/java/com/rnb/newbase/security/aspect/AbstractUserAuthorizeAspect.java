package com.rnb.newbase.security.aspect;

import com.alibaba.fastjson.JSON;
import com.rnb.newbase.controller.api.HttpFrontRequest;
import com.rnb.newbase.exception.RnbRuntimeException;
import com.rnb.newbase.security.config.constant.LoginConstant;
import com.rnb.newbase.security.persistent.entity.SystemResource;
import com.rnb.newbase.security.persistent.entity.SystemUser;
import com.rnb.newbase.security.service.SystemUserService;
import com.rnb.newbase.toolkit.util.ListUtil;
import com.rnb.newbase.toolkit.util.StringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

public abstract class AbstractUserAuthorizeAspect {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SystemUserService systemUserService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public abstract void userAuthorize();

    @Before("userAuthorize()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String httpSessionId = request.getSession().getId();
        String requestUri = request.getRequestURI();
        // 从接口中获取用户loginToken
        if ("POST".equals(request.getMethod())) {
            // 获取参数, 只取自定义的参数, 自带的HttpServletRequest, HttpServletResponse不管
            if (joinPoint.getArgs().length > 0) {
                for (Object argObject : joinPoint.getArgs()) {
                    if (argObject instanceof HttpServletRequest || argObject instanceof HttpServletResponse) {
                        continue;
                    }
                    if (argObject instanceof HttpFrontRequest) {
                        logger.debug("Request for [{}] content [{}]", request.getRequestURI(), JSON.toJSONString(argObject));
                        String loginToken = ((HttpFrontRequest) argObject).getHeader().getLoginToken();
                        if (StringUtil.isBlank(loginToken)) {
                            throw new RnbRuntimeException("999403", "login.token.not.existed");
                        }
                        //从redis中查询对应session是否存在并比对token
                        if (StringUtil.isBlank(httpSessionId)) {
                            throw new RnbRuntimeException("999403", "session.not.existed");
                        }
                        String sessionToken = stringRedisTemplate.opsForValue().get(LoginConstant.REDIS_SESSION_PREFIX + httpSessionId);
                        if (StringUtil.isBlank(sessionToken)) {
                            throw new RnbRuntimeException("999403", "session.token.not.found");
                        }
                        if (!sessionToken.equals(loginToken)) {
                            throw new RnbRuntimeException("999403", "login.token.error");
                        }
                        // 根据token获取用户id
                        String userId = stringRedisTemplate.opsForValue().get(LoginConstant.REDIS_LOGIN_TOKEN_PREFIX + sessionToken);
                        if (StringUtil.isBlank(userId)) {
                            throw new RnbRuntimeException("999403", "login.token.not.existed");
                        }
                        SystemUser systemUser = systemUserService.findUserWithAuthorizationById(new BigInteger(userId));
                        // 判断用户操作权限
                        if (ListUtil.isNotEmpty(systemUser.getResources())) {
                            boolean checkAuthorization = false;
                            for (SystemResource resource : systemUser.getResources()) {
                                if (requestUri.equals(resource.getUrl())) {
                                    // 更新token有效时间
                                    systemUserService.updateRedis(httpSessionId, sessionToken, new BigInteger(userId));
                                    checkAuthorization = true;
                                }
                            }
                            if (!checkAuthorization) {
                                throw new RnbRuntimeException("999403", "user.no.authorization");
                            }
                        } else {
                            throw new RnbRuntimeException("999403", "user.no.authorization");
                        }
                    }
                }
            } else {
                throw new RnbRuntimeException("999500", "error.request");
            }
        }
    }
}
