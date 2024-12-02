package com.foxconn.sw.data.dto.entity.task;

import com.foxconn.sw.data.validate.ConstantsValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class TaskParams {


    @Schema(description = "编码信息")
    private String taskNo;

    @Schema(description = "标题信息")
    private String title;

    @Schema(description = "任务发起人")
    private String proposer;

    @Schema(description = "任务負責人")
    private String handler;

    @Schema(description = "状态")
    private List<Integer> statusList;

    @Schema(description = "专案")
    private String project;

    @Schema(description = "分类")
    private String category;

    @Schema(description = "創建時間開始")
    private String create_s;

    @Schema(description = "創建時間結束")
    private String create_e;

    @Schema(description = "截止時間開始")
    private String deadline_s;

    @Schema(description = "截止時間結束")
    private String deadline_e;

    @Schema(description = "模糊查询关键词")
    @Pattern(regexp = "^\\S*$", message = "字符串不能全部为空格")
    private String keyWord;

    @Schema(description = "请求分类信息：0-不限，1-MIL信息，2-进行中，3-新需求，4-待验收，5-逾期")
    @ConstantsValue(intValues = {0, 1, 2, 3, 4}, message = "请求类型不存在！")
    private Integer searchType;

    @Schema(description = "是否请求团队内容")
    private Integer isTeam;

    @Schema(description = "请求分类信息：0-不限，1-我的任务，2-我的关注")
    @ConstantsValue(intValues = {0, 1, 2}, message = "请求类型不存在！")
    private Integer viewType;

    private List<String> orderBys;

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreate_s() {
        return create_s;
    }

    public void setCreate_s(String create_s) {
        this.create_s = create_s;
    }

    public String getCreate_e() {
        return create_e;
    }

    public void setCreate_e(String create_e) {
        this.create_e = create_e;
    }

    public String getDeadline_s() {
        return deadline_s;
    }

    public void setDeadline_s(String deadline_s) {
        this.deadline_s = deadline_s;
    }

    public String getDeadline_e() {
        return deadline_e;
    }

    public void setDeadline_e(String deadline_e) {
        this.deadline_e = deadline_e;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public Integer getIsTeam() {
        return isTeam;
    }

    public void setIsTeam(Integer isTeam) {
        this.isTeam = isTeam;
    }

    public Integer getViewType() {
        return viewType;
    }

    public void setViewType(Integer viewType) {
        this.viewType = viewType;
    }

    public List<String> getOrderBys() {
        return orderBys;
    }

    public void setOrderBys(List<String> orderBys) {
        this.orderBys = orderBys;
    }
}
