package com.foxconn.sw.data.dto.response.basic;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.group.GroupBriefVo;
import com.foxconn.sw.data.dto.entity.universal.OptionsVo;

import java.util.List;

public class DepartAndEmployeeOptionVo {


    private List<OptionsVo> departmentVos;
    private List<EmployeeVo> employeeVos;
    private List<GroupBriefVo> groupBriefVos;

    public List<OptionsVo> getDepartmentVos() {
        return departmentVos;
    }

    public void setDepartmentVos(List<OptionsVo> departmentVos) {
        this.departmentVos = departmentVos;
    }

    public List<EmployeeVo> getEmployeeVos() {
        return employeeVos;
    }

    public void setEmployeeVos(List<EmployeeVo> employeeVos) {
        this.employeeVos = employeeVos;
    }

    public List<GroupBriefVo> getGroupBriefVos() {
        return groupBriefVos;
    }

    public void setGroupBriefVos(List<GroupBriefVo> groupBriefVos) {
        this.groupBriefVos = groupBriefVos;
    }
}
