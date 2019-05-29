package com.rnbbusiness.newbase.security;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptPassowrdEncoderTest {
    @Test
    public void passowrdEncoderTest() {
        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println("user password [user] as:" + bCryptPasswordEncoder.encode("user"));
        System.out.println("user password [admin] as:" + bCryptPasswordEncoder.encode("admin"));
    }
}
