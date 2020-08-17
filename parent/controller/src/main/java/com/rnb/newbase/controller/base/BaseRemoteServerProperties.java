package com.rnb.newbase.controller.base;

import java.util.Map;

public abstract class BaseRemoteServerProperties {
    private String protocol;
    private String host;
    private String port;
    private String context;
    private Map<String, String> requestUris;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Map<String, String> getRequestUris() {
        return requestUris;
    }

    public void setRequestUris(Map<String, String> requestUris) {
        this.requestUris = requestUris;
    }
}
