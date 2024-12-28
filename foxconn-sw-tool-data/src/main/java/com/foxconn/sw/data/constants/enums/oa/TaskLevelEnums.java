package com.foxconn.sw.data.constants.enums.oa;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum TaskLevelEnums {

    LOW(1, "低","turquoise"),
    MIDDLE(2, "中","dodgerblue"),
    HIGH(3, "高","blueviolet"),
    HIGHEST(4, "紧急","red"),

    ;

    TaskLevelEnums(Integer code, String msg,String color) {
        this.code = code;
        this.msg = msg;
        this.color = color;
    }

    private Integer code;
    private String msg;
    private String color;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getColor() {
        return color;
    }

    public static TaskLevelEnums getLevel(Integer text) {
        for (TaskLevelEnums status : TaskLevelEnums.values()) {
            if (status.getCode().equals(text)) {
                return status;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }


}
