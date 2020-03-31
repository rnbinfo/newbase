package com.rnb.newbase.security.aspect.config;

import lombok.Data;

@Data
public class RemoteClientConfig {
    private String sourceIp;
    private String privateKey;
}
