package com.rnb.newbase.cloud.client.exception;

import com.rnb.newbase.exception.RnbInternalException;
import com.rnb.newbase.exception.RnbInternalExceptionEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RnbInternalExceptionHandler {

    @ExceptionHandler(RnbInternalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)   // 指定http状态码为500时，feign不会抛出异常
    public RnbInternalExceptionEntity rnbInternalExceptionHandler(RnbInternalException internalException) {
        RnbInternalExceptionEntity exceptionEntity = RnbInternalExceptionEntity.generateEntity(internalException.getErrorCode(),
                internalException.getErrorMessage(), internalException.getParameters());
        return exceptionEntity;
    }
}
