package com.rnb.newbase.toolkit.security;

import org.junit.Assert;
import org.junit.Test;

import java.security.Key;
import java.util.Map;


public class SHA256WithRSATest {
    @Test
    public void testSHA256WithRsa() throws Exception{
        String charset = "UTF-8";
        Map<String, Key> genKeyPair = SHA256WithRSA.generateKeys();
        String base64publicKey = SHA256WithRSA.getPublicKey(genKeyPair);
        System.out.println("Public Key\n" + base64publicKey);
        String base64privateKey = SHA256WithRSA.getPrivateKey(genKeyPair);
        System.out.println("Private Key\n" + base64privateKey);
        String text = "amount=100&currency=VND&merOrderNo=20180830180718&merSubmitTime=2018-08-30 18:07:18&merchantId=123456&" +
                "method=wallet.preorder&pageBackUrl=www.baidu.com&productDesc=测试&productName=测试&remark=123&signType=RSA&" +
                "token=E9C03B459ECB476F872351C3B2820AC6&versionNo=1.0";
        String sign = SHA256WithRSA.sign(text, base64privateKey, charset);
        System.out.println("Sign\n" + sign);
        Assert.assertTrue(SHA256WithRSA.verify(text, sign, base64publicKey, charset));
    }
}
