package com.rnb.newbase.demo.aspect;

import com.rnb.newbase.security.aspect.AbstractUserAuthorizeAspect;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAuthorizeAspect extends AbstractUserAuthorizeAspect {

    @Pointcut("execution(public * com.rnb.newbase.demo.controller..*.*(..))" +
            "&& !execution(* com.rnb.newbase.demo.controller.UserLoginController.*(..))")
    public void userAuthorize() {
    }
}
