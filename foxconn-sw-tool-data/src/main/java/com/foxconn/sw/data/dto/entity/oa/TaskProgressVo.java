package com.foxconn.sw.data.dto.entity.oa;

import java.time.LocalDateTime;

public class TaskProgressVo {

    private Integer id;

    private Integer taskid;

    private String operateEid;

    private String resourceIds;

    private String content;

    private String remark;

    private LocalDateTime createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public String getOperateEid() {
        return operateEid;
    }

    public void setOperateEid(String operateEid) {
        this.operateEid = operateEid;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
