package com.rnbbusiness.newbase.web.api;

import lombok.Data;

@Data
public class HttpRequestInnerHeader implements HttpRequestHeader {

    private String requestSys;
    private String requestNo;
    private String timestamp;
    private String method;
    private String version;
    private String sign;

}
