package com.foxconn.sw.data.dto.request.project;

public class ProjectSaveParams {

    private Integer id;

    private Integer years;

    private String projectCode;

    private String customerName;

    private String fullName;

    private String manufacturingModel;

    private String status;

    private String rfqTime;

    private String customer;

    private String customerPartNo;

    private String application;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getManufacturingModel() {
        return manufacturingModel;
    }

    public void setManufacturingModel(String manufacturingModel) {
        this.manufacturingModel = manufacturingModel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRfqTime() {
        return rfqTime;
    }

    public void setRfqTime(String rfqTime) {
        this.rfqTime = rfqTime;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustomerPartNo() {
        return customerPartNo;
    }

    public void setCustomerPartNo(String customerPartNo) {
        this.customerPartNo = customerPartNo;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }
}
