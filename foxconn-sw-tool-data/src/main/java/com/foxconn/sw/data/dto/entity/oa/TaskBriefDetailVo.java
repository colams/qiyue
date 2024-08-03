package com.foxconn.sw.data.dto.entity.oa;

import java.util.List;

public class TaskBriefDetailVo {

    /**
     * 专案-所属项目
     */
    private String project;
    /**
     * 任务分类
     */
    private String category;

    /**
     * 优先等级
     */
    private String level;

    /**
     * 任务期限 YYYY-MM-DD
     */
    private String deadLine;

    /**
     * 任务标题-任务名称
     */
    private String title;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 附件信息
     */
    private List<Integer> resourceIds;

    /**
     * 经办人
     */
    private String managerEid;

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
}
