package com.rnb.demo.entity.api.management.operator;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @ApiModelProperty(value = "登录账号", required = true)
    @NotBlank
    private String account;
    @ApiModelProperty(value = "登录密码")
    @NotBlank
    private String password;
}
