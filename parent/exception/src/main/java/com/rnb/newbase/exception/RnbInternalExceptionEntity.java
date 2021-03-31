package com.rnb.newbase.exception;

public class RnbInternalExceptionEntity {
    private String errorCode;
    private String errorMessage;
    private Object[] parameters;

    public static RnbInternalExceptionEntity generateEntity(String errorCode, String errorMessage, Object[] parameters) {
        RnbInternalExceptionEntity entity = new RnbInternalExceptionEntity();
        entity.setErrorCode(errorCode);
        entity.setErrorMessage(errorMessage);
        entity.setParameters(parameters);
        return entity;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Object[] getParameters() {
        return parameters;
    }
}
