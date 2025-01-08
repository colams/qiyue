package com.foxconn.sw.service.processor.department;


import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.data.dto.request.deparment.DepartmentParams;
import com.foxconn.sw.data.entity.SwDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateDepartmentProcessor {


    @Autowired
    DepartmentBusiness departmentBusiness;


    public boolean updateDepartment(DepartmentParams departmentVo) {
        SwDepartment department = new SwDepartment();
        department.setId(departmentVo.getId());
        department.setLevel(departmentVo.getLevel());
        department.setName(departmentVo.getName());
        department.setShortName(departmentVo.getShortName());
        department.setManagerNo(departmentVo.getManagerNo());
        department.setDescription(departmentVo.getDescription());
        department.setParentId(departmentVo.getParentId());
        department.setStatus(1);
        return departmentBusiness.updateOrInsert(department) > 0;
    }

    public boolean deleteDepartment(Integer departmentID) {
        SwDepartment department = new SwDepartment();
        department.setId(departmentID);
        department.setStatus(0);
        return departmentBusiness.updateOrInsert(department) > 0;
    }


}
