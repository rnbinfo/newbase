package com.rnb.newbase.toolkit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final String DATETIME_COMPACT_FORMAT = "yyyyMMddHHmmss";
    public static final String DATE_COMPACT_FORMAT = "yyyyMMdd";
    public static final String DATETIME_ISO_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_MILLI_ISO_FORMAT = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String DATE_ISO_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_SLASH_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String DATETIME_MILLI_SLASH_FORMAT = "yyyy/MM/dd HH:mm:ss SSS";
    public static final String DATETIME_MILLI_COMPACT_FORMAT = "yyyyMMddHHmmss SSS";
    public static final String DATE_SLASH_FORMAT = "yyyy/MM/dd";
    public static final String DATE_SHORT_FORMAT = "yy/MM/dd";

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

    /**
     * 字符串转时间
     */
    public static Date strToDate(String strDate, String strFormat) {
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 时间转字符串
     */
    public static String dateToStr(Date date, String strFormat) {
        SimpleDateFormat format = new SimpleDateFormat(strFormat);
        String str = format.format(date);
        return str;
    }

    /**
     * 获取当前日期指定时间前的时间
     */
    public static Date getBeforeTime(int type, int count) {
        Calendar beforeCalendar = Calendar.getInstance();
        beforeCalendar.add(type, count);
        Date beforeTime = beforeCalendar.getTime();
        return beforeTime;
    }
}
