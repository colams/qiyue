package com.foxconn.sw.data.dto.request.schedule;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ScheduleListParams {

    @Schema(description = "开始时间")
    private String startDate;
    @Schema(description = "结束时间")
    private String endDate;
    @Schema(description = "目的地")
    private String destination;
    @Schema(description = "幹部身份")
    private List<String> identityOfCadre;
    @Schema(description = "部门信息")
    private Integer departmentId;
    @Schema(description = "管理职")
    private List<Integer> manageLeve;
    @Schema(description = "工号")
    private String employeeNo;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<String> getIdentityOfCadre() {
        return identityOfCadre;
    }

    public void setIdentityOfCadre(List<String> identityOfCadre) {
        this.identityOfCadre = identityOfCadre;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public List<Integer> getManageLeve() {
        return manageLeve;
    }

    public void setManageLeve(List<Integer> manageLeve) {
        this.manageLeve = manageLeve;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }
}
