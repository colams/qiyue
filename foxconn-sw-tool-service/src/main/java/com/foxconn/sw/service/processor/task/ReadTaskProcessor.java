package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReadTaskProcessor {

    @Autowired
    SwTaskEmployeeRelationBusiness taskEmployeeRelationBusiness;


    public Boolean setRead(IntegerParams taskID) {
        return taskEmployeeRelationBusiness.updateReadStatus(taskID.getParams());
    }
}
