package com.foxconn.sw.data.dto.entity.oa;

import com.foxconn.sw.data.validate.ConstantsValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

public class TaskParams {


    @Schema(description = "编码信息")
    private Long taskNo;

    @Schema(description = "标题信息")
    private String title;

    @Schema(description = "任务发起人")
    private String proposer;

    @Schema(description = "专案")
    private String project;

    @Schema(description = "分类")
    private String category;

    @Schema(description = "分类")
    private String create_s;

    @Schema(description = "分类")
    private String create_e;



    @Schema(description = "模糊查询关键词")
    @Pattern(regexp = "^\\S*$", message = "字符串不能全部为空格")
    private String keyWord;

    @Schema(description = "请求分类信息：0-不限，1-MIL信息，2-进行中，3-新需求，4-待验收，5-逾期")
    @ConstantsValue(intValues = {0, 1, 2, 3, 4}, message = "请求类型不存在！")
    private Integer searchType;

    @Schema(description = "是否请求团队内容")
    private Integer isTeam;

    public Long getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(Long taskNo) {
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
}
