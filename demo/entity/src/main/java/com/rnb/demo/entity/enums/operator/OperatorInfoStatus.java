package com.rnb.demo.entity.enums.operator;

public enum OperatorInfoStatus {
    NORMAL("正常"), FREEZE("冻结"), ERROR("错误");
    private String value;

    OperatorInfoStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
