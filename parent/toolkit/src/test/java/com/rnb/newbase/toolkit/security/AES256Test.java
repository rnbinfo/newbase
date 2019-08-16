package com.rnb.newbase.toolkit.security;

import org.junit.Assert;
import org.junit.Test;

public class AES256Test {
    @Test
    public void testAES256() throws Exception{
        String content = "0f607264fc6318a92b9e13c65db7cd3c";
        String password = "zsyysvyt543vgqfxq3f4";
        System.out.println("明文：" + content);
        System.out.println("key：" + password);
        byte[] encryptResult = AES256.encrypt(content, password);
        System.out.println("密文：" + AES256.parseByte2HexStr(encryptResult));
        String decryptResult = AES256.decrypt(encryptResult, password);
        System.out.println("解密：" + decryptResult);
        Assert.assertEquals(content, decryptResult);
    }
}
