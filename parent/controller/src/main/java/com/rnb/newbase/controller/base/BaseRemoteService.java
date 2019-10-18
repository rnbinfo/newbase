package com.rnb.newbase.controller.base;

import com.rnb.newbase.toolkit.http.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public abstract class BaseRemoteService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    protected String serverHost;
    protected String serverPort;
    protected String serverContext;

    protected String doJsonPost(String requestUri, String request) {
        String url = generateUri(requestUri);
        return HttpClientUtil.doJsonPost(url, request);
    }

    protected String doParamGet(String requestUri, String param) {
        String url = generateUri(requestUri);
        String urlWithParam = url + "?" + param;
        return HttpClientUtil.getContentForGet(urlWithParam, 3000);
    }

    protected String generateUri(String requestUri) {
        String url = new StringBuffer("http://").append(serverHost).append(":").append(serverPort)
                .append("/").append(serverContext).append(requestUri).toString();
        return url;
    }

    protected String doFormPost(String requestUri, Map<String, String> formParameters, int timeout) throws Exception{
        return HttpClientUtil.doFormPost(requestUri, formParameters, timeout);
    }

}
