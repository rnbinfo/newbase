package com.rnb.newbase.exception;

public class RnbExceptionInfo implements IRnbExceptionInfo {
    private String errorCode;
    private String errorMessage;

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
