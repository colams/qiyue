package com.foxconn.sw.data.dto.enums.document;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum SourceEnums {

    Internal("Internal"),
    Customer("Customer"),
    Vendor("Vendor"),
    Government("Government"),

    ;

    SourceEnums(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }


    public static SourceEnums getEnumByCode(String code) {
        for (SourceEnums enums : SourceEnums.values()) {
            if (enums.getCode().equalsIgnoreCase(code)) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }

}
