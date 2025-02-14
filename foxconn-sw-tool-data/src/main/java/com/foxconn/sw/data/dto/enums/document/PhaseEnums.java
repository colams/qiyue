package com.foxconn.sw.data.dto.enums.document;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum PhaseEnums implements IUniverseCode {
    C7("C7"),
    C6("C6"),
    C5("C5"),
    C4("C4"),
    C3("C3"),
    C3_1("C3.1"),
    C2("C2"),
    AVB("AVB"),
    OVB("OVB"),
    PRB("PRB"),
    PPRB("PPRB"),
    PVT("PVT"),
    MP("MP"),
    ;

    PhaseEnums(String name) {
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

    public static PhaseEnums getEnumByCode(String code) {
        for (PhaseEnums enums : PhaseEnums.values()) {
            if (enums.getCode().equalsIgnoreCase(code)) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }
}
