package com.foxconn.sw.data.dto.entity.document;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;

import java.util.List;

public class DocumentDetailVo {

    private Integer documentID;
    private String category;
    private String documentName;
    private String downloadUrl;
    private String viewUrl;
    private String project;
    private String description;
    private String department;
    private String fileVersion;

    private Boolean canView;
    private Integer disableDown;
    private Boolean canUpdate;

    private EmployeeVo publisher;
    private String createTime;
    private String updateTime;
    private String title;
    private Integer level;


    private List<String> departmentIDs;
    private List<String> employeeNos;
    private String extra;

    public Integer getDocumentID() {
        return documentID;
    }

    public void setDocumentID(Integer documentID) {
        this.documentID = documentID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getViewUrl() {
        return viewUrl;
    }

    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFileVersion() {
        return fileVersion;
    }

    public void setFileVersion(String fileVersion) {
        this.fileVersion = fileVersion;
    }

    public Boolean getCanView() {
        return canView;
    }

    public void setCanView(Boolean canView) {
        this.canView = canView;
    }

    public Integer getDisableDown() {
        return disableDown;
    }

    public void setDisableDown(Integer disableDown) {
        this.disableDown = disableDown;
    }

    public EmployeeVo getPublisher() {
        return publisher;
    }

    public void setPublisher(EmployeeVo publisher) {
        this.publisher = publisher;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(Boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
}
