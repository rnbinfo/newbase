package com.rnb.newbase.aspect;

import com.alibaba.fastjson.JSON;
import com.rnb.newbase.controller.api.HttpRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                        logger.info("Request for [{}] content [{}]", request.getRequestURI(), passwordMask(JSON.toJSONString(argObject)));
                    }
                }
            }
        }
    }

    @AfterReturning(value = "webLog()", returning = "result")
    public void afterReturing(JoinPoint joinPoint, Object result) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("Response for request[{}] content [{}]", request.getRequestURI(), passwordMask(JSON.toJSONString(result)));
    }

    private String passwordMask(String requestString) {
        String pattern = "\"password\":\"(.*?)\"";
        StringBuffer operatorStr = new StringBuffer(requestString);
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(requestString);
        if (m.find()) {
            //替换第一次出现的password
            operatorStr.replace(m.start(0), m.end(0), "\"password\":\"******\"");
        }
        return operatorStr.toString();
    }
}
