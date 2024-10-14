package com.foxconn.sw.service.processor.department;


import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.data.dto.request.deparment.DepartmentParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateDepartmentProcessor {


    @Autowired
    DepartmentBusiness departmentBusiness;


    public boolean updateDepartment(DepartmentParams departmentVo) {
        return departmentBusiness.updateOrInsert(departmentVo);
    }

    public boolean deleteDepartment(Integer departmentID) {
        return departmentBusiness.delete(departmentID);
    }


}
