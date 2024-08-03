package com.foxconn.sw.data.dto.entity.oa;

import java.time.LocalDateTime;
import java.util.List;

public class TaskProgressVo {

    private Integer id;

    private Integer taskId;

    private String operateEid;

    private List<Integer> resourceIds;

    private List<String> resourcesUrl;

    private String content;

    private LocalDateTime createTime;

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

    public String getOperateEid() {
        return operateEid;
    }

    public void setOperateEid(String operateEid) {
        this.operateEid = operateEid;
    }

    public List<Integer> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public List<String> getResourcesUrl() {
        return resourcesUrl;
    }

    public void setResourcesUrl(List<String> resourcesUrl) {
        this.resourcesUrl = resourcesUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
