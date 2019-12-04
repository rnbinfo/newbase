package com.rnb.newbase.controller.api;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class HttpInnerRequestHeader implements HttpRequestHeader {
    @NotBlank
    private String requestSys; // 请求系统
    @NotBlank
    private String requestNo; // 请求流水号
    private String timestamp; // 请求时间
    private String method; // 请求方法
    private String version; // 方法版本
    private String nonce; // 安全随机串
    private String loginToken; // 用户会话令牌
    private String sign; // 签名

}
