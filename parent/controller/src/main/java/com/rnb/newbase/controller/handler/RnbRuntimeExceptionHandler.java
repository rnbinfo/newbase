package com.rnb.newbase.controller.handler;

import com.rnb.newbase.controller.api.HttpResponse;
import com.rnb.newbase.controller.api.HttpResponseHeader;
import com.rnb.newbase.exception.RnbRuntimeException;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Locale;

@ControllerAdvice
public class RnbRuntimeExceptionHandler {
    @Resource
    private MessageSource messageSource;

    @ExceptionHandler(value = RnbRuntimeException.class)
    @ResponseBody
    public HttpResponse<String> defaultRestExceptionHandler(RnbRuntimeException e, Locale locale) {
        // 国际化处理error message
        String errorMessage;
        try {
            errorMessage = messageSource.getMessage(e.getErrorMessage(), e.getParameters(), locale);
        } catch (Exception i18nException) {
            i18nException.printStackTrace();
            errorMessage = e.getErrorMessage();
        }
        HttpResponse<String> response = new HttpResponse<>();
        HttpResponseHeader responseHeader = new HttpResponseHeader();
        responseHeader.setTranStatus("false");
        responseHeader.setErrorCode(e.getErrorCode());
        responseHeader.setErrorMessage(errorMessage);
        response.setHeader(responseHeader);
        e.printStackTrace();
        return response;
    }
}