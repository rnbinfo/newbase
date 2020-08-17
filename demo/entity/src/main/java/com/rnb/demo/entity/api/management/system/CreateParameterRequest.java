package com.rnb.demo.entity.api.management.system;

import com.rnb.demo.entity.constants.DataDictionaryType;
import com.rnb.demo.entity.constants.ParameterType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateParameterRequest {
    @ApiModelProperty(value = "参数类型", required = true)
    @NotBlank
    private ParameterType type;

    @ApiModelProperty(value = "参数代码", required = true)
    @NotBlank
    private String code;

    @ApiModelProperty(value = "参数类型描述", required = true)
    @NotBlank
    private String description;

    @ApiModelProperty(value = "参数值", required = true)
    @NotBlank
    private String key;

    @ApiModelProperty(value = "参数意义", required = true)
    @NotBlank
    private String value;
}
