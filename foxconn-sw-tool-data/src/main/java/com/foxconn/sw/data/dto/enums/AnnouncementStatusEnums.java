package com.foxconn.sw.data.dto.enums;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum AnnouncementStatusEnums {

    Draft("D", "群组设置"),
    Release("R", "部门设置"),
    ;

    AnnouncementStatusEnums(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static AnnouncementStatusEnums getEnumByCode(String code) {
        for (AnnouncementStatusEnums enums : AnnouncementStatusEnums.values()) {
            if (enums.getCode().equalsIgnoreCase(code)) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }

}
