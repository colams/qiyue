package com.foxconn.sw.data.dto.entity.oa;

public class InfoColorVo {

    private Integer code;
    private String description;
    private String color;

    public InfoColorVo() {
    }

    public InfoColorVo(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public InfoColorVo(Integer code, String description, String color) {
        this.code = code;
        this.description = description;
        this.color = color;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
