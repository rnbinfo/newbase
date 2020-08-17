package com.rnb.demo.entity.api.management.system;

import com.rnb.demo.entity.constants.CommonStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

@Data
public class UpdateDataDictionaryRequest {
    @ApiModelProperty(value = "id", required = true)
    @NotBlank
    private BigInteger id;

    @ApiModelProperty(value = "参数意义", required = true)
    @NotBlank
    private String value;

    @ApiModelProperty(value = "状态", required = true)
    @NotBlank
    private CommonStatus status;
}
