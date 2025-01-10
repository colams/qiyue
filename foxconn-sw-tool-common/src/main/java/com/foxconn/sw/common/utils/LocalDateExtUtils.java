package com.foxconn.sw.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.foxconn.sw.common.constanst.DateTimePattern.yyyyMMdd;

public class LocalDateExtUtils {

    public static LocalDate toLocalDate(String strDate) {
        return LocalDate.parse(strDate, DateTimeFormatter.ofPattern(yyyyMMdd));
    }

    public static String toString(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(yyyyMMdd));
    }


}
