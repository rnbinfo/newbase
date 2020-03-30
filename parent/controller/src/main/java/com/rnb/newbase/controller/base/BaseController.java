package com.rnb.newbase.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.rnb.newbase.controller.api.*;
import com.rnb.newbase.exception.NewbaseExceptionConstants;
import com.rnb.newbase.exception.RnbRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
