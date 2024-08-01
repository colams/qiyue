package com.foxconn.sw.data.constants.enums.exception;

import com.foxconn.sw.data.interfaces.IResult;

/**
 * code六位数字说明：100001-xxsooo
 * xx   表示接口
 * s    待定
 * ooo  表示具体的错误码
 */
public enum LoginExceptionRetCode implements IResult {

    LOGIN_ACCOUNT_EXCEPTION(100001, "账号密码不匹配！"),
    LOGIN_STATE_KEEP_EXCEPTION(100002, "登录异常！"),
    ;

    LoginExceptionRetCode(int code, String msg) {
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