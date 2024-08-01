package com.foxconn.sw.data.dto.entity.oa;

import java.time.LocalDateTime;

public class TaskBriefVo {

    /**
     * 表主键ID
     */
    private Integer id;

    /**
     * 任务分类
     */
    private String category;

    /**
     * 任务标题-任务名称
     */
    private String title;

    /**
     * 专案-所属项目
     */
    private String project;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 优先等级
     */
    private String level;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 任务提出人
     */
    private String proposerEid;

    /**
     * 任务处理人
     */
    private String handleEid;

    /**
     * 任务期限 YYYY-MM-DD
     */
    private String deadLine;

    /**
     * 任务开始时间 YYYY-MM-DD
     */
    private String startDate;

    /**
     * 任务结束时间 YYYY-MM-DD
     */
    private String endDate;

    /**
     * 任务创建时间 YYYY-MM-DD HH:mm:ss
     */
    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProposerEid() {
        return proposerEid;
    }

    public void setProposerEid(String proposerEid) {
        this.proposerEid = proposerEid;
    }

    public String getHandleEid() {
        return handleEid;
    }

    public void setHandleEid(String handleEid) {
        this.handleEid = handleEid;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
