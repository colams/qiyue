package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.business.oa.SwTaskContentHistoryBusiness;
import com.foxconn.sw.data.dto.entity.TupleValue;
import com.foxconn.sw.data.entity.SwTaskContentHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TaskHistoryProcessor {

    @Autowired
    SwTaskContentHistoryBusiness contentHistoryBusiness;

    public TupleValue getHistoryContent(Integer contentHistoryID) {
        SwTaskContentHistory contentHistory = contentHistoryBusiness.getHistoryContent(contentHistoryID);
        if (Objects.isNull(contentHistory)) {
            return new TupleValue("", "");
        }
        return new TupleValue(contentHistory.getOldContent(), contentHistory.getNewContent());
    }

}
