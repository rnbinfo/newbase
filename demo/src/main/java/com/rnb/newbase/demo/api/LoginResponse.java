package com.rnb.newbase.demo.api;

import lombok.Data;

@Data
public class LoginResponse {
    private String loginToken;

    public static LoginResponse generateLoginResponse(String loginToken) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setLoginToken(loginToken);
        return loginResponse;
    }
}
