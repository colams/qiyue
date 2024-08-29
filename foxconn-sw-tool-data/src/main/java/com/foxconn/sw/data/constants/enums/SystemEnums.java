package com.foxconn.sw.data.constants.enums;

public enum SystemEnums {

    LOGIN(0, "登录页面"),
    TOOL(1, "工具库系统"),
    OA(2, "OA系统"),
    QUOTATION(3, "报价系统"),
    SYSTEM(99, "系统管理"),

    ;

    SystemEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
