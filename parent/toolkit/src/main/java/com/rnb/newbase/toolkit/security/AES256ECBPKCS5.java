package com.rnb.newbase.toolkit.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class AES256ECBPKCS5 {
    private static final String KEY_ALGORITHM = "AES";
    private static final String SECURE_RANDOM = "SHA1PRNG";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding"; //默认的加密算法
    private static final int KEY_SIZE = 256;
    private static final String CHARSET = "UTF-8";

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param key     加密密钥
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器
        byte[] byteContent = content.getBytes(CHARSET);
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));// 初始化为加密模式的密码器
        byte[] result = cipher.doFinal(byteContent);// 加密
        return Base64.encodeBase64String(result);//通过Base64转码返回
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) throws Exception {
        //实例化
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        //使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
        //执行操作
        byte[] result = cipher.doFinal(Base64.decodeBase64(content));
        return new String(result, CHARSET);
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String key) throws Exception {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;
        kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        SecureRandom secureRandom = SecureRandom.getInstance(SECURE_RANDOM) ;
        secureRandom.setSeed(key.getBytes(CHARSET));
        kg.init(KEY_SIZE, secureRandom);
        //生成一个密钥
        SecretKey secretKey = kg.generateKey();
        return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
    }
}
