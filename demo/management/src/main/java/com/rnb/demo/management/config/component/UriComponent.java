package com.rnb.demo.management.config.component;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UriComponent {
    @Bean("excludeResponseUri")
    public List<String> ExcludeResponseUri() {
        List<String> excludeResponseUri = new ArrayList();
        // swagger uri
        excludeResponseUri.add("/agency-management/docs.html");
        excludeResponseUri.add("/agency-management/swagger-ui.html");
        excludeResponseUri.add("/agency-management/v2/api-docs");
        excludeResponseUri.add("/agency-management/swagger-resources");
        excludeResponseUri.add("/agency-management/swagger-resources/");
        return excludeResponseUri;
    }
}
