package com.foxconn.sw.data.dto.entity;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;

public class ResourceVo {

    private Integer id;
    private String name;
    private String url;
    private String viewUrl;
//    private String filePath;
    private EmployeeVo operator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getViewUrl() {
        return viewUrl;
    }

    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
    }

//    public String getFilePath() {
//        return filePath;
//    }
//
//    public void setFilePath(String filePath) {
//        this.filePath = filePath;
//    }

    public EmployeeVo getOperator() {
        return operator;
    }

    public void setOperator(EmployeeVo operator) {
        this.operator = operator;
    }
}
