package com.foxconn.sw.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.foxconn.sw.common.utils.constanst.DateTimePattern.yyyyMMdd;

public class StringExtUtils {

    public static String toString(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern(yyyyMMdd));
    }


}
