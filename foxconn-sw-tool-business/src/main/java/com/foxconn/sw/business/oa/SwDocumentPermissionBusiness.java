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
import java.util.stream.Collectors;

@Component
public class SwDocumentPermissionBusiness {

    @Autowired
    SwDocumentPermissionExtMapper permissionExtMapper;
    @Autowired
    DepartmentBusiness departmentBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;

    public boolean deleteDocumentPermission(Integer documentID) {
        SwDocumentPermissionExample example = new SwDocumentPermissionExample();
        SwDocumentPermissionExample.Criteria criteria = example.createCriteria();
        criteria.andDocumentIdEqualTo(documentID);

        SwDocumentPermission record = new SwDocumentPermission();
        record.setIsDelete(1);
        return permissionExtMapper.updateByExampleSelective(record, example) > 0;
    }

    public boolean insertDocumentPermission(Integer documentID,
                                            List<String> departmentIDs,
                                            String extra,
                                            int permissionType) {
        departmentIDs.forEach(e -> {
            SwDocumentPermission permission = new SwDocumentPermission();
            permission.setDocumentId(documentID);
            permission.setPermissionType(permissionType);
            permission.setPermissionValue(e);
            permission.setExtra(extra);
            permission.setIsDelete(0);
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
        if (CollectionUtils.isEmpty(permissions) || permissions.stream().anyMatch(e -> e.getPermissionValue().equalsIgnoreCase("0"))) {
            return true;
        }

        boolean hasEno = permissions.stream().anyMatch(e -> e.getPermissionValue().equalsIgnoreCase(employeeNo));
        if (hasEno) {
            return true;
        }

        boolean hasDepart = permissions.stream().anyMatch(e -> e.getPermissionValue().equalsIgnoreCase(employee.getDepartmentId().toString()));
        if (hasDepart) {
            return true;
        }
        return false;
    }

    public List<String> getPermissionSet(Integer permissionType, Integer documentID) {

        SwDocumentPermissionExample example = new SwDocumentPermissionExample();
        SwDocumentPermissionExample.Criteria criteria0 = example.createCriteria();
        criteria0.andDocumentIdEqualTo(documentID);

        List<SwDocumentPermission> permissions = permissionExtMapper.selectByExample(example);

        List<String> permissionStr = permissions.stream()
                .filter(e -> !"0".equalsIgnoreCase(e.getPermissionValue()))
                .filter(e -> permissionType.equals(e.getPermissionType()))
                .map(e -> e.getPermissionValue())
                .collect(Collectors.toList());
        return permissionStr;
    }

    public String getPermissionExtra(Integer documentID) {
        SwDocumentPermissionExample example = new SwDocumentPermissionExample();
        SwDocumentPermissionExample.Criteria criteria0 = example.createCriteria();
        criteria0.andDocumentIdEqualTo(documentID);

        List<SwDocumentPermission> permissions = permissionExtMapper.selectByExample(example);

        String permissionStr = permissions.stream()
                .map(e -> e.getExtra())
                .findFirst()
                .orElse("");
        return permissionStr;
    }
}
