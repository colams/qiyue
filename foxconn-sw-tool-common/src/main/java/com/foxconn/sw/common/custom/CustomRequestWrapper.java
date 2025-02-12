package com.foxconn.sw.common.custom;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CustomRequestWrapper extends HttpServletRequestWrapper {
    // 用于存储请求体内容的字符串
    private String body = "";
    private byte[] cachedBody;
    private final String characterEncoding = "UTF-8";

    public CustomRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        if (isMultipartContent(request)) {
            return;
        }

        java.io.InputStream requestInputStream = request.getInputStream();
        java.io.ByteArrayOutputStream byteArrayOutputStream = new java.io.ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = requestInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        this.cachedBody = byteArrayOutputStream.toByteArray();
        // 将字节数组转换为字符串并缓存
        this.body = new String(cachedBody, characterEncoding);
    }

    private boolean isMultipartContent(HttpServletRequest request) {
        String contentType = request.getContentType();
        return contentType != null && contentType.toLowerCase().startsWith("multipart/form-data");
    }

    @Override
    public String getCharacterEncoding() {
        return "UTF-8";
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        // 将存储的请求体内容转换为 ByteArrayInputStream
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(cachedBody);
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