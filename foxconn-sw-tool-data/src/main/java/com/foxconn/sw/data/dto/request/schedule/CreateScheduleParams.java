package com.foxconn.sw.data.dto.request.schedule;

import com.foxconn.sw.data.dto.request.schedule.params.ScheduleDetailVo;

import java.util.List;

public class CreateScheduleParams {

    private String place;
    private List<ScheduleDetailVo> detailList;
    private boolean autoMovingDay;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<ScheduleDetailVo> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<ScheduleDetailVo> detailList) {
        this.detailList = detailList;
    }

    public boolean getAutoMovingDay() {
        return autoMovingDay;
    }

    public void setAutoMovingDay(boolean autoMovingDay) {
        this.autoMovingDay = autoMovingDay;
    }
}
