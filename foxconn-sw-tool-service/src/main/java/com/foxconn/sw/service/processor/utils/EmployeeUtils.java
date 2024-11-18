package com.foxconn.sw.service.processor.utils;

import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeUtils {

    @Autowired
    EmployeeBusiness employeeBusiness;

    public EmployeeVo mapEmployee(String employeeNo) {
        EmployeeVo employeeVo = new EmployeeVo();
        return employeeVo;
    }

}
