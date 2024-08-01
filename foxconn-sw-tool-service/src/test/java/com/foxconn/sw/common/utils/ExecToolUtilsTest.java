package com.foxconn.sw.common.utils;

import com.foxconn.sw.service.BaseTest;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ExecToolUtilsTest extends BaseTest {



    @Test
    public void outputResult() throws FileNotFoundException {

        List<String> results = new ArrayList<>();
        results.add("test 000:");
        results.add("test 001:");

        String fileName = ExecToolUtils.outputResult(results);
        System.out.println(fileName);
    }
}