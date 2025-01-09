package com.foxconn.sw.data.dto.enums.system;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum OptionsEnums {


    DocumentOption("文档"),
    ;

    OptionsEnums(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public static OptionsEnums getEnumByCode(String optionCode) {
        for (OptionsEnums enums : OptionsEnums.values()) {
            if (enums.name().equalsIgnoreCase(optionCode)) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }
}
