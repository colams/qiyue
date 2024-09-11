package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwTask {
    private Integer id;

    private Long taskNo;

    private String topCategory;

    private String category;

    private String title;

    private String topProject;

    private String project;

    private String level;

    private Integer progressPercent;

    private Integer status;

    private Integer rejectStatus;

    private String proposerEid;

    private String managerEid;

    private String handleEid;

    private String deadLine;

    private String resourceIds;

    private String startDate;

    private String endDate;

    private String reflection;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    private String description;

    public Integer getId() {
        return id;
    }

    public SwTask withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTaskNo() {
        return taskNo;
    }

    public SwTask withTaskNo(Long taskNo) {
        this.setTaskNo(taskNo);
        return this;
    }

    public void setTaskNo(Long taskNo) {
        this.taskNo = taskNo;
    }

    public String getTopCategory() {
        return topCategory;
    }

    public SwTask withTopCategory(String topCategory) {
        this.setTopCategory(topCategory);
        return this;
    }

    public void setTopCategory(String topCategory) {
        this.topCategory = topCategory == null ? null : topCategory.trim();
    }

    public String getCategory() {
        return category;
    }

    public SwTask withCategory(String category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getTitle() {
        return title;
    }

    public SwTask withTitle(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTopProject() {
        return topProject;
    }

    public SwTask withTopProject(String topProject) {
        this.setTopProject(topProject);
        return this;
    }

    public void setTopProject(String topProject) {
        this.topProject = topProject == null ? null : topProject.trim();
    }

    public String getProject() {
        return project;
    }

    public SwTask withProject(String project) {
        this.setProject(project);
        return this;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public String getLevel() {
        return level;
    }

    public SwTask withLevel(String level) {
        this.setLevel(level);
        return this;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Integer getProgressPercent() {
        return progressPercent;
    }

    public SwTask withProgressPercent(Integer progressPercent) {
        this.setProgressPercent(progressPercent);
        return this;
    }

    public void setProgressPercent(Integer progressPercent) {
        this.progressPercent = progressPercent;
    }

    public Integer getStatus() {
        return status;
    }

    public SwTask withStatus(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRejectStatus() {
        return rejectStatus;
    }

    public SwTask withRejectStatus(Integer rejectStatus) {
        this.setRejectStatus(rejectStatus);
        return this;
    }

    public void setRejectStatus(Integer rejectStatus) {
        this.rejectStatus = rejectStatus;
    }

    public String getProposerEid() {
        return proposerEid;
    }

    public SwTask withProposerEid(String proposerEid) {
        this.setProposerEid(proposerEid);
        return this;
    }

    public void setProposerEid(String proposerEid) {
        this.proposerEid = proposerEid == null ? null : proposerEid.trim();
    }

    public String getManagerEid() {
        return managerEid;
    }

    public SwTask withManagerEid(String managerEid) {
        this.setManagerEid(managerEid);
        return this;
    }

    public void setManagerEid(String managerEid) {
        this.managerEid = managerEid == null ? null : managerEid.trim();
    }

    public String getHandleEid() {
        return handleEid;
    }

    public SwTask withHandleEid(String handleEid) {
        this.setHandleEid(handleEid);
        return this;
    }

    public void setHandleEid(String handleEid) {
        this.handleEid = handleEid == null ? null : handleEid.trim();
    }

    public String getDeadLine() {
        return deadLine;
    }

    public SwTask withDeadLine(String deadLine) {
        this.setDeadLine(deadLine);
        return this;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine == null ? null : deadLine.trim();
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public SwTask withResourceIds(String resourceIds) {
        this.setResourceIds(resourceIds);
        return this;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds == null ? null : resourceIds.trim();
    }

    public String getStartDate() {
        return startDate;
    }

    public SwTask withStartDate(String startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public SwTask withEndDate(String endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public String getReflection() {
        return reflection;
    }

    public SwTask withReflection(String reflection) {
        this.setReflection(reflection);
        return this;
    }

    public void setReflection(String reflection) {
        this.reflection = reflection == null ? null : reflection.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwTask withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwTask withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public SwTask withDescription(String description) {
        this.setDescription(description);
        return this;
    }
}