package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwProject {
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

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwProject withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYears() {
        return years;
    }

    public SwProject withYears(Integer years) {
        this.setYears(years);
        return this;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public SwProject withProjectCode(String projectCode) {
        this.setProjectCode(projectCode);
        return this;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public SwProject withCustomerName(String customerName) {
        this.setCustomerName(customerName);
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getFullName() {
        return fullName;
    }

    public SwProject withFullName(String fullName) {
        this.setFullName(fullName);
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public String getManufacturingModel() {
        return manufacturingModel;
    }

    public SwProject withManufacturingModel(String manufacturingModel) {
        this.setManufacturingModel(manufacturingModel);
        return this;
    }

    public void setManufacturingModel(String manufacturingModel) {
        this.manufacturingModel = manufacturingModel == null ? null : manufacturingModel.trim();
    }

    public String getStatus() {
        return status;
    }

    public SwProject withStatus(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRfqTime() {
        return rfqTime;
    }

    public SwProject withRfqTime(String rfqTime) {
        this.setRfqTime(rfqTime);
        return this;
    }

    public void setRfqTime(String rfqTime) {
        this.rfqTime = rfqTime == null ? null : rfqTime.trim();
    }

    public String getCustomer() {
        return customer;
    }

    public SwProject withCustomer(String customer) {
        this.setCustomer(customer);
        return this;
    }

    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }

    public String getCustomerPartNo() {
        return customerPartNo;
    }

    public SwProject withCustomerPartNo(String customerPartNo) {
        this.setCustomerPartNo(customerPartNo);
        return this;
    }

    public void setCustomerPartNo(String customerPartNo) {
        this.customerPartNo = customerPartNo == null ? null : customerPartNo.trim();
    }

    public String getApplication() {
        return application;
    }

    public SwProject withApplication(String application) {
        this.setApplication(application);
        return this;
    }

    public void setApplication(String application) {
        this.application = application == null ? null : application.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public SwProject withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwProject withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwProject withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}