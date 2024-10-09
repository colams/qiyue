package com.foxconn.sw.data.dto.entity.oa;

import com.foxconn.sw.data.dto.entity.universal.OperateEntity;

import java.time.LocalDateTime;
import java.util.List;

public class TaskBriefListVo {

    /**
     * 表主键ID
     */
    private Integer id;

    private Long taskNo;

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
     * 优先等级
     */
    private String level;


    /**
     * 优先等级
     */
    private InfoColorVo levelInfo;

    /**
     * 处理进度
     */
    private int progressPercent;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 状态描述信息
     */
    private InfoColorVo statusInfoVo;

    /**
     * 任务提出人
     */
    private String proposer;

    /**
     * 任务期限 YYYY-MM-DD
     */
    private String deadLine;

    /**
     * 任务创建时间 YYYY-MM-DD HH:mm:ss
     */
    private LocalDateTime createTime;

    /**
     * 支持的操作列表
     */
    private List<OperateEntity> operateList;

    /**
     * 支持的操作列表
     */
    private Integer rejectStatus;

    /**
     * 跟进状态
     */
    private Integer followStatus;

    private String supervisor;

    private String taskType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(Long taskNo) {
        this.taskNo = taskNo;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public InfoColorVo getLevelInfo() {
        return levelInfo;
    }

    public void setLevelInfo(InfoColorVo levelInfo) {
        this.levelInfo = levelInfo;
    }

    public int getProgressPercent() {
        return progressPercent;
    }

    public void setProgressPercent(int progressPercent) {
        this.progressPercent = progressPercent;
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

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public List<OperateEntity> getOperateList() {
        return operateList;
    }

    public void setOperateList(List<OperateEntity> operateList) {
        this.operateList = operateList;
    }

    public Integer getRejectStatus() {
        return rejectStatus;
    }

    public void setRejectStatus(Integer rejectStatus) {
        this.rejectStatus = rejectStatus;
    }

    public Integer getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(Integer followStatus) {
        this.followStatus = followStatus;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}
