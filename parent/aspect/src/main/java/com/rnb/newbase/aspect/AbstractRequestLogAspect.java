package com.rnb.newbase.aspect;

import com.alibaba.fastjson.JSON;
import com.rnb.newbase.controller.api.HttpRequest;
import com.rnb.newbase.controller.api.HttpResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractRequestLogAspect {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract void webLog();

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 从接口中获取用户loginToken
        if ("POST".equals(request.getMethod())) {
            // 获取参数, 只取自定义的参数, 自带的HttpServletRequest, HttpServletResponse不管
            if (joinPoint.getArgs().length > 0) {
                for (Object argObject : joinPoint.getArgs()) {
                    if (argObject instanceof HttpRequest) {
                        logger.info("Request for [{}] content [{}]", request.getRequestURI(), JSON.toJSONString(argObject));
                    }
                }
            }
        }
    }

    @AfterReturning("webLog()")
    public void afterReturning(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if (joinPoint.getArgs().length > 0) {
            for (Object argObject : joinPoint.getArgs()) {
                if (argObject instanceof HttpResponse) {
                    logger.debug("Response for request[{}] content [{}]", request.getRequestURI(), JSON.toJSONString(argObject));
                }
            }
        }
    }
}
