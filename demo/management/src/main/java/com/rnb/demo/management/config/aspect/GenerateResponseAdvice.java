package com.rnb.demo.management.config.aspect;

import com.rnb.newbase.aspect.AbstractGenerateResponseAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import java.util.List;

@RestControllerAdvice
public class GenerateResponseAdvice extends AbstractGenerateResponseAdvice {

    @Resource
    private List<String> excludeResponseUri;

    public List<String> getExcludeUris() {
        if (excludeUris == null) {
            super.setExcludeUris(excludeResponseUri);
        }
        return excludeUris;
    }
}
