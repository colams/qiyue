package com.foxconn.sw.data.dto.entity.oa;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.universal.OperateEntity;

import java.time.LocalDateTime;
import java.util.List;

public class TaskDetailVo {

    /**
     * 表主键ID
     */
    private Integer id;

    private Long taskNo;

    /**
     * 任务分类
     */
    private String topCategory;

    /**
     * 任务分类
     */
    private String category;

    /**
     * 任务分类
     */
    private String categoryCode;

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
    private EmployeeVo proposer;


    /**
     * 经办人
     */
    private List<EmployeeVo> manager;

    /**
     * 任务处理人
     */
    private EmployeeVo handle;

    /**
     * 任务处理人
     */
    private List<EmployeeVo> watchers;

    /**
     * 任务期限 YYYY-MM-DD
     */
    private String deadLine;

    /**
     * 任务创建时间 YYYY-MM-DD HH:mm:ss
     */
    private LocalDateTime createTime;

    /**
     *
     */
    private Integer progressPercent;

    /**
     * 任务完成时间 YYYY-MM-DD HH:mm:ss
     */
    private String finishTime;


    /**
     * 支持的操作列表
     */
    private List<OperateEntity> operateList;

    private String reflection;

    private Integer rejectStatus;

    private Boolean isCollaboration;


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

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
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

    public EmployeeVo getProposer() {
        return proposer;
    }

    public void setProposer(EmployeeVo proposer) {
        this.proposer = proposer;
    }

    public List<EmployeeVo> getManager() {
        return manager;
    }

    public void setManager(List<EmployeeVo> manager) {
        this.manager = manager;
    }

    public EmployeeVo getHandle() {
        return handle;
    }

    public void setHandle(EmployeeVo handle) {
        this.handle = handle;
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

    public Integer getProgressPercent() {
        return progressPercent;
    }

    public void setProgressPercent(Integer progressPercent) {
        this.progressPercent = progressPercent;
    }

    public List<OperateEntity> getOperateList() {
        return operateList;
    }

    public void setOperateList(List<OperateEntity> operateList) {
        this.operateList = operateList;
    }

    public String getReflection() {
        return reflection;
    }

    public void setReflection(String reflection) {
        this.reflection = reflection;
    }

    public Integer getRejectStatus() {
        return rejectStatus;
    }

    public void setRejectStatus(Integer rejectStatus) {
        this.rejectStatus = rejectStatus;
    }

    public List<EmployeeVo> getWatchers() {
        return watchers;
    }

    public void setWatchers(List<EmployeeVo> watchers) {
        this.watchers = watchers;
    }

    public Boolean getCollaboration() {
        return isCollaboration;
    }

    public void setCollaboration(Boolean collaboration) {
        isCollaboration = collaboration;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
