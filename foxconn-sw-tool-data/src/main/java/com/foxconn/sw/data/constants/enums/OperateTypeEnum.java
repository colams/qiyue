package com.foxconn.sw.data.constants.enums;

public enum OperateTypeEnum {

    VIEW(0, "查看"),
    UPDATE(1, "更新"),
    ASSIGN(2, "分派"),
    CHECK(3, "验收"),
    REVOKE(4, "撤销"),
    ;

    OperateTypeEnum(int code, String msg) {
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
