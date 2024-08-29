package com.foxconn.sw.data.dto.entity.acount;

import com.alibaba.excel.annotation.ExcelProperty;

public class AddressBookVo {

    @ExcelProperty(value = "處級", index = 0)
    private String seniorDepart;
    @ExcelProperty(value = "部門", index = 1)
    private String department;
    @ExcelProperty(value = "工號", index = 2)
    private String employeeNo;
    @ExcelProperty(value = "姓名", index = 4)
    private String name;
    @ExcelProperty(value = "英文名", index = 5)
    private String enName;
    @ExcelProperty(value = "性別", index = 6)
    private String gender;
    @ExcelProperty(value = "電話", index = 7)
    private String phoneMobile;
    @ExcelProperty(value = "內網電話", index = 8)
    private String landLine;
    @ExcelProperty(value = "內部郵箱", index = 9)
    private String innerMail;
    @ExcelProperty(value = "外部郵箱", index = 10)
    private String outerMail;
    @ExcelProperty(value = "收藏狀態", index = 11)
    private Integer status;


    public String getSeniorDepart() {
        return seniorDepart;
    }

    public void setSeniorDepart(String seniorDepart) {
        this.seniorDepart = seniorDepart;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

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

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getLandLine() {
        return landLine;
    }

    public void setLandLine(String landLine) {
        this.landLine = landLine;
    }

    public String getInnerMail() {
        return innerMail;
    }

    public void setInnerMail(String innerMail) {
        this.innerMail = innerMail;
    }

    public String getOuterMail() {
        return outerMail;
    }

    public void setOuterMail(String outerMail) {
        this.outerMail = outerMail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
