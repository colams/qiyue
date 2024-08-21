package com.foxconn.sw.data.dto.entity.oa;

import com.foxconn.sw.data.dto.entity.ResourceVo;

import java.time.LocalDateTime;
import java.util.List;

public class TaskDetailVo {

    /**
     * 表主键ID
     */
    private Integer id;

    /**
     * 任务分类
     */
    private String topCategory;

    /**
     * 任务分类
     */
    private String category;

    /**
     * 任务标题-任务名称
     */
    private String title;

    /**
     * 任务分类
     */
    private String topProject;

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
     * 狀態描述信息
     */
    private InfoColorVo levelInfoVo;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 狀態描述信息
     */
    private InfoColorVo statusInfoVo;

    /**
     * 任务提出人
     */
    private String proposerEid;


    /**
     * 经办人
     */
    private String managerEid;

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

    /**
     *
     */
    private Integer progressPercent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopCategory() {
        return topCategory;
    }

    public void setTopCategory(String topCategory) {
        this.topCategory = topCategory;
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

    public String getTopProject() {
        return topProject;
    }

    public void setTopProject(String topProject) {
        this.topProject = topProject;
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

    public InfoColorVo getLevelInfoVo() {
        return levelInfoVo;
    }

    public void setLevelInfoVo(InfoColorVo levelInfoVo) {
        this.levelInfoVo = levelInfoVo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public InfoColorVo getStatusInfoVo() {
        return statusInfoVo;
    }

    public void setStatusInfoVo(InfoColorVo statusInfoVo) {
        this.statusInfoVo = statusInfoVo;
    }

    public String getProposerEid() {
        return proposerEid;
    }

    public void setProposerEid(String proposerEid) {
        this.proposerEid = proposerEid;
    }

    public String getManagerEid() {
        return managerEid;
    }

    public void setManagerEid(String managerEid) {
        this.managerEid = managerEid;
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

    public Integer getProgressPercent() {
        return progressPercent;
    }

    public void setProgressPercent(Integer progressPercent) {
        this.progressPercent = progressPercent;
    }
}
