package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwDocument {
    private Integer id;

    private String documentName;

    private String fileVersion;

    private String category;

    private Integer department;

    private String project;

    private Integer secretLevel;

    private String expireDate;

    private Integer disableDown;

    private Integer resourceId;

    private String author;

    private String creator;

    private Integer fileType;

    private String content;

    private String workType;

    private String mainType;

    private String subType;

    private String mainPart;

    private String supplier;

    private String source;

    private String deadLine;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwDocument withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public SwDocument withDocumentName(String documentName) {
        this.setDocumentName(documentName);
        return this;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName == null ? null : documentName.trim();
    }

    public String getFileVersion() {
        return fileVersion;
    }

    public SwDocument withFileVersion(String fileVersion) {
        this.setFileVersion(fileVersion);
        return this;
    }

    public void setFileVersion(String fileVersion) {
        this.fileVersion = fileVersion == null ? null : fileVersion.trim();
    }

    public String getCategory() {
        return category;
    }

    public SwDocument withCategory(String category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Integer getDepartment() {
        return department;
    }

    public SwDocument withDepartment(Integer department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getProject() {
        return project;
    }

    public SwDocument withProject(String project) {
        this.setProject(project);
        return this;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public Integer getSecretLevel() {
        return secretLevel;
    }

    public SwDocument withSecretLevel(Integer secretLevel) {
        this.setSecretLevel(secretLevel);
        return this;
    }

    public void setSecretLevel(Integer secretLevel) {
        this.secretLevel = secretLevel;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public SwDocument withExpireDate(String expireDate) {
        this.setExpireDate(expireDate);
        return this;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate == null ? null : expireDate.trim();
    }

    public Integer getDisableDown() {
        return disableDown;
    }

    public SwDocument withDisableDown(Integer disableDown) {
        this.setDisableDown(disableDown);
        return this;
    }

    public void setDisableDown(Integer disableDown) {
        this.disableDown = disableDown;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public SwDocument withResourceId(Integer resourceId) {
        this.setResourceId(resourceId);
        return this;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getAuthor() {
        return author;
    }

    public SwDocument withAuthor(String author) {
        this.setAuthor(author);
        return this;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getCreator() {
        return creator;
    }

    public SwDocument withCreator(String creator) {
        this.setCreator(creator);
        return this;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Integer getFileType() {
        return fileType;
    }

    public SwDocument withFileType(Integer fileType) {
        this.setFileType(fileType);
        return this;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getContent() {
        return content;
    }

    public SwDocument withContent(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getWorkType() {
        return workType;
    }

    public SwDocument withWorkType(String workType) {
        this.setWorkType(workType);
        return this;
    }

    public void setWorkType(String workType) {
        this.workType = workType == null ? null : workType.trim();
    }

    public String getMainType() {
        return mainType;
    }

    public SwDocument withMainType(String mainType) {
        this.setMainType(mainType);
        return this;
    }

    public void setMainType(String mainType) {
        this.mainType = mainType == null ? null : mainType.trim();
    }

    public String getSubType() {
        return subType;
    }

    public SwDocument withSubType(String subType) {
        this.setSubType(subType);
        return this;
    }

    public void setSubType(String subType) {
        this.subType = subType == null ? null : subType.trim();
    }

    public String getMainPart() {
        return mainPart;
    }

    public SwDocument withMainPart(String mainPart) {
        this.setMainPart(mainPart);
        return this;
    }

    public void setMainPart(String mainPart) {
        this.mainPart = mainPart == null ? null : mainPart.trim();
    }

    public String getSupplier() {
        return supplier;
    }

    public SwDocument withSupplier(String supplier) {
        this.setSupplier(supplier);
        return this;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }

    public String getSource() {
        return source;
    }

    public SwDocument withSource(String source) {
        this.setSource(source);
        return this;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getDeadLine() {
        return deadLine;
    }

    public SwDocument withDeadLine(String deadLine) {
        this.setDeadLine(deadLine);
        return this;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine == null ? null : deadLine.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public SwDocument withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwDocument withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwDocument withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}