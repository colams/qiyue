package com.foxconn.sw.data.dto.entity.task;

public class TaskOverviewVo {

    private int code;
    private String category;
    private String title;
    private String countDes;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountDes() {
        return countDes;
    }

    public void setCountDes(String countDes) {
        this.countDes = countDes;
    }
}
