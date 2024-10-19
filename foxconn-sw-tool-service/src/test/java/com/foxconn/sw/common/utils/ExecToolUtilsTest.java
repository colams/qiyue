package com.foxconn.sw.common.utils;

import com.foxconn.sw.service.BaseTest;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExecToolUtilsTest extends BaseTest {



    @Test
    public void outputResult() throws FileNotFoundException {

        List<String> results = new ArrayList<>();
        results.add("test 000:");
        results.add("test 001:");

//        String fileName = ExecToolUtils.outputResult(results);
        System.out.println("fileName");
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println("当前日期属于今年的第 " + weekOfYear + " 周");
    }
}