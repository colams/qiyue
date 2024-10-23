package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwProjectList {
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

    public SwProjectList withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYears() {
        return years;
    }

    public SwProjectList withYears(Integer years) {
        this.setYears(years);
        return this;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public SwProjectList withProjectCode(String projectCode) {
        this.setProjectCode(projectCode);
        return this;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode == null ? null : projectCode.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public SwProjectList withCustomerName(String customerName) {
        this.setCustomerName(customerName);
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getFullName() {
        return fullName;
    }

    public SwProjectList withFullName(String fullName) {
        this.setFullName(fullName);
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public String getManufacturingModel() {
        return manufacturingModel;
    }

    public SwProjectList withManufacturingModel(String manufacturingModel) {
        this.setManufacturingModel(manufacturingModel);
        return this;
    }

    public void setManufacturingModel(String manufacturingModel) {
        this.manufacturingModel = manufacturingModel == null ? null : manufacturingModel.trim();
    }

    public String getStatus() {
        return status;
    }

    public SwProjectList withStatus(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRfqTime() {
        return rfqTime;
    }

    public SwProjectList withRfqTime(String rfqTime) {
        this.setRfqTime(rfqTime);
        return this;
    }

    public void setRfqTime(String rfqTime) {
        this.rfqTime = rfqTime == null ? null : rfqTime.trim();
    }

    public String getCustomer() {
        return customer;
    }

    public SwProjectList withCustomer(String customer) {
        this.setCustomer(customer);
        return this;
    }

    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }

    public String getCustomerPartNo() {
        return customerPartNo;
    }

    public SwProjectList withCustomerPartNo(String customerPartNo) {
        this.setCustomerPartNo(customerPartNo);
        return this;
    }

    public void setCustomerPartNo(String customerPartNo) {
        this.customerPartNo = customerPartNo == null ? null : customerPartNo.trim();
    }

    public String getApplication() {
        return application;
    }

    public SwProjectList withApplication(String application) {
        this.setApplication(application);
        return this;
    }

    public void setApplication(String application) {
        this.application = application == null ? null : application.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public SwProjectList withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwProjectList withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwProjectList withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}