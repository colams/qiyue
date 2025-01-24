package com.foxconn.sw.data.dto.enums.document;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum MainTypeEnums implements IUniverseCode {
    Project("工程類"),
    HR("人資行政類"),
    BusinessAndMarket("業務市場類"),
    Intellectual_Property("知識產權類"),
    Supply_Chain("供應鏈類"),
    File_Template("文件模板類"),
    Workflow("工作流程類"),

    ;

    MainTypeEnums(String name) {
        this.name = name;
    }

    private String name;

    public String getCode() {
        return name();
    }

    public String getName() {
        return name;
    }


    public static MainTypeEnums getEnumByCode(String code) {
        for (MainTypeEnums enums : MainTypeEnums.values()) {
            if (enums.name().equalsIgnoreCase(code)) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }

}
