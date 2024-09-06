package com.foxconn.sw.data.constants.enums;

public enum OperateTypeEnum {

    VIEW("查看", "list"),
    UPDATE("更新", "list"),
    REMINDER("跟催", "list"),
    REVOKE("撤销", "list"),

    REJECT("駁回", "detail"),
    ASSIGN("分派", "detail"),
    ACCEPT("接受", "detail"),
    SUBMIT("提交", "detail"),
    CHECK("驗收", "detail"),
    ;

    OperateTypeEnum(String msg, String page) {
        this.msg = msg;
        this.page = page;
    }

    private String msg;
    private String page;


    public String getMsg() {
        return msg;
    }

    public String getPage() {
        return page;
    }
}
