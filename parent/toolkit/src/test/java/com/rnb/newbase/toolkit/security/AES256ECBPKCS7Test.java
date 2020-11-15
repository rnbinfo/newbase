package com.rnb.newbase.toolkit.security;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AES256ECBPKCS7Test {
    @Test
    public void testAES256() throws Exception{
        Map<String, String> value = new HashMap<>();
        value.put("title", "这是被解密的明文");
        String content = JSON.toJSONString(value);
        //String content = "{\"title\": \"这是被解密的明文\"}";
        String password = "RXPV95+YEuWwh4piDzaUSP445+BonBfE";
        System.out.println("明文：" + content);
        System.out.println("key：" + password);
        String encryptResult = AES256ECBPKCS7.encrypt(content, password);
        System.out.println("密文：" + encryptResult);
        String decryptResult = AES256ECBPKCS7.decrypt(encryptResult, password);
        System.out.println("解密：" + decryptResult);
        Assert.assertEquals(content, decryptResult);
    }
}
