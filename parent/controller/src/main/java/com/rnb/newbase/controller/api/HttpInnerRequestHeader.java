package com.rnb.newbase.controller.api;


import javax.validation.constraints.NotBlank;

public class HttpInnerRequestHeader implements HttpRequestHeader {
    @NotBlank
    private String requestSys; // 请求系统
    @NotBlank
    private String requestToken; // 请求防重令牌
    @NotBlank
    private String timestamp; // 请求时间
    @NotBlank
    private String version; // 方法版本
    @NotBlank
    private String nonce; // 安全随机串
    private String loginToken; // 用户会话令牌
    private String sign; // 签名

    public String getRequestSys() {
        return requestSys;
    }

    public void setRequestSys(String requestSys) {
        this.requestSys = requestSys;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "HttpInnerRequestHeader{" +
                "requestSys='" + requestSys + '\'' +
                ", requestToken='" + requestToken + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", version='" + version + '\'' +
                ", nonce='" + nonce + '\'' +
                ", loginToken='" + loginToken + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
