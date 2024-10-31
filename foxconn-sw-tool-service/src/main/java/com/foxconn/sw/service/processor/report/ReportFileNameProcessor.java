package com.foxconn.sw.service.processor.report;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.entity.SwDepartment;
import com.foxconn.sw.service.processor.utils.ReportSearchParamsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportFileNameProcessor {

    @Autowired
    DepartmentBusiness departmentBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;

    public String generatorFileName(String startDate) {
        String empoloyee = employeeBusiness.convertEmployeeNo(RequestContext.getEmployeeNo());
        List<SwDepartment> departmentList = departmentBusiness.getDepartment(empoloyee);
        String department = departmentList.stream().map(e -> {
                    if (StringUtils.isNotEmpty(e.getShortName())) {
                        return e.getShortName();
                    }
                    return e.getName();
                }).findFirst()
                .orElse("");
        String weekOfYear = ReportSearchParamsUtils.processDate(startDate);
        return String.format("CMA_RD_%s_Weekly Report _WK%s.xlsx", department, weekOfYear);
    }

}
