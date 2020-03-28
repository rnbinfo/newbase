package com.rnb.newbase.aspect;

import com.rnb.newbase.controller.api.HttpResponse;
import com.rnb.newbase.controller.api.HttpResponseHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

@RestControllerAdvice
public class GenerateResponseAdvice implements ResponseBodyAdvice<Object> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if ((body instanceof Map && ((Map) body).containsKey("status") && ((Map) body).get("status").equals(500))
                || body instanceof HttpResponse) {
            return body;
        } else {
            HttpResponseHeader responseHeader = new HttpResponseHeader();
            responseHeader.setTranStatus("true");
            HttpResponse responseWithHeader = new HttpResponse();
            responseWithHeader.setHeader(responseHeader);
            if (body != null) {
                responseWithHeader.setBody(body);
            }
            logger.debug("Response body [{}]", responseWithHeader);
            return responseWithHeader;
        }
    }
}
