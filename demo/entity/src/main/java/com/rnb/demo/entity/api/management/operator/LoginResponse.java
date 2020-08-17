package com.rnb.demo.entity.api.management.operator;

import com.rnb.demo.entity.po.operator.OperatorInfo;
import lombok.Data;

@Data
public class LoginResponse {
    private String loginToken;
    private OperatorInfo operatorInfo;

    public static LoginResponse generateResponse(String loginToken, OperatorInfo operatorInfo) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setLoginToken(loginToken);
        loginResponse.setOperatorInfo(operatorInfo);
        return loginResponse;
    }
}
