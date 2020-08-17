package com.rnb.demo.entity.api.noauth.noauth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
public class LoginRequest {
    @ApiModelProperty(value = "接入系统id", required = true)
    @NotNull
    private BigInteger accessorId;
    @ApiModelProperty(value = "操作员几号", required = true)
    @NotBlank
    private String account;
    @ApiModelProperty(value = "操作员密码", required = true)
    @NotBlank
    private String password;
}
