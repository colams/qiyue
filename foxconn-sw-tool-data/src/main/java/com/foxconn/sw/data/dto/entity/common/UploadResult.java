package com.foxconn.sw.data.dto.entity.common;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "上传结果")
public class UploadResult {

    @Schema(description = "文件保存的Id")
    private Integer fileId;
    @Schema(description = "文件保存路径")
    private String filePath;


    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
