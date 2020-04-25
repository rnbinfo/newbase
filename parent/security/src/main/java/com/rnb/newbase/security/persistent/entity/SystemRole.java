package com.rnb.newbase.security.persistent.entity;

import com.rnb.newbase.entity.AbstractEntity;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class SystemRole extends AbstractEntity {
    private String name;
    private String description;
    private List<SystemResource> resources;
}
