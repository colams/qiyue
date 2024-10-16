package com.foxconn.sw.data.dto.request.document;

public class ReviseDocParams {
    private Integer documentID;
    private String fileName;
    private Integer category;
    private String source ;
    private Integer sourceID;
    private Integer resourceID;

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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getSourceID() {
        return sourceID;
    }

    public void setSourceID(Integer sourceID) {
        this.sourceID = sourceID;
    }

    public Integer getResourceID() {
        return resourceID;
    }

    public void setResourceID(Integer resourceID) {
        this.resourceID = resourceID;
    }
}
