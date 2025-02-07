package com.foxconn.sw.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateTimeUtils {

    /**
     * 设置时间戳
     *
     * @return
     */
    public static String getTimeStamp() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DateTimeFormatter.ofPattern(DateTimePattern.yyyyMMddHHmmss));
    }

    /**
     * 设置时间戳
     *
     * @return
     */
    public static String format(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(DateTimePattern.yyyyMMddHHmmss2));
    }

    /**
     * 设置时间戳
     *
     * @return
     */
    public static String formatYMD(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return "";
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(DateTimePattern.yyyyMMdd1));
    }

    /**
     * 设置时间戳
     *
     * @return
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 设置时间戳
     *
     * @return
     */
    public static String formatHm(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(DateTimePattern.hhMM));
    }


    /**
     * 设置时间戳
     *
     * @return
     */
    public static String format(String inputDate, String pattern) {
        LocalDate localDate = LocalDate.parse(inputDate);
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 设置时间戳
     *
     * @return
     */
    public static String formatYMD() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DateTimeFormatter.ofPattern(DateTimePattern.yyyyMMdd));
    }

    /**
     * 设置时间戳
     *
     * @return
     */
    public static String getFilePrefix() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DateTimeFormatter.ofPattern(DateTimePattern.yyyyMMddHHmmssSSS));
    }

    public interface DateTimePattern {
        String yyyyMMddHHmmss = "yyyyMMddHHmmss";

        String yyyyMMddHHmmss2 = "yyyy-MM-dd HH:mm:ss";

        String yyyyMMddHHmmsssss = "yyyy-MM-dd HH:mm:ss.SSS";

        String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

        String yyyyMMdd = "yyyyMMdd";

        String yyyyMMdd1 = "yyyy-MM-dd";

        String hhMM = "HH:mm";
    }
}
