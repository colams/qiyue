package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.entity.SwEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueryMemberProcessor {
    @Autowired
    EmployeeBusiness employeeBusiness;

    public List<EmployeeVo> queryMembers(String employeeNo, Integer departID) {
        List<SwEmployee> employeeVos = employeeBusiness.queryMembers(employeeNo, departID);
        List<EmployeeVo> vos = employeeVos.stream().map(e -> {
            EmployeeVo vo = new EmployeeVo();
            vo.setName(e.getName());
            vo.setEmployeeNo(e.getEmployeeNo());
            return vo;
        }).collect(Collectors.toList());

        return vos;
    }

}
