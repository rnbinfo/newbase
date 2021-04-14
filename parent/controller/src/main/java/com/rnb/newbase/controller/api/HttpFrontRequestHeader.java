package com.rnb.newbase.controller.api;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class HttpFrontRequestHeader implements HttpRequestHeader {
    // 前端请求参数
    @NotBlank
    private String deviceType; // 设备类型：APP，H5，WEB
    private String deviceId; // 设备编号
    private String deviceInfo; // 设备信息
    // 请求app信息
    private String appChannel; // 渠道
    private String appVersion; // app版本
    // 请求参数
    @NotBlank
    private String requestToken; // 防重放令牌
    private Date timestamp; // 请求终端时间
    private String version; // 调用方法版本
    @NotBlank
    private String nonce; // 安全随机串
    private String loginToken; // 用户会话令牌
    private String sign; // 签名

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getAppChannel() {
        return appChannel;
    }

    public void setAppChannel(String appChannel) {
        this.appChannel = appChannel;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
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
        return "HttpFrontRequestHeader{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceInfo='" + deviceInfo + '\'' +
                ", appChannel='" + appChannel + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", requestToken='" + requestToken + '\'' +
                ", timestamp=" + timestamp +
                ", version='" + version + '\'' +
                ", nonce='" + nonce + '\'' +
                ", loginToken='" + loginToken + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
