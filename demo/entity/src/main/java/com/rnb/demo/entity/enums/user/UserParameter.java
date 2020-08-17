package com.rnb.demo.entity.enums.user;

public enum UserParameter {
    DEFAULT_AVATAR("00001");
    private String value;

    UserParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
