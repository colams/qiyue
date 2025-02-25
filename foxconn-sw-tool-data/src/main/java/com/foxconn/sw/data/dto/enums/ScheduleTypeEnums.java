package com.foxconn.sw.data.dto.enums;

import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public enum ScheduleTypeEnums {

    Working("工作"),
    BusinessTrip("出差"),
    PersonalVacation("個人假日"),
    OfficialHoliday("法定假日"),
    MovingDay("移動日"),

    ;


    ScheduleTypeEnums(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }


    public static ScheduleTypeEnums getEnumByCode(String code) {
        for (ScheduleTypeEnums enums : ScheduleTypeEnums.values()) {
            if (enums.name().equalsIgnoreCase(code)) {
                return enums;
            }
        }
        throw new BizException(ENUM_CONVERT_ERROR);
    }


}
