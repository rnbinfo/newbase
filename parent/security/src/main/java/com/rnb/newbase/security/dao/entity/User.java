package com.rnb.newbase.security.dao.entity;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class User {
    private BigInteger id;
    private String username;
    private String password;
    private List<String> roleCodes;
    private String token;
    private Date tokenExpired;
}
