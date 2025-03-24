package com.foxconn.sw.data.dto.request.project;

import java.time.LocalDateTime;

public class SwProjectDocTree {
    private Integer id;

    private Integer projectId;

    private Integer parentId;

    private String name;

    private String nodeKey;

    private Integer nodeLevel;

    private Integer isLeaf;

    private Integer priority;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public Integer getId() {
        return id;
    }

    public SwProjectDocTree withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public SwProjectDocTree withProjectId(Integer projectId) {
        this.setProjectId(projectId);
        return this;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public SwProjectDocTree withParentId(Integer parentId) {
        this.setParentId(parentId);
        return this;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public SwProjectDocTree withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNodeKey() {
        return nodeKey;
    }

    public SwProjectDocTree withNodeKey(String nodeKey) {
        this.setNodeKey(nodeKey);
        return this;
    }

    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey == null ? null : nodeKey.trim();
    }

    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public SwProjectDocTree withNodeLevel(Integer nodeLevel) {
        this.setNodeLevel(nodeLevel);
        return this;
    }

    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public SwProjectDocTree withIsLeaf(Integer isLeaf) {
        this.setIsLeaf(isLeaf);
        return this;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Integer getPriority() {
        return priority;
    }

    public SwProjectDocTree withPriority(Integer priority) {
        this.setPriority(priority);
        return this;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public SwProjectDocTree withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwProjectDocTree withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public SwProjectDocTree withModifyTime(LocalDateTime modifyTime) {
        this.setModifyTime(modifyTime);
        return this;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }
}