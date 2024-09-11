package com.foxconn.sw.service.processor.oa;

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
        SwEmployee employee = employeeBusiness.selectEmployeeByENo(employeeNo);
        SwDepartment department = departmentBusiness.getDepartment(employee.getDepartmentId());
        return department.getManagerNo().equalsIgnoreCase(employeeNo);
    }
}
