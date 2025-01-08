package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.common.utils.PinyinUtils;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.entity.SwEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SubordinateProcessor {
    @Autowired
    EmployeeBusiness employeeBusiness;


    public List<EmployeeVo> subordinateList() {
        String employee = RequestContext.getEmployeeNo();
        List<SwEmployee> ee = employeeBusiness.queryMembers(employee);
        return ee.stream().map(e -> {
            EmployeeVo vo = new EmployeeVo();
            vo.setId(e.getId());
            vo.setName(e.getName());
            vo.setEmployeeNo(e.getEmployeeNo());
            vo.setPinyin(PinyinUtils.toPinyin(e.getName()));
            vo.setDepartmentId(e.getDepartmentId());
            return vo;
        }).collect(Collectors.toList());
    }
}
