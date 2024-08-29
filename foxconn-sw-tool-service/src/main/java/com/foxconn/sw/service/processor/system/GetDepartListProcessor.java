package com.foxconn.sw.service.processor.system;

import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.data.dto.entity.system.DepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetDepartListProcessor {

    @Autowired
    DepartmentBusiness departmentBusiness;

    public List<DepartmentVo> getDepartList() {
        return departmentBusiness.getTreeDepartmentVos();
    }
}
