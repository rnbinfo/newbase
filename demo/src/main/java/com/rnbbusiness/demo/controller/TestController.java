package com.rnbbusiness.demo.controller;

import com.business.newbase.toolkit.security.AES256;
import com.rnbbusiness.demo.api.TestHeadReqeust;
import com.rnbbusiness.demo.api.TestResponse;
import com.rnbbusiness.newbase.aspect.annotation.PrintLog;
import com.rnbbusiness.newbase.exception.RnbbusinessRuntimeException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        throw new RnbbusinessRuntimeException("10003", "this is an demo exception");
    }

    @RequestMapping("/testHead")
    public TestResponse testHeader(TestHeadReqeust request) {
        System.out.println("test header now~~~~~");
        TestResponse tr = new TestResponse();
        tr.setFunction("testHeader");
        tr.setResult("completed");
        return tr;
    }

    @PrintLog
    @PostMapping("testAop")
    public String testAop(@RequestBody TestAopReq request) {
        String value = request.getTarget() + " - " + request.getMessage();
        System.out.println("Controller value : " + value);
        return value;
    }
}
