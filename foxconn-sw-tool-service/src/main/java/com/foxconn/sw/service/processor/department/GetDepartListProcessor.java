package com.foxconn.sw.service.processor.department;

import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.entity.system.DepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetDepartListProcessor {

    @Autowired
    DepartmentBusiness departmentBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;

    public List<DepartmentVo> getDepartList() {
        return departmentBusiness.getTreeDepartmentVos();
    }

    public List<DepartmentVo> subDepts() {
        return departmentBusiness.getMangeDepartVo(RequestContext.getEmployeeNo());
    }
}
