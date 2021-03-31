package com.rnb.newbase.exception;

public class RnbInternalException extends RuntimeException {
    private IRnbExceptionInfo exceptionInfo;
    private Object[] parameters;

    public RnbInternalException() {
        super();
    }

    public RnbInternalException(IRnbExceptionInfo exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public RnbInternalException(IRnbExceptionInfo exceptionInfo, Object[] parameters) {
        this.exceptionInfo = exceptionInfo;
        this.parameters = parameters;
    }

    public RnbInternalException(String errorCode, String errorMessage) {
        RnbExceptionInfo rnbExceptionInfo = new RnbExceptionInfo();
        rnbExceptionInfo.setErrorCode(errorCode);
        rnbExceptionInfo.setErrorMessage(errorMessage);
        this.exceptionInfo = rnbExceptionInfo;
    }

    public RnbInternalException(String errorCode, String errorMessage, Object[] parameters) {
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
