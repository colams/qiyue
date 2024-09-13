package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.dto.entity.acount.EmployeeOptionVo;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AssignEmployeesProcessor {

    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    DepartmentBusiness departmentBusiness;

    public List<EmployeeOptionVo> assignEmployees() {
        List<EmployeeVo> employeeVos = employeeBusiness.getEmployeesByLevel();

        List<EmployeeOptionVo> vos = new ArrayList<>();
        employeeVos.forEach(e -> {
            EmployeeOptionVo vo = new EmployeeOptionVo();
            String department = departmentBusiness.getShortDepartName(e.getDepartmentId());
            vo.setName(String.format("%s(%s)[%s]", e.getName(), e.getEmployeeNo(), department));
            vo.setEmployeeNo(e.getEmployeeNo());
            vos.add(vo);
        });
        return vos;
    }
}
