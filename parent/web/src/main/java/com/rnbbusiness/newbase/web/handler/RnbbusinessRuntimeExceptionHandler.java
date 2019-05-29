package com.rnbbusiness.newbase.web.handler;

import com.rnbbusiness.newbase.exception.RnbbusinessRuntimeException;
import com.rnbbusiness.newbase.web.api.HttpResponse;
import com.rnbbusiness.newbase.web.api.HttpResponseHeader;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RnbbusinessRuntimeExceptionHandler {
    @ExceptionHandler(value = RnbbusinessRuntimeException.class)
    @ResponseBody
    public HttpResponse<String> defaultRestExceptionHandler(RnbbusinessRuntimeException e) {
        HttpResponse<String> response = new HttpResponse<>();
        HttpResponseHeader responseHeader = new HttpResponseHeader();
        responseHeader.setTranStatus("false");
        responseHeader.setErrorCode(e.getErrorCode());
        responseHeader.setErrorMessage(e.getErrorMessage());
        response.setHeader(responseHeader);
        e.printStackTrace();
        return response;
    }
}