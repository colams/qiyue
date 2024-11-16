package com.foxconn.sw.data.dto.request.collaboration;

import jakarta.validation.constraints.NotNull;

import java.util.Map;

public class CollaborationUpdateParams {
    @NotNull
    private Integer taskID;
    private Long id;
    private Map<String, String> content;

    private String header;
    private Map<Integer, String> colPair;

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public void setContent(Map<String, String> content) {
        this.content = content;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Map<Integer, String> getColPair() {
        return colPair;
    }

    public void setColPair(Map<Integer, String> colPair) {
        this.colPair = colPair;
    }
}
