package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwToolRunResult {
    private Integer id;

    private String operator;

    private String toolName;

    private String runResult;

    private Long interval;

    private String remark;

    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public SwToolRunResult withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public SwToolRunResult withOperator(String operator) {
        this.setOperator(operator);
        return this;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getToolName() {
        return toolName;
    }

    public SwToolRunResult withToolName(String toolName) {
        this.setToolName(toolName);
        return this;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName == null ? null : toolName.trim();
    }

    public String getRunResult() {
        return runResult;
    }

    public SwToolRunResult withRunResult(String runResult) {
        this.setRunResult(runResult);
        return this;
    }

    public void setRunResult(String runResult) {
        this.runResult = runResult == null ? null : runResult.trim();
    }

    public Long getInterval() {
        return interval;
    }

    public SwToolRunResult withInterval(Long interval) {
        this.setInterval(interval);
        return this;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public String getRemark() {
        return remark;
    }

    public SwToolRunResult withRemark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwToolRunResult withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}