package com.rnb.newbase.exception;

public class RnbbusinessRuntimeException extends RuntimeException {
    private IRnbExceptionInfo exceptionInfo;

    public RnbbusinessRuntimeException() {
        super();
    }

    public RnbbusinessRuntimeException(IRnbExceptionInfo exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public RnbbusinessRuntimeException(String errorCode, String errorMessage) {
        this.exceptionInfo = exceptionInfo;
    }

    public IRnbExceptionInfo getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(IRnbExceptionInfo exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public String getErrorCode() {
        return exceptionInfo.getErrorCode();
    }

    public String getErrorMessage() {
        return exceptionInfo.getErrorMessage();
    }
}
