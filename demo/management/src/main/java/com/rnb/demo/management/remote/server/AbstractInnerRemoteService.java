package com.rnb.demo.management.remote.server;

import com.alibaba.fastjson.JSON;
import com.rnb.newbase.controller.api.HttpInnerRequest;
import com.rnb.newbase.controller.api.HttpInnerRequestHeader;
import com.rnb.newbase.controller.base.BaseRemoteService;
import com.rnb.newbase.toolkit.util.RandomUtil;

public abstract class AbstractInnerRemoteService extends BaseRemoteService {
    /**
     * 生成内部服务请求
     * @param requestBody
     * @param requestUri
     * @return
     */
    protected String generateInnerRequest(Object requestBody, String requestUri) {
        HttpInnerRequestHeader header = new HttpInnerRequestHeader();
        header.setMethod(requestUri);
        header.setRequestSys("management");
        header.setRequestNo(String.valueOf(System.currentTimeMillis()));
        header.setNonce(RandomUtil.generateNoSymbleString(21));
        header.setVersion("1.0");
        header.setSign("aaa");
        header.setLoginToken(RandomUtil.generateAllString(32));
        HttpInnerRequest request = new HttpInnerRequest();
        request.setHeader(header);
        request.setBody(requestBody);
        return JSON.toJSONString(request);
    }
}
