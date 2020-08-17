package com.rnb.demo.server.config.component;

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
        excludeResponseUri.add("/energy-server/docs.html");
        excludeResponseUri.add("/energy-server/swagger-ui.html");
        excludeResponseUri.add("/energy-server/v2/api-docs");
        excludeResponseUri.add("/energy-server/swagger-resources");
        excludeResponseUri.add("/energy-server/swagger-resources/");
        return excludeResponseUri;
    }
}
