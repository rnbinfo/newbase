package com.rnb.newbase.entity;

import com.alibaba.fastjson.JSON;

import java.math.BigInteger;
import java.util.Date;

public abstract class AbstractEntity {
    protected BigInteger id;
    private Date createTime;
    private Date modifyTime;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " -> " + JSON.toJSONString(this);
    }
}
