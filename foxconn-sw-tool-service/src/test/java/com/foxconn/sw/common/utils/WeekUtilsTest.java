package com.foxconn.sw.common.utils;

import com.foxconn.sw.service.BaseTest;
import org.junit.Test;

import java.util.List;

public class WeekUtilsTest extends BaseTest {

    @Test
    public void getWeeksOfYearInfo() {
        List<Integer> weeks = WeekUtils.getWeeksOfYearInfo("2021-12-27");
        System.out.println(weeks);

        Integer i2 = Integer.valueOf(1);
        Integer i1 = Integer.valueOf("1");
        System.out.println(i1 == i2);
        System.out.println(i1.equals(i2));

    }
}