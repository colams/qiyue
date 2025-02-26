package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.SwReadStatusBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.data.constants.enums.ModuleEnums;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.entity.SwReadStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveReadStatusProcessor {
    @Autowired
    SwReadStatusBusiness readStatusBusiness;

    public boolean saveReadStatus(ModuleEnums moduleEnum, IntegerParams data) {
        SwReadStatus readStatus = new SwReadStatus();
        readStatus.setModuleType(moduleEnum.name());
        readStatus.setForeignId(data.getParams());
        readStatus.setEmployeeNo(RequestContext.getEmployeeNo());
        readStatus.setIsRead(NumberConstants.ONE);
        return readStatusBusiness.updateOrInsert(readStatus) > 0;
    }

}
