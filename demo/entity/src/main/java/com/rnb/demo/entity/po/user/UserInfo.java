package com.rnb.demo.entity.po.user;

import com.rnb.newbase.entity.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * 用户信息表
 */
@Data
@NoArgsConstructor
public class UserInfo extends AbstractEntity {
    /**
     * 登录账号
     */
    private String account;

    /**
     * 昵称
     */
    private String nickname;
    
    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 状态,001-正常,090-冻结,099-销户
     */
    private String status;
}