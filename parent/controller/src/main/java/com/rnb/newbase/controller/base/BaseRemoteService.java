package com.rnb.newbase.controller.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.rnb.newbase.controller.api.HttpFrontRequest;
import com.rnb.newbase.controller.api.HttpFrontRequestHeader;
import com.rnb.newbase.controller.api.HttpPaginationRepertory;
import com.rnb.newbase.controller.api.HttpResponse;
import com.rnb.newbase.exception.NewbaseExceptionConstants;
import com.rnb.newbase.exception.RnbRuntimeException;
import com.rnb.newbase.toolkit.http.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

public abstract class BaseRemoteService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public abstract BaseRemoteServerProperties getRemoteServerProperties();

    protected String doJsonPost(String requestUri, String request, int timeout) throws Exception {
        String url = generateUri(requestUri);
        return HttpClientUtil.doJsonPost(url, request, timeout);
    }

    protected String generateUri(String requestUri) {
        String url = new StringBuffer(getRemoteServerProperties().getProtocol()).append("://").append(getRemoteServerProperties().getHost()).append(":").append(getRemoteServerProperties().getPort())
                .append("/").append(getRemoteServerProperties().getContext()).append(requestUri).toString();
        return url;
    }

    protected String doFormPost(String requestUri, Map<String, String> formParameters, int timeout) throws Exception {
        //TODO 待实现 form-data
        return null;
    }

    protected String doFormUrlEncodedPost(String requestUri, Map<String, String> formParameters, int timeout) throws Exception {
        String url = generateUri(requestUri);
        return HttpClientUtil.doFormUrlEncodedPost(url, formParameters, timeout);
    }

    protected HttpFrontRequestHeader generateHeader(String sign) {
        return new HttpFrontRequestHeader() {{
            this.setAppChannel("");
            this.setAppVersion("");
            this.setDeviceInfo("");
            this.setDeviceId("");
            this.setDeviceType("");
            this.setLoginToken("");
            this.setNonce("");
            this.setRequestToken("");
            this.setTimestamp(new Date());
            this.setVersion("1.0");
            this.setSign(sign);
        }};
    }

    protected void checkResponse(HttpResponse<? extends Object> response) {
        if (response == null) {
            throw new RnbRuntimeException(NewbaseExceptionConstants.HTTP_INNER_REQUEST_EXCEPTION);
        }
        if (StringUtils.equals(
                response.getHeader().getTranStatus(),
                "true")
        ) {
            return;
        }
        throw new RnbRuntimeException(response.getHeader().getErrorCode(), response.getHeader().getErrorMessage());
    }

    protected <T> T sendInnerRequest(String url, Object req, int timeout, Class<T> clazz) {
        try {
            String jsonString = JSONObject.toJSONString(
                    new HttpFrontRequest<Object>() {{
                        this.setBody(req);
                        this.setHeader(generateHeader(""));
                    }}
            );
            logger.debug("请求参数={}", JSONObject.toJSONString(req));
            String result = this.doJsonPost(url, jsonString, timeout);
            HttpResponse<T> rsp = JSON.parseObject(result, new TypeReference<HttpResponse<T>>(clazz) {
            });
            logger.debug("响应参数={}", rsp.toString());
            checkResponse(rsp);
            return rsp.getBody();
        } catch (RnbRuntimeException e) {
            throw new RnbRuntimeException(e.getErrorCode(), e.getErrorMessage());
        } catch (Exception e) {
            throw new RnbRuntimeException(NewbaseExceptionConstants.HTTP_INNER_REQUEST_EXCEPTION);
        }
    }

    protected <T> HttpPaginationRepertory<T> sendPaginationInnerRequest(
            String url,
            Object req,
            int timeout,
            Class<T> clazz) {
        try {
            String jsonString = JSONObject.toJSONString(
                    new HttpFrontRequest<Object>() {{
                        this.setBody(req);
                        this.setHeader(generateHeader(""));
                    }}
            );
            logger.debug("请求参数={}", JSONObject.toJSONString(req));
            String result = this.doJsonPost(url, jsonString, timeout);
            HttpResponse<HttpPaginationRepertory<T>> rsp = JSON.parseObject(result, new TypeReference<HttpResponse<HttpPaginationRepertory<T>>>(clazz) {
            });
            logger.debug("响应参数={}", rsp.toString());
            checkResponse(rsp);
            return rsp.getBody();
        } catch (RnbRuntimeException e) {
            throw new RnbRuntimeException(e.getErrorCode(), e.getErrorMessage());
        } catch (Exception e) {
            throw new RnbRuntimeException(NewbaseExceptionConstants.HTTP_INNER_REQUEST_EXCEPTION);
        }
    }

}
