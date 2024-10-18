package com.foxconn.sw.data.entity;

public class SwDocumentPermission {
    private Integer id;

    private Integer documentId;

    private Integer permissionType;

    private String permissionValue;

    public Integer getId() {
        return id;
    }

    public SwDocumentPermission withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public SwDocumentPermission withDocumentId(Integer documentId) {
        this.setDocumentId(documentId);
        return this;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public Integer getPermissionType() {
        return permissionType;
    }

    public SwDocumentPermission withPermissionType(Integer permissionType) {
        this.setPermissionType(permissionType);
        return this;
    }

    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public SwDocumentPermission withPermissionValue(String permissionValue) {
        this.setPermissionValue(permissionValue);
        return this;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue == null ? null : permissionValue.trim();
    }
}