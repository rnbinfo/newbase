package com.rnb.newbase.security.persistent.entity;

import com.rnb.newbase.entity.AbstractEntity;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class SystemUser extends AbstractEntity {
    private String username;
    private String secret;
    private Boolean secretExpired;
    private Boolean secretLocked;
    private Boolean enabled;
    private Date lastLoginTime;
    private List<SystemRole> roles;
    private List<SystemResource> resources;
}
