package com.lyf.es.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    public static synchronized String getNowDateTime() {
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = ldt.format(dtf);
        return format;
    }

    /**将日期类型转换成String类型，yyyy-MM-dd HH:mm:ss格式*/
    public static String dateToString(Date date) {
        String sDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return sDate;
    }

    public static Date formatDateString(String date, String pattern) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.parse(date);
        } catch (Exception e) {
            return null;
        }
    }


    /**将String类型转换成日期类型：格式：yyyy-MM-dd*/
    public static Date stringToDate(String sDate) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    // 格式化日期
    public static String formatDate(Date date, String pattern) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.format(date);
        } catch (RuntimeException e) {
            return null;
        }
    }

    /**将String类型转换成固定格式日期类型：格式：yyyy-MM-dd HH:mm:ss*/
    public static String stringToString(String sDate) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse = simpleDateFormat.parse(sDate);
            return simpleDateFormat.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date stringToDate(String sDate, String pattern) {
        Date date = null;
        try {
            date = new SimpleDateFormat(pattern).parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


}