package com.foxconn.sw.service;


import com.foxconn.sw.data.dto.Header;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;


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

    public static void main(String[] args) {
        String originalText = "Big5字符串";

        try {
            // 将UTF-8编码的字节转换回字符串
            String convertedText = new String(originalText.getBytes(StandardCharsets.UTF_8), "MS950");

            System.out.println("Original Text: " + originalText);
            System.out.println("Converted Text: " + convertedText);
        } catch (UnsupportedCharsetException | UnsupportedEncodingException e) {
            System.out.println("Charset not supported.");
        }
    }



}
