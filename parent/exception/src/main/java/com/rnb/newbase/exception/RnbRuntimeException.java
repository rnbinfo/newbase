package com.rnb.newbase.exception;

public class RnbRuntimeException extends RuntimeException {
    private IRnbExceptionInfo exceptionInfo;
    private Object[] parameters;

    public RnbRuntimeException() {
        super();
    }

    public RnbRuntimeException(IRnbExceptionInfo exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public RnbRuntimeException(IRnbExceptionInfo exceptionInfo, Object[] parameters) {
        this.exceptionInfo = exceptionInfo;
        this.parameters = parameters;
    }

    public RnbRuntimeException(String errorCode, String errorMessage) {
        RnbExceptionInfo rnbExceptionInfo = new RnbExceptionInfo();
        rnbExceptionInfo.setErrorCode(errorCode);
        rnbExceptionInfo.setErrorMessage(errorMessage);
        this.exceptionInfo = rnbExceptionInfo;
    }

    public RnbRuntimeException(String errorCode, String errorMessage, Object[] parameters) {
        RnbExceptionInfo rnbExceptionInfo = new RnbExceptionInfo();
        rnbExceptionInfo.setErrorCode(errorCode);
        rnbExceptionInfo.setErrorMessage(errorMessage);
        this.exceptionInfo = rnbExceptionInfo;
        this.parameters = parameters;
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

    public Object[] getParameters() {
        return parameters;
    }
}
