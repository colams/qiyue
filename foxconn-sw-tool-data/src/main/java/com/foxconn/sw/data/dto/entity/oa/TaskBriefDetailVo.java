package com.foxconn.sw.data.dto.entity.oa;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class TaskBriefDetailVo {

    /**
     * 专案-所属项目
     */
    @Schema(description = "任務ID")
    private Integer id;

    /**
     * 专案-所属项目
     */
    @Schema(description = "专案-所属项目")
    @NotNull(message = "专案不能爲空！")
    private String project;

    /**
     * 任务分类
     */
    @Schema(description = "任务分类")
    @NotNull(message = "任务分类不能爲空！")
    private String category;

    /**
     * 专案-所属项目
     */
    @Schema(description = "专案-所属项目")
    @NotNull(message = "专案不能爲空！")
    private String topProject;

    /**
     * 任务分类
     */
    @Schema(description = "任务分类")
    @NotNull(message = "任务分类不能爲空！")
    private String topCategory;


    /**
     * 优先等级
     */
    @Schema(description = "优先等级")
    @NotNull(message = "优先等级不能爲空！")
    private String level;

    /**
     * 任务期限 YYYY-MM-DD
     */
    @Schema(description = "任务期限 YYYY-MM-DD")
    @NotNull(message = "任务期限不能爲空！")
    private String deadLine;

    /**
     * 任务标题-任务名称
     */
    @Schema(description = "任务标题-任务名称")
    @NotNull(message = "任務標題不能爲空！")
    private String title;

    /**
     * 任务描述
     */
    @Schema(description = "任务描述")
    @NotNull(message = "任務描述不能爲空！")
    private String description;

    /**
     * 附件信息
     */
    @Schema(description = "上传附件ID集合")
    private List<Integer> resourceIds;

    /**
     * 关注人
     */
    @Schema(description = "关注人")
    private List<String> watchers;

    /**
     * 关注人
     */
    @Schema(description = "关注人")
    private List<String> managers;

    /**
     * 状态
     */
    @Schema(description = "0-暂存/草稿，1-待确认需求")
    private Integer status;

    private List<CapexParamsVo> capexParamsVos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTopProject() {
        return topProject;
    }

    public void setTopProject(String topProject) {
        this.topProject = topProject;
    }

    public String getTopCategory() {
        return topCategory;
    }

    public void setTopCategory(String topCategory) {
        this.topCategory = topCategory;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public List<String> getWatchers() {
        return watchers;
    }

    public void setWatchers(List<String> watchers) {
        this.watchers = watchers;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getManagers() {
        return managers;
    }

    public void setManagers(List<String> managers) {
        this.managers = managers;
    }

    public List<CapexParamsVo> getCapexParamsVos() {
        return capexParamsVos;
    }

    public void setCapexParamsVos(List<CapexParamsVo> capexParamsVos) {
        this.capexParamsVos = capexParamsVos;
    }
}
