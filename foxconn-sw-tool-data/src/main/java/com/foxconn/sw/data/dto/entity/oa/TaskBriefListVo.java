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
     * 专案-所属项目
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
     * 专案-所属项目
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
     * 任务处理人
     */
    private String handler;

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

    private String handler2;
    private String handlerEID;
    private String managerEID;
    private String proposerEID;
    private Integer nextID;

    public String getHandler2() {
        return handler2;
    }

    public void setHandler2(String handler2) {
        this.handler2 = handler2;
    }

    public Integer getNextID() {
        return nextID;
    }

    public void setNextID(Integer nextID) {
        this.nextID = nextID;
    }

    public String getManagerEID() {
        return managerEID;
    }

    public void setManagerEID(String managerEID) {
        this.managerEID = managerEID;
    }

    public String getHandlerEID() {
        return handlerEID;
    }

    public void setHandlerEID(String handlerEID) {
        this.handlerEID = handlerEID;
    }

    public String getProposerEID() {
        return proposerEID;
    }

    public void setProposerEID(String proposerEID) {
        this.proposerEID = proposerEID;
    }

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

    public InfoColorVo getLevelInfo() {
        return levelInfo;
    }

    public void setLevelInfo(InfoColorVo levelInfo) {
        this.levelInfo = levelInfo;
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

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
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

}
