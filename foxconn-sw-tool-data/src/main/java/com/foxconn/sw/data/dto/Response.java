package com.foxconn.sw.data.dto;

public class Response<T> {

    /**
     * 請求錯誤碼：0-success，其他失敗
     */
    private Integer code;

    /**
     * 請求錯誤消息
     */
    private String message;


    /**
     * 时间戳
     */
    private String timeStamp;

    /**
     * 請求跟蹤信息，同request中的traceid
     */
    private String traceId;

    /**
     * 響應結果信息
     */
    private T result;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
