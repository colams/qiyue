package com.foxconn.sw.data.dto.request.task;

public class SubTaskParams {
    private Integer parentTaskID;
    private String title;
    private String handler;
    private String deadline;

    public Integer getParentTaskID() {
        return parentTaskID;
    }

    public void setParentTaskID(Integer parentTaskID) {
        this.parentTaskID = parentTaskID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
