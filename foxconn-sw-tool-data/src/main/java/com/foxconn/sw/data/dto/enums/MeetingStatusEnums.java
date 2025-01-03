package com.foxconn.sw.data.dto.enums;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum MeetingStatusEnums {

    Initiate(0, "待开始"),
    Afoot(0, "进行中"),
    Finish(0, "已结束"),
    ;

    MeetingStatusEnums(int code, String description) {
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


    public static MeetingStatusEnums getEnumByCode(Integer code) {
        for (MeetingStatusEnums enums : MeetingStatusEnums.values()) {
            if (enums.getCode() == code) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }

}
