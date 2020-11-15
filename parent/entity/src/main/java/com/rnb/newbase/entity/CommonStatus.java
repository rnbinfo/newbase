package com.rnb.newbase.entity;

public enum CommonStatus {
    NORMAL("001", "正常"),
    ABNORMAL("090", "异常"),
    ERROR("099", "错误");

    private String key;
    private String value;

    CommonStatus(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static CommonStatus getByKey(String key) {
        for (CommonStatus object : CommonStatus.values()) {
            if (object.getKey().equals(key)) {
                return object;
            }
        }
        return null;
    }

    public static CommonStatus getByValue(String name) {
        for (CommonStatus object : CommonStatus.values()) {
            if (object.name().equals(name)) {
                return object;
            }
        }
        return null;
    }
}
