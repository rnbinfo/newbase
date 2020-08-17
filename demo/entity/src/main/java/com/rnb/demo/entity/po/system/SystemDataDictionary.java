package com.rnb.demo.entity.po.system;

import com.rnb.demo.entity.constants.CommonStatus;
import com.rnb.demo.entity.constants.DataDictionaryType;
import com.rnb.newbase.entity.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统数据字典表
 */
@Data
@NoArgsConstructor
public class SystemDataDictionary extends AbstractEntity {
    /**
     * 参数类型
     */
    private DataDictionaryType type;

    /**
     * 参数类型描述
     */
    private String description;

    /**
     * 参数值
     */
    private String key;

    /**
     * 参数意义
     */
    private String value;

    /**
     * 是否系统使用
     */
    private Boolean system;

    /**
     * 状态
     */
    private CommonStatus status;
}