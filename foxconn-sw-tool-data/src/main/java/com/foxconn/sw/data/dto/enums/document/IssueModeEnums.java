package com.foxconn.sw.data.dto.enums.document;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum IssueModeEnums implements IUniverseCode {
    None("None"),
    Cosmetic("Cosmetic"),
    Electrical("Electrical"),
    Mechanical("Mechanical"),
    Optical("Optical"),
    Particle_DPC("Particle - DPC"),
    Particle_LCB("Particle - LCB"),
    Software("Software"),
    Functional("Functional"),
    Other("Other"),

    ;

    IssueModeEnums(String name) {
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

    public static IssueModeEnums getEnumByCode(String code) {
        for (IssueModeEnums enums : IssueModeEnums.values()) {
            if (enums.getCode().equalsIgnoreCase(code)) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }
}
