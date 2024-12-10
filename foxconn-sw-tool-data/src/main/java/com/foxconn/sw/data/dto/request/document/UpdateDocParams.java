package com.foxconn.sw.data.dto.request.document;

import java.util.List;

public class UpdateDocParams {

    private Integer documentID;


    private String fileName;
    private String category;
    private String fileVersion;
    private String project;
    private Integer secretLevel;
    private Integer resourceID;
    private String expireDate;
    private Integer disableDown;
    private List<String> departmentIDs;
    private List<String> employeeNos;
    private String extra;
    private String content;

    public Integer getDocumentID() {
        return documentID;
    }

    public void setDocumentID(Integer documentID) {
        this.documentID = documentID;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDisableDown() {
        return disableDown;
    }

    public void setDisableDown(Integer disableDown) {
        this.disableDown = disableDown;
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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getSecretLevel() {
        return secretLevel;
    }

    public void setSecretLevel(Integer secretLevel) {
        this.secretLevel = secretLevel;
    }

    public Integer getResourceID() {
        return resourceID;
    }

    public void setResourceID(Integer resourceID) {
        this.resourceID = resourceID;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}
