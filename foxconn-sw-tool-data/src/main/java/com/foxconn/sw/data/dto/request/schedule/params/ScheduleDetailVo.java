package com.foxconn.sw.data.dto.request.schedule.params;

import com.foxconn.sw.data.dto.enums.ScheduleTypeEnums;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class ScheduleDetailVo {

    @Schema(description = "    Working(\"工作\"),\n" +
            "    BusinessTrip(\"出差\"),\n" +
            "    PersonalVacation(\"個人假日\"),\n" +
            "    OfficialHoliday(\"法定假日\"),\n" +
            "    MovingDay(\"移動日\"),\n")
    private ScheduleTypeEnums scheduleType;
    @NotNull
    private String startDate;
    @NotNull
    private String endDate;

    public ScheduleTypeEnums getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(ScheduleTypeEnums scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
