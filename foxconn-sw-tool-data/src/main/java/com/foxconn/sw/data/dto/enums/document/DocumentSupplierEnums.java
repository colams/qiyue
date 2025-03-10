package com.foxconn.sw.data.dto.enums.document;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum DocumentSupplierEnums implements IUniverseCode {

    None("None"),
    MTM("MTM"),
    ALPS("ALPS"),
    ICT("ICT"),
    OV("OV"),
    SONY("SONY"),
    ST("ST"),
    AGC("AGC"),
    PTOT("PTOT"),
    VIAVI("VIAVI"),
    COT("COT"),
    JCET("JCET"),
    UTAC("UTAC"),
    Sumitomo("Sumitomo"),
    Fujikura("Fujikura"),
    Flexium("Flexium"),
    Mektec("Mektec"),
    Career("Career"),
    Compeq("Compeq"),
    Avary("Avary"),
    GESO("GESO"),
    Largan("Largan"),
    Sunny("Sunny"),
    Derkwoo("Derkwoo"),
    Sunway("Sunway"),
    Chitwing("Chitwing"),
    Triotek("Triotek"),
    Amagasaki("Amagasaki"),
    LY("LY"),
    Other("Other"),

    ;

    DocumentSupplierEnums(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name();
    }

    @Override
    public String getCode() {
        return name();
    }

    public static DocumentSupplierEnums getEnumByCode(String code) {
        for (DocumentSupplierEnums enums : DocumentSupplierEnums.values()) {
            if (enums.getCode().equalsIgnoreCase(code)) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }

    public static String getEnumNameByCode(String code) {
        for (DocumentSupplierEnums enums : DocumentSupplierEnums.values()) {
            if (enums.getCode().equalsIgnoreCase(code)) {
                return enums.getName();
            }
        }
        return code;
    }


}
