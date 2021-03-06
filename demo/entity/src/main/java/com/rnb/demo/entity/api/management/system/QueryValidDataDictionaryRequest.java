package com.rnb.demo.entity.api.management.system;

import com.rnb.demo.entity.constants.DataDictionaryType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class QueryValidDataDictionaryRequest {
    @ApiModelProperty(value = "类型列表", required = true)
    private List<DataDictionaryType> types;

}
