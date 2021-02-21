package com.rnb.newbase.persistence.constant;

public enum SortType {
    ASC("ASC"), DESC("DESC");

    private String value;

    SortType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
