package com.foxconn.sw.data.dto.entity.collaboration;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;

import java.util.List;

public class CollaborationVo {

    private List<String> headers;
    private List<EmployeeVo> employeeVos;

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<EmployeeVo> getEmployeeVos() {
        return employeeVos;
    }

    public void setEmployeeVos(List<EmployeeVo> employeeVos) {
        this.employeeVos = employeeVos;
    }
}
