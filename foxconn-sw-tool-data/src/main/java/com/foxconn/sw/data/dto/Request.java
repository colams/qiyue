package com.foxconn.sw.data.dto;

import jakarta.validation.Valid;

public class Request<T> {

    /**
     * 請求跟蹤參數
     */
    private String traceId;

    private String timeStamp;

    /**
     * 請求head信息，主要記錄瀏覽器，客戶相關信息
     */
    private Header head;

    /**
     * 請求實際參數
     */
    @Valid
    private T data;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Header getHead() {
        return head;
    }

    public void setHead(Header head) {
        this.head = head;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
