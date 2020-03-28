package com.rnb.newbase.security.persistent.entity;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class SystemRole {
    private BigInteger id;
    private String name;
    private String description;
    private List<SystemResource> resources;
    private Date createTime;
    private Date modifyTime;
}
