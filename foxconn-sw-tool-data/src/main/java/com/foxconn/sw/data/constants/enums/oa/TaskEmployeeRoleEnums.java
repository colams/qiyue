package com.foxconn.sw.data.constants.enums.oa;


public enum TaskEmployeeRoleEnums {

    unknown(0, "未知"),
    proposer(1, "提出者"),
    linkman(2, "中间人"),
    handler(3, "直接处理人"),
;

    TaskEmployeeRoleEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
