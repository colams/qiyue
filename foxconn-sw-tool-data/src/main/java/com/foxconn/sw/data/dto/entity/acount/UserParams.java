package com.foxconn.sw.data.dto.entity.acount;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserParams {

    @Schema(description = "账号")
    private String employeeNo;

    @Schema(description = "姓名关键词")
    private String name;

    @Schema(description = "查询层级：某一个层级所有员工信息")
    private Integer level;

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
