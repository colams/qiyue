package com.foxconn.sw.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.foxconn.sw.common.utils.StringExtensionUtils.DateTimePattern.yyyyMMdd;

public class StringExtensionUtils {

    public interface DateTimePattern {
        String yyyyMMdd = "yyyy-MM-dd";
    }

    public static LocalDate toLocalDate(String strDate) {
        return LocalDate.parse(strDate, DateTimeFormatter.ofPattern(yyyyMMdd));
    }


}
