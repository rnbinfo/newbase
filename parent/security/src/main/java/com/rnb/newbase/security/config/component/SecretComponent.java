package com.rnb.newbase.security.config.component;

import com.rnb.newbase.toolkit.security.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SecretComponent {
    @Bean("bCryptPasswordEncoder")
    public BCryptPasswordEncoder generateBCryptEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}
