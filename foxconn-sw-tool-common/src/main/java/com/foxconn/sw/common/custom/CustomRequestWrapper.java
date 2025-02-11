package com.foxconn.sw.common.custom;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.*;

public class CustomRequestWrapper extends HttpServletRequestWrapper {
    // 用于存储请求体内容的字符串
    private final String body;

    public CustomRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            // 获取请求的输入流
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                // 使用 BufferedReader 读取输入流
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead;
                // 循环读取输入流内容
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            // 捕获并抛出读取输入流时的异常
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    // 关闭 BufferedReader
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        // 将读取到的内容存储到 body 变量中
        body = stringBuilder.toString();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        // 将存储的请求体内容转换为 ByteArrayInputStream
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                // 判断输入流是否读取完毕
                return byteArrayInputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                // 表示输入流随时可以读取
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                // 不支持设置 ReadListener，抛出不支持操作异常
                throw new UnsupportedOperationException();
            }

            @Override
            public int read() throws IOException {
                // 从 ByteArrayInputStream 中读取一个字节
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        // 通过 getInputStream() 方法获取输入流，并包装成 BufferedReader 返回
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        // 返回存储的请求体内容
        return this.body;
    }
}