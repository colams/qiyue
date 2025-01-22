package com.foxconn.sw.data.dto.enums.document;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum MainTypeEnums implements IUniverseCode {
    Project("project", "工程類"),
    HR("HR", "人資行政類"),
    BusinessAndMarket("BM", "業務市場類"),
    Intellectual_Property("intellectual", "知識產權類"),
    Supply_Chain("supply_chain", "供應鏈類"),
    File_Template("file_template", "文件模板類"),
    Workflow("workflow", "工作流程類"),

    ;

    MainTypeEnums(String code, String name) {
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


    public static MainTypeEnums getEnumByCode(String code) {
        for (MainTypeEnums enums : MainTypeEnums.values()) {
            if (enums.getCode().equalsIgnoreCase(code)) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }

}
