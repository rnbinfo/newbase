package com.rnb.demo.entity.enums.noauth;

public enum NoauthOperatorStatus {
    NORMAL("正常"), FREEZE("冻结"), ERROR("错误");
    private String value;

    NoauthOperatorStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
