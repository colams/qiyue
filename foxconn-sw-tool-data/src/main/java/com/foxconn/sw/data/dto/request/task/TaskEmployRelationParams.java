package com.foxconn.sw.data.dto.request.task;

public class TaskEmployRelationParams {

    private Long taskID;

    /**
     * 角色類型：TaskRoleFlagEnums,
     * 0001 任务发起人    1
     * 0010 任务经办人    2
     * 0100 任务DRI 处理人    4
     * 1000 任务关注者    8
     */
    private Integer role;

    private String employeeNo;
    /**
     * 0-刪除，1-新增
     */
    private Integer operateType;

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }
}
