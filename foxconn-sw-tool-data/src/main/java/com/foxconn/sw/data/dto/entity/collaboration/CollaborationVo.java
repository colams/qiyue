package com.foxconn.sw.data.dto.entity.collaboration;

import com.foxconn.sw.data.dto.entity.ResourceVo;

import java.util.List;
import java.util.Map;

public class CollaborationVo {

    private List<String> headers;
    private List<Map<String, Object>> content;
    private ResourceVo resource;
    private Long taskNo;
    private String taskTitle;
    private Boolean canSubmit;
    private Boolean canFinish;

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<Map<String, Object>> getContent() {
        return content;
    }

    public void setContent(List<Map<String, Object>> content) {
        this.content = content;
    }

    public ResourceVo getResource() {
        return resource;
    }

    public void setResource(ResourceVo resource) {
        this.resource = resource;
    }

    public Long getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(Long taskNo) {
        this.taskNo = taskNo;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Boolean getCanSubmit() {
        return canSubmit;
    }

    public void setCanSubmit(Boolean canSubmit) {
        this.canSubmit = canSubmit;
    }

    public Boolean getCanFinish() {
        return canFinish;
    }

    public void setCanFinish(Boolean canFinish) {
        this.canFinish = canFinish;
    }
}
