package com.rnb.demo.entity.po.system;

import java.math.BigInteger;
import java.util.Date;

import com.rnb.newbase.entity.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统操作日志
 */
@Data
@NoArgsConstructor
public class SystemOperateLog extends AbstractEntity {
    /**
     * 操作员id
     */
    private BigInteger operatorId;

    /**
     * 请求uri
     */
    private String uri;

    /**
     * 请求内容
     */
    private String request;

    /**
     * 返回内容
     */
    private String response;
}