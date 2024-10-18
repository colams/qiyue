package com.foxconn.sw.business.oa;

import com.foxconn.sw.data.entity.SwDocumentPermission;
import com.foxconn.sw.data.mapper.extension.oa.SwDocumentPermissionExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwDocumentPermissionBusiness {

    @Autowired
    SwDocumentPermissionExtMapper permissionExtMapper;


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
}
