package com.rnb.newbase.exception;

public enum NewbaseExceptionConstants implements IRnbExceptionInfo {
    HTTP_REQUEST_NULL("900001", "Http request check header failed: request or request header is null."),
    HTTP_REQUEST_NOT_INNER_HEADER("900002", "Http request check header failed: request header is not inner header."),
    HTTP_REQUEST_NOT_FRONT_HEADER("900003", "Http request check header failed: request header is not front header.");

    private String errorCode;
    private String errorMessage;

    NewbaseExceptionConstants(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
