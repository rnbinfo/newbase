package com.rnbbusiness.newbase.web.base;

import com.business.newbase.toolkit.http.HttpClientUtil;
import com.rnbbusiness.newbase.web.api.HttpInnerRequest;
import com.rnbbusiness.newbase.web.api.HttpResponse;
import org.omg.CORBA.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
