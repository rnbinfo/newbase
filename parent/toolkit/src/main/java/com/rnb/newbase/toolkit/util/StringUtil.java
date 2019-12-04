package com.rnb.newbase.toolkit.util;

public class StringUtil {
    public static Boolean isBlank(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }
}
