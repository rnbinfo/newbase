package com.rnb.demo.management.remote.server;

import com.alibaba.fastjson.JSONObject;
import com.rnb.newbase.controller.api.HttpResponse;
import com.rnb.newbase.controller.base.BaseRemoteServerProperties;
import com.rnb.newbase.toolkit.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SyncConfigService extends AbstractInnerRemoteService {
    @Resource
    private ServerProperties serverProperties;
    @Override
    public BaseRemoteServerProperties getRemoteServerProperties() {
        return serverProperties;
    }

    public Object sendRequest(String requestKey, Object requestBody) {
        String requestUri = serverProperties.getRequestUris().get(requestKey);
        String requestContent = generateInnerRequest(requestBody, requestUri);
        try {
            String responseContent = doJsonPost(requestUri, requestContent, 3000);
            HttpResponse response = JSONObject.parseObject(responseContent, HttpResponse.class);
            if ("true".equals(response.getHeader().getTranStatus()) && StringUtil.isBlank(response.getHeader().getErrorCode())) {
                return response.getBody();
            } else {
                logger.error("Request remote server FAILED! Response[{}]", requestContent);
            }
        } catch (Exception e) {
            logger.error("Request remote server FAILED! Exception => ", e);
        }
        return null;
    }
}
