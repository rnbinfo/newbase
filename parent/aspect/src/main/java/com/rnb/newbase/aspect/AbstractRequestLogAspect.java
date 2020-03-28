package com.rnb.newbase.aspect;

import com.alibaba.fastjson.JSON;
import com.rnb.newbase.controller.api.HttpRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                    if (argObject instanceof HttpServletRequest || argObject instanceof HttpServletResponse) {
                        continue;
                    }
                    if (argObject instanceof HttpRequest) {
                        logger.debug("Request for [{}] content [{}]", request.getRequestURI(), JSON.toJSONString(argObject));
                    }
                }
            }
        }
    }
}
