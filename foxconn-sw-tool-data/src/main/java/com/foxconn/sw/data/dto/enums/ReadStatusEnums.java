package com.foxconn.sw.data.dto.enums;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum ReadStatusEnums {

    Un_Read(0, "未讀"),
    Read(1, "已讀"),
    ;

    ReadStatusEnums(int code, String description) {
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


    public static ReadStatusEnums getEnumByCode(Integer code) {
        for (ReadStatusEnums enums : ReadStatusEnums.values()) {
            if (enums.getCode() == code) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }

}
