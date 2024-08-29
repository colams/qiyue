package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssignEmployeesProcessor {

    @Autowired
    EmployeeBusiness employeeBusiness;

    public List<EmployeeVo> assignEmployees() {
        return employeeBusiness.getEmployeesByLevel();
    }
}
