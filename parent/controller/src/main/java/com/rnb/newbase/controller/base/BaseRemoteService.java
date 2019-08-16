package com.rnb.newbase.controller.base;

import com.rnb.newbase.toolkit.http.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseRemoteService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    protected String serverHost;
    protected String serverPort;
    protected String serverContext;

    protected String doJsonPost(String requestUri, String request) {
        String url = new StringBuffer("http://").append(serverHost).append(":").append(serverPort)
                .append("/").append(serverContext).append(requestUri).toString();
        return HttpClientUtil.doJsonPost(url, request);
    }

}
