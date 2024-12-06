package com.foxconn.sw.data.dto.request.group;

public class MemberBrief {

    private String employeeNo;
    /**
     * 0-普通成员，1-owner 群主，2-管理员
     */
    private Integer memberType;

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Integer getMemberType() {
        return memberType;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }
}
