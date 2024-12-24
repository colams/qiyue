package com.foxconn.sw.data.dto.request.group;

import com.foxconn.sw.data.dto.enums.GroupMemberTypeEnums;

public class MemberBrief {

    private String employeeNo;
    /**
     * 0-普通成员，1-owner 群主，2-管理员
     */
    private GroupMemberTypeEnums memberType;

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public GroupMemberTypeEnums getMemberType() {
        return memberType;
    }

    public void setMemberType(GroupMemberTypeEnums memberType) {
        this.memberType = memberType;
    }
}
