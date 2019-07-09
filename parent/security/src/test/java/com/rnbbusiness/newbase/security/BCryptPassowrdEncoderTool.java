package com.rnbbusiness.newbase.security;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;

public class BCryptPassowrdEncoderTool {

    public static void main(String[] args) throws Exception {
        //生成密码 10位，包含大小字母，数字，符号
        BCryptPassowrdEncoderTool tool = new BCryptPassowrdEncoderTool();
        String password = tool.getRandomString(10);
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        System.out.println("generate password [" + password + "] encrpty as[" + encoder.encode(password) + "]");
    }

    public String getRandomString(int length) { //length表示生成字符串的长度
        String base = "ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijklmnpqrstuvwxyz0123456789{}[]()-_+=|!@#$%^&*,.<>?";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
