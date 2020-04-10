package com.rnb.newbase.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.rnb.newbase.controller.api.*;
import com.rnb.newbase.exception.NewbaseExceptionConstants;
import com.rnb.newbase.exception.RnbRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    /**
     * 将对象转换成map
     * @param conditionObject
     * @return
     */
    protected Map<String, Object> convertConditionToMap(Object conditionObject) {
        String conditionString = JSONObject.toJSONString(conditionObject);
        Map<String, Object> condition = JSONObject.parseObject(conditionString, new TypeReference<Map<String, Object>>(){});
        return condition;
    }

    /**
     * 获取客户端
     * @param request
     * @return
     */
    public String getCliectIp(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ip.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ip = str;
                break;
            }
        }
        return ip;
    }
}
