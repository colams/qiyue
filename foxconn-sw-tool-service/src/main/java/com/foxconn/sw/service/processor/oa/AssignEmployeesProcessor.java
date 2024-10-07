package com.foxconn.sw.service.processor.oa;

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

    private String fillDot(int dotCount) {
        int i = 0;
        String str = "";
        while (i < dotCount) {
            i++;
            str += "・";
        }
        return str;
    }

    private static Integer findLongestStringLength(List<EmployeeVo> vos) {
        if (vos == null || vos.isEmpty()) {
            return null;
        }
        String longestString = vos.get(0).getName() + vos.get(0).getDepartmentName() + "・・・・・";
        for (EmployeeVo vo : vos) {
            String str = vo.getName() + vo.getDepartmentName();
            if (str.length() > longestString.length()) {
                longestString = str;
            }
        }
        return longestString.length();
    }
}
