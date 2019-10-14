package com.rnb.newbase.toolkit.security;


import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

public class MD5Sign {
    /**
     * 将字符串MD5加码 生成32位md5码
     */
    public static String encodeAsMd5(String inStr,  String charset) {
        try {
            return DigestUtils.md5Hex(inStr.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("Md5 signature failed");
        }
    }

    /**
     * 验证签名
     */
    public static boolean verify(String plainText, String sign, String charset) throws Exception {
        if (sign == null || "".equals(sign.trim())) {
            throw new Exception("Md5 signature is null or blank");
        }
        String mysign = encodeAsMd5(plainText, charset);
        if (mysign.equals(sign)) {
            return true;
        } else {
            return false;
        }

    }

}
