package com.rnbbusiness.newbase.exception;

public class RnbbusinessRuntimeException extends RuntimeException {
    private String errorCode;
    private String errorMessage;
    private Throwable cause;

    public RnbbusinessRuntimeException() {
        super();
    }

    public RnbbusinessRuntimeException(String code, String message) {
        super(message);
        this.errorCode = code;
        this.errorMessage = message;
    }

    public RnbbusinessRuntimeException(Throwable cause) {
        super(cause);
        this.cause = cause;
    }

    public RnbbusinessRuntimeException(String code, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = code;
        this.errorMessage = message;
        this.cause = cause;
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
}
