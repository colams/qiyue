package com.foxconn.sw.data.dto.entity.task;

import com.foxconn.sw.data.dto.entity.ResourceVo;

import java.time.LocalDateTime;
import java.util.List;

public class TaskProgressVo {

    private Integer id;

    private Integer taskId;

    private String operateEid;

    private Integer progress;

    private List<Integer> resourceIds;

    private List<ResourceVo> resources;

    private String content;

    private LocalDateTime createTime;

    private Integer contentHistoryId;

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

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public List<Integer> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public List<ResourceVo> getResources() {
        return resources;
    }

    public void setResources(List<ResourceVo> resources) {
        this.resources = resources;
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

    public Integer getContentHistoryId() {
        return contentHistoryId;
    }

    public void setContentHistoryId(Integer contentHistoryId) {
        this.contentHistoryId = contentHistoryId;
    }
}
