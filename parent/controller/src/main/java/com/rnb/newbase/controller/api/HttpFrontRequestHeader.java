package com.rnb.newbase.controller.api;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class HttpFrontRequestHeader implements HttpRequestHeader {
    @NotBlank
    private String deviceId; // 设备编号
    @NotBlank
    private String deviceType; // 设备类型
    private String deviceBrand; // 设备型号
    private String requestNo; // 终端请求流水号(防重)
    private Date timestamp; // 请求终端时间
    @NotBlank
    private String token; // 防重放令牌
    @NotBlank
    private String appChannel; // 渠道
    @NotBlank
    private String appVersion; // app版本
    private String nonce; // 安全随机串
    private String sign; // 签名
}
