package com.rnb.demo.accessor.config.aspect;

import com.rnb.newbase.security.aspect.AbstractUserAuthorizeAspect;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAuthorizeAspect extends AbstractUserAuthorizeAspect {

    @Pointcut("execution(public * com.rnb.demo.accessor.controller..*.*(..))" +
            "&& !execution(* com.rnb.energy.accessor.controller.accessor.LoginController.*(..))" +
            "&& !execution(* com.rnb.energy.accessor.controller.system.SyncSystemDataController.*(..))")
    public void userAuthorize() {
    }

    @Value("${app.auth.mode:block}")
    @Override
    public void setAuthMode(String authMode) {
        super.authMode = authMode;
    }
}
