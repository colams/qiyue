package com.foxconn.sw.data.dto.entity.oa;

import io.swagger.v3.oas.annotations.media.Schema;

public class MyWorks {

    @Schema(description = "紧急事项")
    private int urgencyCount;
    @Schema(description = "项目待办")
    private int scheduleCount;
    @Schema(description = "今日会议")
    private int meetingCount;
    @Schema(description = "日志更新")
    private int logCount;

    public int getUrgencyCount() {
        return urgencyCount;
    }

    public void setUrgencyCount(int urgencyCount) {
        this.urgencyCount = urgencyCount;
    }

    public int getScheduleCount() {
        return scheduleCount;
    }

    public void setScheduleCount(int scheduleCount) {
        this.scheduleCount = scheduleCount;
    }

    public int getMeetingCount() {
        return meetingCount;
    }

    public void setMeetingCount(int meetingCount) {
        this.meetingCount = meetingCount;
    }

    public int getLogCount() {
        return logCount;
    }

    public void setLogCount(int logCount) {
        this.logCount = logCount;
    }
}
