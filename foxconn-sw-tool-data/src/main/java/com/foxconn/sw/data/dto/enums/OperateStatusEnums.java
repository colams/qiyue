package com.foxconn.sw.data.dto.enums;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum OperateStatusEnums {

    WaitProcess(0, "待處理"),
    Finish(1, "已同意"),
    Reject(2, "駁回"),
    ;

    OperateStatusEnums(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private int code;
    private String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }


    public static OperateStatusEnums getEnumByCode(Integer code) {
        for (OperateStatusEnums enums : OperateStatusEnums.values()) {
            if (enums.getCode() == code) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }

}
