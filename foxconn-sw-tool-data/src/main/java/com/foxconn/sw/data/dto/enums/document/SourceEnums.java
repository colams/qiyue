package com.foxconn.sw.data.dto.enums.document;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum SourceEnums implements IUniverseCode {

    Internal("Internal"),
    Customer("Customer"),
    Vendor("Vendor"),
    Government("Government"),

    ;

    SourceEnums(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }


    @Override
    public String getCode() {
        return name();
    }


    public static SourceEnums getEnumByCode(String code) {
        for (SourceEnums enums : SourceEnums.values()) {
            if (enums.getCode().equalsIgnoreCase(code)) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }


    public static String getEnumNameByCode(String code) {
        for (SourceEnums enums : SourceEnums.values()) {
            if (enums.getCode().equalsIgnoreCase(code)) {
                return enums.getName();
            }
        }
        return code;
    }

}
