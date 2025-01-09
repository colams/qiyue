package com.foxconn.sw.data.dto.request.document;

import java.util.List;

public class CreateDocParams {

    private String fileName;
    private String category;
    private String fileVersion;
    private String project;
    private Integer secretLevel;
    private Integer resourceID;
    private String expireDate;
    private Integer disableDown;
    private String author;
    private List<String> departmentIDs;
    private List<String> employeeNos;
    private String extra;

    private String workType;
    private String mainType;
    private String subType;
    private String mainPart;
    private String supplier;
    private String source;
    private String deadLine;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFileVersion() {
        return fileVersion;
    }

    public void setFileVersion(String fileVersion) {
        this.fileVersion = fileVersion;
    }

    public Integer getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(Integer secretLevel) {
        this.secretLevel = secretLevel;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getResourceID() {
        return resourceID;
    }

    public void setResourceID(Integer resourceID) {
        this.resourceID = resourceID;
    }

    public Integer getDisableDown() {
        return disableDown;
    }

    public void setDisableDown(Integer disableDown) {
        this.disableDown = disableDown;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getDepartmentIDs() {
        return departmentIDs;
    }

    public void setDepartmentIDs(List<String> departmentIDs) {
        this.departmentIDs = departmentIDs;
    }

    public List<String> getEmployeeNos() {
        return employeeNos;
    }

    public void setEmployeeNos(List<String> employeeNos) {
        this.employeeNos = employeeNos;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getMainType() {
        return mainType;
    }

    public void setMainType(String mainType) {
        this.mainType = mainType;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getMainPart() {
        return mainPart;
    }

    public void setMainPart(String mainPart) {
        this.mainPart = mainPart;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }
}
