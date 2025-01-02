package com.foxconn.sw.data.dto.request.collaboration;

public class CollaborationUpdateCellParams {
    private Long detailID;
    private Integer taskID;
    private String value;

    public Long getDetailID() {
        return detailID;
    }

    public void setDetailID(Long detailID) {
        this.detailID = detailID;
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
