package com.rnbbusiness.demo.config;

import com.rnbbusiness.newbase.security.config.RnbbusinessFilterSecurityMetadataSource;
import com.rnbbusiness.newbase.security.config.RnbbusinessSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.Arrays;

@Configuration
public class CustomSecurityConfig extends RnbbusinessSecurityConfig {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").defaultSuccessUrl("/home")
                .and()
                .exceptionHandling().accessDeniedPage("/error")
                .and()
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    public <O extends FilterSecurityInterceptor> O postProcess(
                            O fsi) {
                        fsi.setSecurityMetadataSource(customerMetadataSource());
                        fsi.setAccessDecisionManager(myAccessDecisionManager());
                        return fsi;
                    }
                });//设置后置处理程序对象
    }

    @Bean
    public RnbbusinessFilterSecurityMetadataSource customerMetadataSource() {
        RnbbusinessFilterSecurityMetadataSource metadataSource
                =
                new RnbbusinessFilterSecurityMetadataSource(Arrays.asList("/testHead", "/getException",
                        "/findTest2Users", "/testAop",
                        "/login", "/css/**", "/fonts/**", "/js/**", "/scss/**", "/img/**"));
        return metadataSource;
    }
}
