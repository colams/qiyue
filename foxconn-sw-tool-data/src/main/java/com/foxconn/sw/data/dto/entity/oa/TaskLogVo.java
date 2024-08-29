package com.foxconn.sw.data.dto.entity.oa;

import java.time.LocalDateTime;

public class TaskLogVo {

    private Integer id;

    private Integer taskId;

    private String operator;

    private String content;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}
