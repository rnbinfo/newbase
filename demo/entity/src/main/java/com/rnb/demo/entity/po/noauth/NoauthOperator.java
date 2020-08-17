package com.rnb.demo.entity.po.noauth;

import com.rnb.newbase.entity.AbstractEntity;
import lombok.Data;

import java.math.BigInteger;

@Data
public class NoauthOperator extends AbstractEntity {
    private String account;
    private String name;
    private BigInteger systemUserId;
    private String status;
}
