package com.rnb.newbase.entity;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public abstract class AbstractEntity {
    private BigInteger id;
    private Date createTime;
    private Date modifyTime;
}
