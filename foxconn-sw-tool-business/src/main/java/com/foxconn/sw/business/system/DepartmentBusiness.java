package com.foxconn.sw.business.system;

import com.foxconn.sw.data.dto.entity.system.DepartmentVo;
import com.foxconn.sw.data.mapper.extension.system.DepartmentExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentBusiness {

    @Autowired
    DepartmentExtensionMapper departmentExtensionMapper;

    public List<DepartmentVo> getDepartList() {
        return departmentExtensionMapper.getDepartList();
    }
}
