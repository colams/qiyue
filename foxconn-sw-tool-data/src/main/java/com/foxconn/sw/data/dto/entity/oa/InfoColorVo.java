package com.foxconn.sw.data.dto.entity.oa;

public class InfoColorVo <T> {

    private T code;
    private String description;
    private String color;

    public InfoColorVo() {
    }

    public InfoColorVo(T code, String description) {
        this.code = code;
        this.description = description;
    }

    public InfoColorVo(T code, String description, String color) {
        this.code = code;
        this.description = description;
        this.color = color;
    }

    public T getCode() {
        return code;
    }

    public void setCode(T code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
