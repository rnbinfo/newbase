package com.rnb.newbase.toolkit.security;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

public class AES256ECBPKCS7 {
    private static final String KEY_ALGORITHM = "AES";
    private static final String CRYPT_ALGORITHM = "AES/ECB/PKCS7Padding";
    private static final int KEY_SIZE = 256;
    private static final String CHARSET = "UTF-8";

    public static String encrypt(String content, String key) throws Exception {
        try {
            Security.addProvider(new BouncyCastleProvider());
            byte[] raw = key.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] data = cipher.doFinal(content.getBytes(CHARSET));
            String result = Base64.encodeBase64String(data);
            return result;
        } catch (Exception e) {
            throw new Exception("AES加密失败：" + e.getMessage(), e);
        }
    }

    public static String decrypt(String content, String key) throws Exception {
        try {
            Security.addProvider(new BouncyCastleProvider());
            byte[] raw = key.getBytes(CHARSET);
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] encrypted1 = Base64.decodeBase64(content);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, CHARSET);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception e) {
            throw new Exception("AES解密失败：" + e.getMessage(), e);
        }
    }
}

