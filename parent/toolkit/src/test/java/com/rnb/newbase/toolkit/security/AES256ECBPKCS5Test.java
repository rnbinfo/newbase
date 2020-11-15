package com.rnb.newbase.toolkit.security;

import org.junit.Test;


public class AES256ECBPKCS5Test {
    @Test
    public void testAES256() throws Exception{
        String content = "hello,您好";
        String key = "RXPV95+YEuWwh4piDzaUSP445+BonBfE";
        System.out.println("content:" + content);
        String s1 = AES256ECBPKCS5.encrypt(content, key);
        System.out.println("s1:" + s1);
        System.out.println("s2:" + AES256ECBPKCS5.decrypt(s1, key));
    }
}
