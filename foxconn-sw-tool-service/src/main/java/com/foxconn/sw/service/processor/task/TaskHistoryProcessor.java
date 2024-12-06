package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.business.oa.SwTaskContentHistoryBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskHistoryProcessor {

    @Autowired
    SwTaskContentHistoryBusiness contentHistoryBusiness;

    public String getHistoryContent(Integer contentHistoryID) {
        return contentHistoryBusiness.getHistoryContent(contentHistoryID);
    }

}
