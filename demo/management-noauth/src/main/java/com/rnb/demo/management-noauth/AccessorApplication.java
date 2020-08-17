package com.rnb.demo.accessor;

import com.rnb.newbase.security.support.RnbSecurityWebMvcConfigurationSupport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan({"com.rnb.energy", "com.rnb.newbase"})
public class AccessorApplication extends RnbSecurityWebMvcConfigurationSupport {
    public static void main(String[] args) {
        SpringApplication.run(AccessorApplication.class, args);
    }
}
