package com.foxconn.sw.data.dto.enums.document;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum MainPartEnums {

    None("None"),
    Flex("Flex"),
    Connector("Connector"),
    VCM("VCM"),
    Sensor("Sensor"),
    Lens("Lens"),
    Substrate("Substrate"),
    Stiffener("Stiffener"),
    LED("LED"),
    ALS("ALS"),
    VESCEL("VESCEL"),
    Tool("Tool"),
    Socket("Socket"),
    Tray("Tray"),
    Cap("Cap"),
    Cube("Cube"),
    Module("Module"),
    Sensor_Stiffener("Sensor Stiffener"),
    IRCF("IRCF"),
    EPOXY("EPOXY"),
    Other("Other"),
    ;

    MainPartEnums(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }


    public static MainPartEnums getEnumByCode(String code) {
        for (MainPartEnums enums : MainPartEnums.values()) {
            if (enums.getCode().equalsIgnoreCase(code)) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }

}
