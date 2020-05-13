package com.rnb.newbase.exception;

public enum NewbaseExceptionConstants implements IRnbExceptionInfo {
    HTTP_REQUEST_NULL("900001", "Http request check header failed: request or request header is null."),
    HTTP_REQUEST_NOT_INNER_HEADER("900002", "Http request check header failed: request header is not inner header."),
    HTTP_REQUEST_NOT_FRONT_HEADER("900003", "Http request check header failed: request header is not front header."),

    HTTP_INNER_REQUEST_EXCEPTION("901001", "http.inner.request.exception"),
    HTTP_INNER_REQUEST_NOT_EXISTED("901001", "http.inner.request.not.existed"),
    HTTP_INNER_REQUEST_SOURCE_NOT_ALLOWED("901001", "http.inner.request.source.not.allowed"),

    SECURITY_ROLE_NOT_ONLY_ONE("910001", "security.role.not.only.one"),
    SECURITY_RESOURCE_NOT_EXISTED("910002", "security.resource.not.existed"),
    SECURITY_RESOURCE_HAS_CHILD("910003", "security.resource.has.child"),
    SECURITY_USER_EXISTED("910004", "security.user.existed"),
    SECURITY_USER_NOT_EXISTED("910005", "security.not.user.existed"),
    SECURITY_ROLE_NOT_EXISTED("910006", "security.role.not.existed"),
    SECURITY_PASSWORD_NOT_MATCH("910007", "security.password.not.match");

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
