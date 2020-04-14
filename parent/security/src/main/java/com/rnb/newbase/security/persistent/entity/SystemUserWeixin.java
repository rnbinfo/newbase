package com.rnb.newbase.security.persistent.entity;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class SystemUserWeixin {
    private BigInteger id;
    private BigInteger userId;
    private String openId;
    private String accessToken;
    private String refreshToken;
    private Date createTime;
    private Date modifyTime;
}
