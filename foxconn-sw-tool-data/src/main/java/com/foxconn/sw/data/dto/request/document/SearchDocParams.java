package com.foxconn.sw.data.dto.request.document;

import io.swagger.v3.oas.annotations.media.Schema;

public class SearchDocParams {
    private String documentName ;
    private String publisher;


    private String category;
    private String source;

    @Schema(description = "个人文档：personal,公共文档：public")
    private String fileType;

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
