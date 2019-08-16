package com.rnb.newbase.toolkit.security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.Security;

public class AES256 {
    private static final String KEY_ALGORITHM = "AES";
    private static final String SECURE_RANDOM = "SHA1PRNG";
    private static final String CRYPT_ALGORITHM = "AES/ECB/PKCS7Padding";
    private static final int KEY_SIZE = 256;
    private static final String CHARSET = "UTF-8";
    private static final String CIPHER_PROVIDER = "BC";

    public static byte[] encrypt(String content, String password) {
        try {
            //"AES"：请求的密钥算法的标准名称
            KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
            //256：密钥生成参数；securerandom：密钥生成器的随机源
            SecureRandom securerandom = SecureRandom.getInstance(SECURE_RANDOM);
            kgen.init(KEY_SIZE, securerandom);
            //生成秘密（对称）密钥
            SecretKey secretKey = kgen.generateKey();
            //返回基本编码格式的密钥
            byte[] enCodeFormat = secretKey.getEncoded();
            //根据给定的字节数组构造一个密钥。enCodeFormat：密钥内容；"AES"：与给定的密钥内容相关联的密钥算法的名称
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);
            //将提供程序添加到下一个可用位置
            Security.addProvider(new BouncyCastleProvider());
            //创建一个实现指定转换的 Cipher对象，该转换由指定的提供程序提供。
            //"AES/ECB/PKCS7Padding"：转换的名称；"BC"：提供程序的名称
            Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM, CIPHER_PROVIDER);

            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] byteContent = content.getBytes(CHARSET);
            byte[] cryptograph = cipher.doFinal(byteContent);
            return Base64.encode(cryptograph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(byte[] cryptograph, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom securerandom = SecureRandom.getInstance(SECURE_RANDOM);
            kgen.init(KEY_SIZE, securerandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, KEY_ALGORITHM);
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM, CIPHER_PROVIDER);

            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] content = cipher.doFinal(Base64.decode(cryptograph));
            return new String(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

//    private static byte[] tohash256Deal(String datastr) {
//        try {
//            MessageDigest digester = MessageDigest.getInstance(MESSAGE_DIGEST);
//            digester.update(datastr.getBytes());
//            byte[] hex = digester.digest();
//            return hex;
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
}

