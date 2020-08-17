package com.rnb.demo.entity.api.noauth.noauth;

import com.rnb.demo.entity.po.noauth.NoauthOperator;
import lombok.Data;

@Data
public class LoginResponse {
    private String loginToken;
    private NoauthOperator accessorOperator;

    public static LoginResponse generateResponse(String loginToken, NoauthOperator accessorOperator) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setLoginToken(loginToken);
        loginResponse.setAccessorOperator(accessorOperator);
        return loginResponse;
    }
}
