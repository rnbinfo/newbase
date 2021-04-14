package com.rnb.newbase.api;

public abstract class AbstractGatewayApi extends AbstractApi{
    /**
     * 来源真实ip
     */
    private String ip;
    /**
     * 设备类型：APP, H5, WEB
     */
    private String deviceType;
    /**
     * 设备id
     */
    private String deviceId;
    /**
     * 设备信息JSON
     * 包括：brand, model, os, osVersion
     */
    private String deviceInfo;
    /**
     * app渠道
     */
    private String appChannel;
    /**
     * app版本
     */
    private String appVersion; // app版本

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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
}
