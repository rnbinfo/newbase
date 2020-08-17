package com.rnb.demo.entity.constants;

public enum DataDictionaryType {
    // 用户类 USER
    USER_STATUS("10001", "用户状态"),
    // 渠道方 CHANNEL
    CHANNEL_ENERGY("20001", "能源渠道"),
    CHANNEL_STATION_STATUS("21001", "站点状态"),
    CHANNEL_STATION_TYPE("21002", "站点类型"),
    // 基础数据类 BASE
    BASE_FUEL_TYPE("30001", "能源类型"),
    // 交易类 TRADE
    TRADE_ORDER_STATUS("40001", "交易订单状态"),
    // 接入类 ACCESSOR
    ACCESSOR_OPERATOR_STATUS("70001", "接入方操作员状态"),
    // 管理类 MANAGEMENT
    MANAGEMENT_OPERATOR_STATUS("80001", "操作员状态");
    // 通用类 COMMON

    private String key;
    private String value;

    DataDictionaryType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static DataDictionaryType getByKey(String key) {
        for (DataDictionaryType object : DataDictionaryType.values()) {
            if (object.getKey().equals(key)) {
                return object;
            }
        }
        return null;
    }

    public static DataDictionaryType getByValue(String name) {
        for (DataDictionaryType object : DataDictionaryType.values()) {
            if (object.name().equals(name)) {
                return object;
            }
        }
        return null;
    }
}
