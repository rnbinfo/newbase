package com.rnb.newbase.toolkit.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * 调整指定时间
     */
    public static Date adjustTime(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }
}
