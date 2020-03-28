package com.rnb.newbase.security.persistent.entity;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class SystemResource {
    private BigInteger id;
    private String code;
    private String name;
    private Integer weight;
    private SystemResourceType type;
    private String url;
    private BigInteger parentId;
    private Boolean hasChild;
    private List<SystemResource> childSystemResources;
    private Date createTime;
    private Date modifyTime;

    @Override
    public boolean equals(Object object) {
        if (object instanceof SystemResource) {
            SystemResource systemResource = (SystemResource) object;
            if (systemResource.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
