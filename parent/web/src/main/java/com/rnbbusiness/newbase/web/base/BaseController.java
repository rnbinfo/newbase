package com.rnbbusiness.newbase.web.base;

import com.rnbbusiness.newbase.exception.NewbaseExceptionConstants;
import com.rnbbusiness.newbase.exception.RnbbusinessRuntimeException;
import com.rnbbusiness.newbase.web.api.*;
import com.rnbbusiness.newbase.web.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    protected HttpResponse generateSuccessHttpResponse(Object responseBody) {
        HttpResponseHeader responseHeader = new HttpResponseHeader();
        responseHeader.setTranStatus("true");
        HttpResponse response = new HttpResponse();
        response.setHeader(responseHeader);
        response.setBody(responseBody);
        logger.debug("Response[{}]", response);
        return response;
    }

    protected void checkInnerHttpRequestHeader(HttpInnerRequest request) {
        this.checkHttpRequestHeader(request, Constants.RequestSource.INNER.getValue());
    }

    private void checkHttpRequestHeader(HttpRequest request, String requestSource) {
        if (request != null && request.getHeader() != null) {
            if (Constants.RequestSource.INNER.getValue().equals(requestSource)) {
                if (!(request.getHeader() instanceof HttpInnerRequestHeader)) {
                    throw new RnbbusinessRuntimeException(NewbaseExceptionConstants.HTTP_REQUEST_NOT_INEER_HEADER);
                }
            } else if (Constants.RequestSource.OUTER.getValue().equals(requestSource)) {
                if (!(request.getHeader() instanceof HttpOuterRequestHeader)) {
                    throw new RnbbusinessRuntimeException(NewbaseExceptionConstants.HTTP_REQUEST_NOT_OUTER_HEADER);
                }
            }
        } else {
            throw new RnbbusinessRuntimeException(NewbaseExceptionConstants.HTTP_REQUEST_NULL);
        }
    }
}
