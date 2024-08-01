package com.foxconn.sw.data.dto.entity.oa;

public class TaskAssignParams {

    private String taskId;

    private String assignEid;

    private String remark;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getAssignEid() {
        return assignEid;
    }

    public void setAssignEid(String assignEid) {
        this.assignEid = assignEid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
