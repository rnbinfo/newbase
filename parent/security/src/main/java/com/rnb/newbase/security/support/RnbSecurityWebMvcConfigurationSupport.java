package com.rnb.newbase.security.support;

import com.rnb.newbase.security.resolver.GetUserResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.List;

public abstract class RnbSecurityWebMvcConfigurationSupport extends WebMvcConfigurationSupport {
    @Resource
    private GetUserResolver getUserResolver;

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        //注册@GetUser注解的实现类
        argumentResolvers.add(getUserResolver);
    }
}
