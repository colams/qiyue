package com.foxconn.sw.data.dto.entity.oa;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class TaskBriefDetailVo {

    /**
     * 专案-所属项目
     */
    @Schema(description = "专案-所属项目")
    @NotNull(message = "专案不能爲空！")
    @Pattern(regexp = "^(?!\\s*$)\\S+.*$", message = "专案不能全部为空格")
    private String topProject;


    /**
     * 专案-所属项目
     */
    @Schema(description = "专案-所属项目")
    @NotNull(message = "专案不能爲空！")
    @Pattern(regexp = "^(?!\\s*$)\\S+.*$", message = "专案不能全部为空格")
    private String project;

    /**
     * 任务分类
     */
    @Schema(description = "任务分类")
    @NotNull(message = "任务分类不能爲空！")
    @Pattern(regexp = "^(?!\\s*$)\\S+.*$", message = "任务分类不能全部为空格")
    private String topCategory;

    /**
     * 任务分类
     */
    @Schema(description = "任务分类")
    @NotNull(message = "任务分类不能爲空！")
    @Pattern(regexp = "^(?!\\s*$)\\S+.*$", message = "任务分类不能全部为空格")
    private String category;

    /**
     * 优先等级
     */
    @Schema(description = "优先等级")
    @NotNull(message = "优先等级不能爲空！")
    @Pattern(regexp = "^(?!\\s*$)\\S+.*$", message = "优先等级不能全部为空格")
    private String level;

    /**
     * 任务期限 YYYY-MM-DD
     */
    @Schema(description = "任务期限 YYYY-MM-DD")
    @NotNull(message = "任务期限不能爲空！")
    @Pattern(regexp = "^(?!\\s*$)\\S+.*$", message = "任务期限不能全部为空格")
    private String deadLine;

    /**
     * 任务标题-任务名称
     */
    @Schema(description = "任务标题-任务名称")
    @NotNull(message = "任務標題不能爲空！")
    @Pattern(regexp = "^(?!\\s*$)\\S+.*$", message = "任務標題不能全部为空格")
    private String title;

    /**
     * 任务描述
     */
    @Schema(description = "任务描述")
    @NotNull(message = "任務描述不能爲空！")
    @Pattern(regexp = "^(?!\\s*$)\\S+.*$", message = "任务描述不能全部为空格")
    private String description;

    /**
     * 附件信息
     */
    @Schema(description = "上传附件ID集合")
    private List<Integer> resourceIds;

    /**
     * 经办人
     */
    @Schema(description = "中间人")
    private String managerEid;

    /**
     * 状态
     */
    @Schema(description = "0-暂存/草稿，1-待确认需求")
    private Integer status;

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

    public String getManagerEid() {
        return managerEid;
    }

    public void setManagerEid(String managerEid) {
        this.managerEid = managerEid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
