package com.foxconn.sw.data.constants.enums.retcode;

import com.foxconn.sw.data.interfaces.IResult;

/**
 * 常用错误消息
 */
public enum RetCode implements IResult {
    SUCCESS(0, "接口調用成功"),
    FAILURE(1, "接口調用失败"),
    VALIDATE_FAILED(2, "參數校驗失敗"),
    FORBIDDEN(3, "沒有權限訪問資源"),
    SYSTEM_EXCEPTION(4, "接口異常,請聯繫管理員(張占騰)"),
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