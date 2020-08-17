package com.rnb.demo.entity.constants;

public enum ParameterType {
    //用户类 USER
    USER_DEFAULT_PARAMETER("100", "用户默认参数"),
    SYSTEM_RUNNING_PARAMETER("999", "系统运行参数");

    private String key;
    private String value;

    ParameterType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static ParameterType getByKey(String key) {
        for (ParameterType object : ParameterType.values()) {
            if (object.getKey().equals(key)) {
                return object;
            }
        }
        return null;
    }

    public static ParameterType getByValue(String name) {
        for (ParameterType object : ParameterType.values()) {
            if (object.name().equals(name)) {
                return object;
            }
        }
        return null;
    }
}
