package com.foxconn.sw.service;


import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootApplication(scanBasePackages = {
        "com.foxconn.sw.service",
        "com.foxconn.sw.model",
        "com.foxconn.sw.data",
        "com.foxconn.sw.common",
        "com.foxconn.sw.business",
})
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class BaseTest {
}
