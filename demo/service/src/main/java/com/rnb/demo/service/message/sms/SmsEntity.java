package com.rnb.demo.service.message.sms;

import lombok.Data;

@Data
public class SmsEntity {
    private String[] mobiles;
    private String action;
    private String[] contents;
}
