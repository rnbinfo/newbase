package com.rnb.demo.management;

import com.rnb.newbase.security.support.RnbSecurityWebMvcConfigurationSupport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan({"com.rnb.energy", "com.rnb.newbase"})
public class ManagementApplication extends RnbSecurityWebMvcConfigurationSupport {
    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class, args);
    }
}
