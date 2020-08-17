package com.rnb.demo.entity.po.system;

import com.rnb.demo.entity.constants.ParameterType;
import com.rnb.newbase.entity.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统参数表
 */
@Data
@NoArgsConstructor
public class SystemParameter extends AbstractEntity {
    /**
     * 参数分类
     */
    private ParameterType type;

    /**
     * 参数代码
     */
    private String code;

    /**
     * 参数类型描述
     */
    private String description;

    /**
     * 参数值
     */
    private String key;

    /**
     * 参数值意义
     */
    private String value;
}