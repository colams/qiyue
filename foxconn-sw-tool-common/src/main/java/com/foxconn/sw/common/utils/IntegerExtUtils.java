package com.foxconn.sw.common.utils;

import com.foxconn.sw.common.constanst.NumberConstants;

import java.util.Objects;

public class IntegerExtUtils {


    public static boolean isPk(Integer origin) {
        return Objects.nonNull(origin) && origin.compareTo(NumberConstants.ZERO) > 0;
    }


}
