package com.foxconn.sw.data.constants.enums.retcode;

import com.foxconn.sw.data.interfaces.IResult;

/**
 * code六位数字说明：1000001-mmxxooo
 * mm   表示模块
 * xx   表示接口
 * ooo  表示具体的错误码
 */
public enum AccountExceptionCode implements IResult {

    LOGIN_ACCOUNT_EXCEPTION(1000001, "账号密码不匹配！"),
    LOGIN_STATE_KEEP_EXCEPTION(1000002, "登录异常！"),
    ;

    AccountExceptionCode(int code, String msg) {
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