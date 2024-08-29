package com.foxconn.sw.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


@Schema(description = "请求参数")
public class Request<T> {

    /**
     * 請求跟蹤參數
     */
    @Schema(description = "跟踪参数traceid")
    private String traceId;

    @Schema(description = "请求时间戳")
    private String timeStamp;

    /**
     * 請求head信息，主要記錄瀏覽器，客戶相關信息
     */
    @NotNull(message = "head 对象不能为空")
    @Schema(description = "请求头信息")
    private Header head;

    /**
     * 請求實際參數
     */
    @Valid
    @Schema(description = "请求信息")
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
