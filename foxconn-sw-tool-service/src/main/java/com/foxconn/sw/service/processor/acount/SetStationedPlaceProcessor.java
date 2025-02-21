package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetStationedPlaceProcessor {

    @Autowired
    EmployeeBusiness employeeBusiness;


    public Boolean setStationedPlace(StringParams data) {
        return employeeBusiness.setStationedPlace(data.getParams(), RequestContext.getEmployeeNo());
    }
}
