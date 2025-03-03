package com.foxconn.sw.service.processor.department;

import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetSubDepartIdsProcessor {
    @Autowired
    DepartmentBusiness departmentBusiness;

    public List<Integer> getSubDepartID(IntegerParams data) {
        return departmentBusiness.getSubDepartID(data.getParams());
    }
}
