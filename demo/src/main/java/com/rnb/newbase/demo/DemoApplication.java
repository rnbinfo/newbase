package com.rnb.newbase.demo;

import com.rnb.newbase.security.resolver.GetUserResolver;
import com.rnb.newbase.security.support.RnbSecurityWebMvcConfigurationSupport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rnb.newbase"})
public class DemoApplication extends RnbSecurityWebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

