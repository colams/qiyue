package com.foxconn.sw.data.dto.response.user;

import java.util.List;

public class SubordinateVo {

    private Integer id;

    private String name;

    private String employeeNo;

    private Integer departmentId;

    private List<String> departments;

    private String innerEmail;

    private Integer status;

    private String identityOfCadre;

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

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public List<String> getDepartments() {
        return departments;
    }

    public void setDepartments(List<String> departments) {
        this.departments = departments;
    }

    public String getInnerEmail() {
        return innerEmail;
    }

    public void setInnerEmail(String innerEmail) {
        this.innerEmail = innerEmail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIdentityOfCadre() {
        return identityOfCadre;
    }

    public void setIdentityOfCadre(String identityOfCadre) {
        this.identityOfCadre = identityOfCadre;
    }
}
