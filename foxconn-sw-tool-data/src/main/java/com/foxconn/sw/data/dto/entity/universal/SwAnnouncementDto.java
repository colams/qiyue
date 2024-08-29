package com.foxconn.sw.data.dto.entity.universal;

import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public class SwAnnouncementDto {

    private Integer id;

    @Pattern(regexp = "^\\S*$", message = "字符串不能全部为空格")
    private String title;

    @Pattern(regexp = "^\\S*$", message = "字符串不能全部为空格")
    private String content;

    private String operator;

    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
