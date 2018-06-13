package com.nextyu.mall.util;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * created on 2016-08-17 11:49
 *
 * @author nextyu
 */
public final class DateTimeUtil {
    private DateTimeUtil() {
        throw new AssertionError();
    }

    /**
     * 返回指定时间当天的0时0分0秒
     *
     * @param dateTime 需要转换的时间
     * @return 转换后的时间
     */
    public static DateTime beginningOfTheDay(DateTime dateTime) {
        return dateTime.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
    }

    /**
     * 返回指定时间当天的23时59分59秒
     *
     * @param dateTime 需要转换的时间
     * @return 转换后的时间
     */
    public static DateTime endOfTheDay(DateTime dateTime) {
        return dateTime.withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
    }

    /**
     * jdk 的 Date 转换为 joda-time 的 DateTime
     *
     * @param date
     * @return
     */
    public static DateTime date2DateTime(Date date) {
        return new DateTime(date.getTime());
    }

    /**
     * joda-time 的 DateTime 转换为 jdk 的 Date
     *
     * @param dateTime
     * @return
     */
    public static Date dateTime2Date(DateTime dateTime) {
        return dateTime.toDate();
    }

    /**
     * 格式化 DateTime
     *
     * @param dateTime
     * @param pattern
     * @return
     */
    public static String formatDateTime(DateTime dateTime, String pattern) {
        return dateTime.toString(pattern);
    }

    /**
     * 格式化 Date
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        return formatDateTime(date2DateTime(date), pattern);
    }

    /**
     * 比较两个DateTime的大小
     *
     * @param first  第一个
     * @param second 第二个
     * @return first == second，返回0；first < second，返回值小于0；first > second，返回值大于0
     */
    public static int compareDateTime(DateTime first, DateTime second) {
        long firstMillis = first.getMillis();
        long secondMillis = second.getMillis();
        return Long.compare(firstMillis, secondMillis);
    }

    /**
     * 比较两个Date的大小
     *
     * @param first  第一个
     * @param second 第二个
     * @return first == second，返回0；first < second，返回值小于0；first > second，返回值大于0
     */
    public static int compareDate(Date first, Date second) {
        long firstMillis = first.getTime();
        long secondMillis = second.getTime();
        return Long.compare(firstMillis, secondMillis);
    }

    /**
     * 返回当前时间-毫秒
     *
     * @return
     */
    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 返回当前时间-秒
     *
     * @return
     */
    public static long currentTimeSeconds() {
        return currentTimeMillis() / 1000;
    }

    /**
     * DateTime 转毫秒
     *
     * @param dateTime
     * @return
     */
    public static long dateTime2Millis(DateTime dateTime) {
        return dateTime.getMillis();
    }

    /**
     * Date 转毫秒
     *
     * @param date
     * @return
     */
    public static long date2Millis(Date date) {
        return date.getTime();
    }

    /**
     * DateTime 转秒
     *
     * @param dateTime
     * @return
     */
    public static long dateTime2Seconds(DateTime dateTime) {
        return dateTime2Millis(dateTime) / 1000;
    }

    /**
     * Date 转秒
     *
     * @param date
     * @return
     */
    public static long date2Seconds(Date date) {
        return date2Millis(date) / 1000;
    }

    /**
     * 毫秒转 DateTime
     *
     * @param mills
     * @return
     */
    public static DateTime millis2DateTime(long mills) {
        return new DateTime(mills);
    }

    /**
     * 毫秒转 Date
     *
     * @param mills
     * @return
     */
    public static Date millis2Date(long mills) {
        return new Date(mills);
    }

    /**
     * 秒转 DateTime
     *
     * @param seconds
     * @return
     */
    public static DateTime seconds2DateTime(long seconds) {
        return millis2DateTime(seconds * 1000);
    }

    /**
     * 秒转 Date
     *
     * @param seconds
     * @return
     */
    public static Date seconds2Date(long seconds) {
        return millis2Date(seconds * 1000);
    }

}
