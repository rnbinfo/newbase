package com.rnb.newbase.api;

import com.alibaba.fastjson.JSON;

public abstract class AbstractApi {
    @Override
    public String toString() {
        return this.getClass().getName() + " -> " + JSON.toJSONString(this);
    }
}
