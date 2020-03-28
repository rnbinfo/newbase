package com.rnb.newbase.security.persistent.entity;

public enum SystemResourceType {
    FRONT_MENU("FM", "前台菜单"),  BACK_SERVICE("FS", "后台服务");

    private String key;
    private String value;

    SystemResourceType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static SystemResourceType getByKey(String key) {
        for (SystemResourceType systemResourceType : SystemResourceType.values()) {
            if (systemResourceType.getKey().equals(key)) {
                return systemResourceType;
            }
        }
        return null;
    }
}
