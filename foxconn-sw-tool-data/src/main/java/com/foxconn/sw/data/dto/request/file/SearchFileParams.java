package com.foxconn.sw.data.dto.request.file;

public class SearchFileParams {

    private String fileName;
    private String category;
    private String source;
    private String publisher;
    private String author;

    private String phase;
    private String config;
    private String issueMode;
    private String process;
    private String stage;
    private String mainPart;


    private String supplier;
    private boolean isArchive;
    private boolean isRelease;
    private boolean hasDfm;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getIssueMode() {
        return issueMode;
    }

    public void setIssueMode(String issueMode) {
        this.issueMode = issueMode;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
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

    public boolean isArchive() {
        return isArchive;
    }

    public void setArchive(boolean archive) {
        isArchive = archive;
    }

    public boolean isRelease() {
        return isRelease;
    }

    public void setRelease(boolean release) {
        isRelease = release;
    }

    public boolean isHasDfm() {
        return hasDfm;
    }

    public void setHasDfm(boolean hasDfm) {
        this.hasDfm = hasDfm;
    }
}
