package com.rnb.newbase.controller.base;

import com.rnb.newbase.controller.api.*;
import com.rnbbusiness.newbase.exception.NewbaseExceptionConstants;
import com.rnbbusiness.newbase.exception.RnbbusinessRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    protected HttpResponse generateSuccessHttpResponse(Object responseBody) {
        HttpResponseHeader responseHeader = new HttpResponseHeader();
        responseHeader.setTranStatus("true");
        HttpResponse response = new HttpResponse();
        response.setHeader(responseHeader);
        if (responseBody != null) {
            response.setBody(responseBody);
            logger.debug("Response[{}]", response);
        }
        return response;
    }

    protected void checkHttpRequestHeader(HttpRequest request) {
        if (request != null && request.getHeader() != null) {
            if (request instanceof HttpInnerRequest) {
                if (!(request.getHeader() instanceof HttpInnerRequestHeader)) {
                    throw new RnbbusinessRuntimeException(NewbaseExceptionConstants.HTTP_REQUEST_NOT_INNER_HEADER);
                }
            } else if (request instanceof HttpFrontRequest) {
                if (!(request.getHeader() instanceof HttpFrontRequestHeader)) {
                    throw new RnbbusinessRuntimeException(NewbaseExceptionConstants.HTTP_REQUEST_NOT_FRONT_HEADER);
                }
            }
        } else {
            throw new RnbbusinessRuntimeException(NewbaseExceptionConstants.HTTP_REQUEST_NULL);
        }
    }
}
