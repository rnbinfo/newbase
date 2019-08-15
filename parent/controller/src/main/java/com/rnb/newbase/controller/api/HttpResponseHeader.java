package com.rnb.newbase.controller.api;

import lombok.Data;

@Data
public class HttpResponseHeader {

    private String tranStatus;                          // 交易状态
    private String errorCode;                           // 错误码
    private String errorMessage;                        // 错误信息
    private String sign;                                // 签名数据

}
