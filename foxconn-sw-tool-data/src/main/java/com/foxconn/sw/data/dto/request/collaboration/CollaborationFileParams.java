package com.foxconn.sw.data.dto.request.collaboration;

import org.springframework.web.multipart.MultipartFile;

public class CollaborationFileParams {
    private MultipartFile multipartFile;
    private String taskID;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }
}
