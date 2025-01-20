package com.foxconn.sw.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.foxconn.sw.common.constanst.DateTimePattern.yyyyMMdd;

public class StringExtUtils {


    public static String toString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern(yyyyMMdd));
    }


}
