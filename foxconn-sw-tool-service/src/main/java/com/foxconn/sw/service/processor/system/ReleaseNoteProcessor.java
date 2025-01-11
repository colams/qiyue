package com.foxconn.sw.service.processor.system;

import com.foxconn.sw.business.system.SwChangeLogBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.common.utils.IntegerExtUtils;
import com.foxconn.sw.data.dto.request.system.CreateChangeLogParams;
import com.foxconn.sw.data.dto.request.system.UpdateChangeLogParams;
import com.foxconn.sw.data.entity.SwChangeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReleaseNoteProcessor {

    @Autowired
    SwChangeLogBusiness changeLogBusiness;


    public Boolean addReleaseNote(CreateChangeLogParams data) {
        SwChangeLog changeLog = new SwChangeLog();
        changeLog.setOperator(RequestContext.getEmployeeNo());
        changeLog.setLastUpdater(RequestContext.getEmployeeNo());
        changeLog.setReleaseNote(data.getReleaseNote());
        changeLog.setReleaseVersion(data.getReleaseVersion());
        return changeLogBusiness.insertOrUpdate(changeLog) > 0;
    }

    public Boolean updateReleaseNote(UpdateChangeLogParams data) {
        SwChangeLog changeLog = new SwChangeLog();
        changeLog.setId(data.getId());
        if (!IntegerExtUtils.isPk(data.getId())) {
            changeLog.setOperator(RequestContext.getEmployeeNo());
            changeLog.setLastUpdater(RequestContext.getEmployeeNo());
        } else {
            changeLog.setLastUpdater(RequestContext.getEmployeeNo());
        }
        changeLog.setReleaseNote(data.getReleaseNote());
        changeLog.setReleaseVersion(data.getReleaseVersion());
        return changeLogBusiness.insertOrUpdate(changeLog) > 0;
    }
}
