package com.rnb.newbase.security.persistent.entity;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class SystemUser {
    private BigInteger id;
    private String username;
    private String secret;
    private Boolean secretExpired;
    private Boolean secretLocked;
    private Boolean enabled;
    private Date lastLoginTime;
    private List<SystemRole> roles;
    private List<SystemResource> resources;
    private Date createTime;
    private Date modifyTime;
}
