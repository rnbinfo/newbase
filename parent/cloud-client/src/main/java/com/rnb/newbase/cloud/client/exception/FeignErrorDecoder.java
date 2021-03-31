package com.rnb.newbase.cloud.client.exception;

import com.alibaba.fastjson.JSON;
import com.rnb.newbase.exception.RnbInternalException;
import com.rnb.newbase.exception.RnbInternalExceptionEntity;
import com.rnb.newbase.exception.RnbRuntimeException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
public class FeignErrorDecoder implements ErrorDecoder {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${app.isGateway: false}")
    private Boolean isGateway;

    @Override
    public Exception decode(String s, Response response) {
        RnbInternalExceptionEntity exceptionEntity = null;
        if (response.status() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            String errorContent;
            try {
                errorContent = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
                exceptionEntity = JSON.parseObject(errorContent, RnbInternalExceptionEntity.class);
            } catch (IOException e) {
                logger.error("Decoder internal exception FAILED");
                logger.error("Exception => ", e);
                if (isGateway) {
                    return new RnbRuntimeException("999500", "unknown.error");
                } else {
                    return new RnbInternalException("999500", "unknown.error");
                }
            }
        }
        if (isGateway) {
            return new RnbRuntimeException(exceptionEntity.getErrorCode(), exceptionEntity.getErrorMessage(), exceptionEntity.getParameters());
        } else {
            return new RnbInternalException(exceptionEntity.getErrorCode(), exceptionEntity.getErrorMessage(), exceptionEntity.getParameters());
        }
    }
}
