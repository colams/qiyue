package com.foxconn.sw.data.constants.enums.retcode;

import com.foxconn.sw.data.interfaces.IResult;

/**
 * code六位数字说明：1000001-mmxxooo
 * mm   表示模块
 * xx   表示接口
 * ooo  表示具体的错误码
 */
public enum OAExceptionCode implements IResult {

    TASK_ERROR_EXCEPTION(1100001, "查询失败，请联系管理员！"),
    NO_PERMISSION_EXCEPTION(1100002, "没有权限访问资源！"),



    ;

    OAExceptionCode(int code, String msg) {
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