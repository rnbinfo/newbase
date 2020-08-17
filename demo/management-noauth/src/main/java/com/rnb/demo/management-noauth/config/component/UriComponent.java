package com.rnb.demo.accessor.config.component;

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
        excludeResponseUri.add("/accessor/docs.html");
        excludeResponseUri.add("/accessor/swagger-ui.html");
        excludeResponseUri.add("/accessor/v2/api-docs");
        excludeResponseUri.add("/accessor/swagger-resources");
        excludeResponseUri.add("/accessor/swagger-resources/");
        return excludeResponseUri;
    }
}
