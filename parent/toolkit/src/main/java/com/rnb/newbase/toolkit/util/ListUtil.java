package com.rnb.newbase.toolkit.util;

import java.util.List;

public class ListUtil {
    public static boolean isEmpty(List lists) {
        if (lists == null || lists.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(List lists) {
        if (lists == null || lists.size() == 0) {
            return false;
        }
        return true;
    }
}
