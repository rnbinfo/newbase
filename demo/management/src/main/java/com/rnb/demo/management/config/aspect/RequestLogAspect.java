package com.rnb.demo.management.config.aspect;

import com.rnb.newbase.aspect.AbstractRequestLogAspect;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestLogAspect extends AbstractRequestLogAspect {

    @Pointcut("execution(public * com.rnb.demo.management.controller..*.*(..))")
    public void webLog() {

    }
}
