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
    CREATE_ACCOUNT_EXCEPTION(1000003, "账号创建失败"),
    CREATE_ACCOUNT_LOGIN_EXCEPTION(1000004, "账号创建成功，请登录"),
    RESET_PASSWORD_EXCEPRTION(1000005, "重置密码失败"),
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