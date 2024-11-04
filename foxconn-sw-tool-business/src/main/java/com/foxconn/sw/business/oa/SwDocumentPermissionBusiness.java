package com.foxconn.sw.business.oa;

import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.data.entity.SwDepartment;
import com.foxconn.sw.data.entity.SwDocumentPermission;
import com.foxconn.sw.data.entity.SwDocumentPermissionExample;
import com.foxconn.sw.data.mapper.extension.oa.SwDocumentPermissionExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SwDocumentPermissionBusiness {

    @Autowired
    SwDocumentPermissionExtMapper permissionExtMapper;
    @Autowired
    DepartmentBusiness departmentBusiness;


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
        List<SwDepartment> swDepartments = departmentBusiness.getDepartment(employeeNo);
        List<String> departmentIDs = swDepartments.stream().map(e -> e.getId().toString()).collect(Collectors.toList());
        SwDocumentPermissionExample example = new SwDocumentPermissionExample();
        SwDocumentPermissionExample.Criteria criteria = example.createCriteria();
        criteria.andPermissionValueEqualTo(employeeNo);
        criteria.andDocumentIdEqualTo(documentID);
        SwDocumentPermissionExample.Criteria orCriteria = example.or();
        orCriteria.andPermissionValueIn(departmentIDs);
        orCriteria.andDocumentIdEqualTo(documentID);
        List<SwDocumentPermission> permissions = permissionExtMapper.selectByExample(example);
        return !CollectionUtils.isEmpty(permissions);
    }
}
