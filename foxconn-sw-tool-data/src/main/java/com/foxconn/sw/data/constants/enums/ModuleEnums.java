package com.foxconn.sw.data.constants.enums;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum ModuleEnums {

    ALL(0, "所有"),
    Forum(1, "论坛"),
    Task(2, "工作任務"),

    ;

    ModuleEnums(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


    public static ModuleEnums getEnumByCode(Integer code) {
        for (ModuleEnums enums : ModuleEnums.values()) {
            if (enums.getCode() == code) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }

}
