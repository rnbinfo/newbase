package com.rnb.demo.entity.enums.user;

public enum UserStatus {
    NORMAL("正常"), FROZEN("冻结"), CLOSED("销户");
    private String value;

    UserStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
