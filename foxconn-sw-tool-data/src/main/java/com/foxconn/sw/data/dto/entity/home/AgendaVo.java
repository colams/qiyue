package com.foxconn.sw.data.dto.entity.home;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AgendaVo {
    // 会议日期
    private String currentDate;
    // 所有任务
    private Integer taskTotalCount;
    // 会议时间
    private Integer meetingCount;
    // 超时任务
    private Integer taskOverdue;
    // 完成
    private Integer taskFinish;
    // 超时完成任务
    private Integer taskFinishOverdue;

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public Integer getTaskTotalCount() {
        return taskTotalCount;
    }

    public void setTaskTotalCount(Integer taskTotalCount) {
        this.taskTotalCount = taskTotalCount;
    }

    public Integer getTaskOverdue() {
        return taskOverdue;
    }

    public void setTaskOverdue(Integer taskOverdue) {
        this.taskOverdue = taskOverdue;
    }

    public Integer getTaskFinish() {
        return taskFinish;
    }

    public void setTaskFinish(Integer taskFinish) {
        this.taskFinish = taskFinish;
    }

    public Integer getTaskFinishOverdue() {
        return taskFinishOverdue;
    }

    public void setTaskFinishOverdue(Integer taskFinishOverdue) {
        this.taskFinishOverdue = taskFinishOverdue;
    }

    public Integer getMeetingCount() {
        return meetingCount;
    }

    public void setMeetingCount(Integer meetingCount) {
        this.meetingCount = meetingCount;
    }

    public void plusOne(Consumer<Integer> consumer, Supplier<Integer> supplier) {
        int i = Objects.isNull(supplier.get()) ? 0 : supplier.get();
        consumer.accept(i + 1);
    }

    public void plusNumber(Consumer<Integer> consumer, Supplier<Integer> supplier, Integer count) {
        int i = Objects.isNull(supplier.get()) ? 0 : supplier.get();
        consumer.accept(i + count);
    }

}
