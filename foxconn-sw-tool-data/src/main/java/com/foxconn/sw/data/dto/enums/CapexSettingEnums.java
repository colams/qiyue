package com.foxconn.sw.data.dto.enums;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum CapexSettingEnums {
    GroupSetting(1, "GroupSetting", "群组设置"),
    DepartSetting(2, "DepartSetting", "部门设置"),
    EmployeeSetting(3, "EmployeeSetting", "员工设置"),
    ;

    CapexSettingEnums(int code, String enCode, String name) {
        this.code = code;
        this.enCode = enCode;
        this.name = name;
    }

    private int code;
    private String enCode;
    private String name;

    public int getCode() {
        return code;
    }

    public String getEnCode() {
        return enCode;
    }

    public String getName() {
        return name;
    }

    public static CapexSettingEnums getEnumByCode(Integer code) {
        for (CapexSettingEnums enums : CapexSettingEnums.values()) {
            if (enums.getCode() == code) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }
}
