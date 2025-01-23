package com.foxconn.sw.data.dto.enums.document;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum ProcessEnums implements IUniverseCode {
    None("None"),
    Test("Test"),
    AA("AA"),
    FC("FC"),
    JS("JS"),
    AOI("AOI"),
    TS("TS"),
    GB("GB"),
    Design("Design"),
    Team_Roster("Team Roster"),
    Technical_Report("Technical Report"),
    Measurement("Measurement"),
    Layout("Layout"),
    Other("Other"),

    ;

    ProcessEnums(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCode() {
        return name();
    }

    public static ProcessEnums getEnumByCode(String code) {
        for (ProcessEnums enums : ProcessEnums.values()) {
            if (enums.getCode().equalsIgnoreCase(code)) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }
}
