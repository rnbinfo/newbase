package com.rnb.demo.entity.po.operator;

import java.math.BigInteger;
import java.util.Date;

import com.rnb.newbase.entity.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 运营操作员信息
 */
@Data
@NoArgsConstructor
public class OperatorInfo extends AbstractEntity {
    /**
     * 登录账号
     */
    private String account;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 登录手机号
     */
    private String mobile;

    /**
     * 运营邮箱
     */
    private String email;

    /**
     * 状态
     */
    private String status;

    /**
     * 关联系统用户id
     */
    private BigInteger systemUserId;
}