package com.foxconn.sw.service.processor;

import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.dto.entity.acount.EmployeeOptionVo;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssignEmployeesProcessor {

    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    DepartmentBusiness departmentBusiness;

    public List<EmployeeVo> assignEmployees() {
        return employeeBusiness.getEmployeesByLevel();
    }

    public List<EmployeeVo> assignByType(StringParams data) {
        List<EmployeeVo> employeeVos = employeeBusiness.getEmployeesByLevel();
        if (data.getParams().equalsIgnoreCase("assign")) {

            return employeeVos;
        }
        return employeeVos;
    }
}
