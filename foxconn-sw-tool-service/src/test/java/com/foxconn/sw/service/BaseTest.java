package com.foxconn.sw.service;


import com.foxconn.sw.data.dto.Header;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootApplication(scanBasePackages = {
        "com.foxconn.sw.service",
        "com.foxconn.sw.data",
        "com.foxconn.sw.common",
        "com.foxconn.sw.business",
})
// @RunWith(MockitoJUnitRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class BaseTest {

    public Header initHead() {
        Header head = new Header();
        head.setToken("51cc893825d9468ebb265e779b732a23");
        return head;
    }

}
