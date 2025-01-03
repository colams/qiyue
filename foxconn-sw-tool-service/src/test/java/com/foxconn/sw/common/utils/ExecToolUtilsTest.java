package com.foxconn.sw.common.utils;

import com.foxconn.sw.service.BaseTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExecToolUtilsTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ExecToolUtilsTest.class);


    @Test
    public void outputResult() throws FileNotFoundException {

        List<String> results = new ArrayList<>();
        results.add("test 000:");
        results.add("test 001:");
        logger.info("test listToolHistory over");
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        logger.info("当前日期属于今年的第 " + weekOfYear + " 周");
    }
}