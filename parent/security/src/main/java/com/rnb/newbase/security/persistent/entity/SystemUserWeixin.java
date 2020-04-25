package com.rnb.newbase.security.persistent.entity;

import com.rnb.newbase.entity.AbstractEntity;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class SystemUserWeixin extends AbstractEntity {
    private BigInteger userId;
    private String openId;
    private String accessToken;
    private String refreshToken;
}
