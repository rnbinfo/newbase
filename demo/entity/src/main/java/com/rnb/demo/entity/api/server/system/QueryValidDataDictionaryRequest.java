package com.rnb.demo.entity.api.server.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class QueryValidDataDictionaryRequest {
    @ApiModelProperty(value = "数据字典类型列表", required = true)
    @NotNull
    private List<String> types;
}
