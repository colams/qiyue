package com.foxconn.sw.data.dto.request.document;

public class ReviseDocParams {

    private Integer documentID;
    private String fileName;
    private String source;
    private Integer sourceID;

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
}
