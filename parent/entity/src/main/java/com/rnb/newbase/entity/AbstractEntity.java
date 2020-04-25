package com.rnb.newbase.entity;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public abstract class AbstractEntity {
    protected BigInteger id;
    private Date createTime;
    private Date modifyTime;
}
