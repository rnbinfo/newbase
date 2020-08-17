package com.rnb.demo.entity.api.server.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class QueryValidParameterRequest {
    @ApiModelProperty(value = "参数类型列表", required = true)
    @NotNull
    private List<String> types;
}
