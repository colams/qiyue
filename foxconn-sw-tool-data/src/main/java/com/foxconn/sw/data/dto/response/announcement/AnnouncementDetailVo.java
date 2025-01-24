package com.foxconn.sw.data.dto.response.announcement;

import com.foxconn.sw.data.dto.entity.ResourceVo;

import java.util.List;

public class AnnouncementDetailVo {

    private Integer id;
    private String title;
    private String content;
    private String operator;
    private String releaseDate;
    private List<ResourceVo> resourceVos;


    private String category;

    private String expiryDate;

    private Integer top;


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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<ResourceVo> getResourceVos() {
        return resourceVos;
    }

    public void setResourceVos(List<ResourceVo> resourceVos) {
        this.resourceVos = resourceVos;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }
}
