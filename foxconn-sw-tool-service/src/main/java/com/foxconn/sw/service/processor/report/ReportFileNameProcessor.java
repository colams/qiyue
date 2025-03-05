package com.foxconn.sw.service.processor.report;

import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.service.processor.utils.ReportSearchParamsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportFileNameProcessor {

    @Autowired
    DepartmentBusiness departmentBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;

    public String generatorFileName(String startDate) {
        String weekOfYear = ReportSearchParamsUtils.processDate(startDate);
        return String.format("Weekly Report _WK%s.xlsx", weekOfYear);
    }

}
