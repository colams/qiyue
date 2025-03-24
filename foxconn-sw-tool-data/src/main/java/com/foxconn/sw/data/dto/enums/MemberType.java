package com.foxconn.sw.data.dto.enums;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum MemberType {
    DEPARTMENT(1, "部门"),
    EMPLOYEE(2, "员工"),
    GROUP(3, "群组"),

    ;

    MemberType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    private Integer code;
    private String description;

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }


    public static MemberType getEnumByName(String name) {
        for (MemberType enums : MemberType.values()) {
            if (enums.name().equalsIgnoreCase(name)) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }

    public static MemberType getEnumByCode(Object code) {
        for (MemberType enums : MemberType.values()) {
            if (code instanceof Integer) {
                if (enums.getCode().equals(code)) {
                    return enums;
                }
            }

            if (code instanceof String) {
                String value = (String) code;
                if (enums.getCode().toString().equalsIgnoreCase(value) || enums.name().equalsIgnoreCase(value)) {
                    return enums;
                }
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }

}
