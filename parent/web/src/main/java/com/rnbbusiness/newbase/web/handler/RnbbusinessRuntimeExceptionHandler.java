package com.rnbbusiness.newbase.web.handler;

import com.rnbbusiness.newbase.exception.RnbbusinessRuntimeException;
import com.rnbbusiness.newbase.web.api.HttpResponse;
import com.rnbbusiness.newbase.web.api.HttpResponseHeader;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Locale;

@ControllerAdvice
public class RnbbusinessRuntimeExceptionHandler {
    @Resource
    private MessageSource messageSource;

    @ExceptionHandler(value = RnbbusinessRuntimeException.class)
    @ResponseBody
    public HttpResponse<String> defaultRestExceptionHandler(RnbbusinessRuntimeException e, Locale locale) {
        // 国际化处理error message
        String errorMessage;
        try {
            errorMessage = messageSource.getMessage(e.getErrorMessage(), null, locale);
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