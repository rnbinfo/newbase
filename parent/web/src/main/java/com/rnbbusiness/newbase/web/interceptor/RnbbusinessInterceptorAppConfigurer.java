package com.rnbbusiness.newbase.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class RnbbusinessInterceptorAppConfigurer extends WebMvcConfigurerAdapter {
    @Autowired
    private HttpHeaderInterceptor httpHeaderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpHeaderInterceptor);
    }
}
