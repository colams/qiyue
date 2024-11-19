package com.foxconn.sw.data.dto.request.forums;

import java.util.List;

public class InvitePostsMemberParams {

    private Integer id;
    private List<String> employeeNos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getEmployeeNos() {
        return employeeNos;
    }

    public void setEmployeeNos(List<String> employeeNos) {
        this.employeeNos = employeeNos;
    }
}
