package com.rnb.newbase.exception;

public class RnbRuntimeException extends RuntimeException {
    private IRnbExceptionInfo exceptionInfo;

    public RnbRuntimeException() {
        super();
    }

    public RnbRuntimeException(IRnbExceptionInfo exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public RnbRuntimeException(String errorCode, String errorMessage) {
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
