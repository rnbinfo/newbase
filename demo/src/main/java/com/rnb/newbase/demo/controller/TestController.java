package com.rnb.newbase.demo.controller;

import com.rnb.newbase.exception.RnbRuntimeException;
import com.rnb.newbase.toolkit.security.AES256;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/securityAES")
    public String doAES256() {
        String content = "I Love Shanghai!";
        String passwd = "sahgu4v25hjvg8hom";
        byte[] encrypt = AES256.encrypt(content, passwd);
        String decrypt = AES256.decrypt(encrypt, passwd);
        return decrypt;
    }

    @RequestMapping("/getException")
    public void getException() {
        throw new RnbRuntimeException("10003", "this is an demo exception");
    }
}
