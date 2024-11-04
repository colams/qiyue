package com.foxconn.sw.business.oa;

import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.entity.SwDocumentPermission;
import com.foxconn.sw.data.entity.SwDocumentPermissionExample;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.mapper.extension.oa.SwDocumentPermissionExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class SwDocumentPermissionBusiness {

    @Autowired
    SwDocumentPermissionExtMapper permissionExtMapper;
    @Autowired
    DepartmentBusiness departmentBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;


    public boolean insertDocumentPermission(Integer documentID, List<String> departmentIDs, int permissionType) {
        departmentIDs.forEach(e -> {
            SwDocumentPermission permission = new SwDocumentPermission();
            permission.setDocumentId(documentID);
            permission.setPermissionType(permissionType);
            permission.setPermissionValue(e);
            permissionExtMapper.insertSelective(permission);
        });
        return true;
    }

    public boolean getViewPermission(String employeeNo, Integer documentID) {
        SwEmployee employee = employeeBusiness.selectEmployeeByENo(employeeNo);

        SwDocumentPermissionExample example = new SwDocumentPermissionExample();
        SwDocumentPermissionExample.Criteria criteria0 = example.createCriteria();
        criteria0.andDocumentIdEqualTo(documentID);

        List<SwDocumentPermission> permissions = permissionExtMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(permissions)) {
            return true;
        }

        boolean hasEno = permissions.stream().anyMatch(e -> e.getPermissionValue().equalsIgnoreCase(employeeNo));
        if (hasEno) {
            return hasEno;
        }

        boolean hasDepart = permissions.stream().anyMatch(e -> e.getPermissionValue().equalsIgnoreCase(employee.getDepartmentId().toString()));
        if (hasDepart) {
            return hasDepart;
        }
        return false;
    }
}
