package com.rnb.demo.entity.api.management.system;

import com.rnb.demo.entity.constants.DataDictionaryType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateDataDictionaryRequest {
    @ApiModelProperty(value = "参数类型", required = true)
    @NotBlank
    private DataDictionaryType type;

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
