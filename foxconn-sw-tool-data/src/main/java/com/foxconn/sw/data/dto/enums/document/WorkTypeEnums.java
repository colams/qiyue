package com.foxconn.sw.data.dto.enums.document;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum WorkTypeEnums implements IUniverseCode {
    Work( "工作文件"),
    Non_Work( "非工作文件"),
    ;

    WorkTypeEnums(String name) {
        this.name = name;
    }

    private String name;

    public String getCode() {
        return name();
    }

    @Override
    public String getName() {
        return name;
    }


    public static WorkTypeEnums getEnumByCode(String code) {
        for (WorkTypeEnums enums : WorkTypeEnums.values()) {
            if (enums.getCode().equalsIgnoreCase(code)) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }

    public static String getEnumNameByCode(String code) {
        for (WorkTypeEnums enums : WorkTypeEnums.values()) {
            if (enums.getCode().equalsIgnoreCase(code)) {
                return enums.getName();
            }
        }
        return code;
    }
}
