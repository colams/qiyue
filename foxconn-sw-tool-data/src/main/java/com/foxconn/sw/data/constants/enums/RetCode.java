package com.foxconn.sw.data.constants.enums;

import com.foxconn.sw.data.interfaces.IResult;

public enum RetCode implements IResult {
    SUCCESS(0, "接口调用成功"),
    FAILURE(1, "接口调用失败"),
    VALIDATE_FAILED(2, "参数校验失败"),
    FORBIDDEN(3, "没有权限访问资源"),
    SYSTEM_EXCEPTION(4, "接口发生异常"),



    EMPTY_FILE_ERROR(4, "empty file error"),
    UPLOAD_FILE_ERROR(5, "upload file error"),

    ;

    RetCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;


    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}