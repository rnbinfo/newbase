package com.rnb.newbase.security.dao.entity;

import java.math.BigInteger;
import java.util.List;

public class Resource {
    private BigInteger id;
    private String url;
    private List<String> roleCodes;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(List<String> roleCodes) {
        this.roleCodes = roleCodes;
    }
}
