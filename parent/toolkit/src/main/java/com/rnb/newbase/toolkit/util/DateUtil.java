package com.rnb.newbase.toolkit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
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
     * @param date now date
     * @param dateType Calendar date type
     * @param amount adjust amount
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
     * @param dateType Calendar date type
     * @param amount adjust amount
     */
    public static Date getBeforeTime(int dateType, int amount) {
        Calendar beforeCalendar = Calendar.getInstance();
        beforeCalendar.add(dateType, amount);
        Date beforeTime = beforeCalendar.getTime();
        return beforeTime;
    }

    /**
     * 获取指定时间的差值
     * @param startTime
     * @param endTime
     * @param dateType
     * @return
     */
    public static Long getTimeDifference(Date startTime, Date endTime, int dateType) {
        Instant startInstant = startTime.toInstant();
        Instant endInstant = endTime.toInstant();
        Long difference = 0l;
        switch(dateType) {
            case Calendar.MILLISECOND:
                difference = Duration.between(startInstant, endInstant).toMillis();
                break;
            case Calendar.SECOND:
                difference = Duration.between(startInstant, endInstant).getSeconds();
                break;
            case Calendar.MINUTE:
                difference = Duration.between(startInstant, endInstant).getSeconds()/60;
                break;
            case Calendar.HOUR:
                difference = Duration.between(startInstant, endInstant).getSeconds()/3600;
                break;
        }
        return difference;
    }

    /**
     * 获取指定日期的下一日0点时间
     * @param date
     * @return
     */
    public static Date getNextDate(Date date) {
        Date nextTime = getNextTime(date, Calendar.DATE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String roundTime = simpleDateFormat.format(nextTime);
        return strToDate(roundTime, DateUtil.DATETIME_ISO_FORMAT);
    }

    /**
     * 获取指定周期后的下一时间
     * @param date
     * @return
     */
    public static Date getNextTime(Date date, int dateType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateType, 1);
        Date nextDate = calendar.getTime();
        return nextDate;
    }

    /**
     * 获取指定日期的上一日0点时间
     * @param date
     * @return
     */
    public static Date getBeforeDate(Date date) {
        Date nextTime = getLastTime(date, Calendar.DATE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String roundTime = simpleDateFormat.format(nextTime);
        return strToDate(roundTime, DateUtil.DATETIME_ISO_FORMAT);
    }

    /**
     * 获取指定周期后的上一时间
     * @param date
     * @return
     */
    public static Date getLastTime(Date date, int dateType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(dateType, -1);
        Date nextDate = calendar.getTime();
        return nextDate;
    }

    /**
     * 获取指定日期对应星期几
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }
}
