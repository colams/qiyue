package com.foxconn.sw.data.dto.request.account;

public class CreateAccountParams {

    private Integer id;
    private String name;
    private Integer departmentId;
    private String innerEmail;
    private String employeeNo;

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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getInnerEmail() {
        return innerEmail;
    }

    public void setInnerEmail(String innerEmail) {
        this.innerEmail = innerEmail;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }
}
