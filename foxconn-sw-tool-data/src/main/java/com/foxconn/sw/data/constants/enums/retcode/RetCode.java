package com.foxconn.sw.data.constants.enums.retcode;

import com.foxconn.sw.data.interfaces.IResult;

/**
 * 常用错误消息
 */
public enum RetCode implements IResult {
    SUCCESS(0, "接口调用成功"),
    FAILURE(1, "接口调用失败"),
    VALIDATE_FAILED(2, "参数校验失败"),
    FORBIDDEN(3, "没有权限访问资源"),
    SYSTEM_EXCEPTION(4, "接口发生异常"),
    EMPTY_FILE_ERROR(5, "empty file error"),
    UPLOAD_FILE_ERROR(6, "upload file error"),
    ENUM_CONVERT_ERROR(7, "enum convert error"),
    UPLOAD_FILE_TYPE_ERROR(8, "upload file file error"),
    SYSTEM_ERROR(9, "system error"),

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