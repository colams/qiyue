package com.foxconn.sw.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public static String getFilePrefix() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DateTimeFormatter.ofPattern(DateTimePattern.yyyyMMddHHmmssSSS));
    }

    public interface DateTimePattern {

        String yyyyMMddHHmmss = "yyyyMMddHHmmss";

        String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";

    }
}
