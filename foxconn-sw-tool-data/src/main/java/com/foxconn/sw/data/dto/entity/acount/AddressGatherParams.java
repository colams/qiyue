package com.foxconn.sw.data.dto.entity.acount;

public class AddressGatherParams {

    private String employeeNo;
    private Integer gatherType;

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Integer getGatherType() {
        return gatherType;
    }

    public void setGatherType(Integer gatherType) {
        this.gatherType = gatherType;
    }
}
