package com.foxconn.sw.service.processor;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.entity.SwDepartment;
import com.foxconn.sw.data.entity.SwEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportAuthorityProcessor {

    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    DepartmentBusiness departmentBusiness;


    public boolean reportAuthority() {
        String employeeNo = RequestContext.getEmployeeNo();
        boolean configRes = checkConfig(employeeNo);
        if (!configRes) {
            SwEmployee employee = employeeBusiness.selectEmployeeByENo(employeeNo);
            SwDepartment department = departmentBusiness.getDepartment(employee.getDepartmentId());
            return department.getManagerNo().equalsIgnoreCase(employeeNo);
        } else {
            return true;
        }
    }

    private boolean checkConfig(String employeeNo) {
        return employeeBusiness.getAssistants().contains(employeeNo);
    }
}
