package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.oa.SwWorkReportLockBusiness;
import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.dto.entity.universal.StringParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExportStatusProcessor {

    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    DepartmentBusiness departmentBusiness;
    @Autowired
    SwWorkReportLockBusiness reportLockBusiness;

    public boolean updateExport(StringParams data) {
        return reportLockBusiness.updateLockStatusYearWeek(data.getParams());
    }

    public boolean exportStatus(StringParams data) {
        return reportLockBusiness.queryWorkReportLock(data.getParams());
    }
}
