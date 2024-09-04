package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.oa.SwWorkReportBusiness;
import com.foxconn.sw.data.dto.entity.oa.WorkReportParams;
import com.foxconn.sw.data.entity.SwWorkReport;
import com.foxconn.sw.data.exception.BizException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.foxconn.sw.data.constants.enums.retcode.OAExceptionCode.LESS_ITEM_EXCEPTION;
import static com.foxconn.sw.data.constants.enums.retcode.RetCode.VALIDATE_FAILED;

@Component
public class SubmitReportProcessor {

    @Autowired
    SwWorkReportBusiness workReportBusiness;


    public boolean submitReport(List<WorkReportParams> data) {

        int weekOfYear = getWeekOfYear();
        Map<Integer, List<WorkReportParams>> paramsMap = data.stream().collect(Collectors.groupingBy(WorkReportParams::getWeek));

        if (paramsMap.size() != 2) {
            throw new BizException(LESS_ITEM_EXCEPTION);
        }

        List<WorkReportParams> currentWeeks = paramsMap.get(weekOfYear);
        List<WorkReportParams> nextWeeks = paramsMap.get(weekOfYear + 1);

        if (CollectionUtils.isEmpty(currentWeeks) || CollectionUtils.isEmpty(nextWeeks)) {
            throw new BizException(LESS_ITEM_EXCEPTION);
        }

        workReportBusiness.invalidReport(Lists.newArrayList(weekOfYear, weekOfYear + 1));
        for (Map.Entry<Integer, List<WorkReportParams>> entry : paramsMap.entrySet()) {
            processReport(entry.getKey(), entry.getValue());
        }
        return true;
    }

    private boolean processReport(Integer weekOfYear, List<WorkReportParams> paramsList) {
        List<SwWorkReport> reports = new ArrayList<>();

        paramsList.forEach(e -> {
            SwWorkReport report = new SwWorkReport();
            report.setEmployeeNo(RequestContext.getEmployeeNo());
            report.setWeek(weekOfYear);
            report.setNum(e.getNum());
            report.setProject(e.getProject());
            report.setDays(e.getDay());
            report.setTarget(e.getTarget());
            report.setCurrent(e.getCurrent());
            report.setStatus(1);
            report.setDescription(e.getDescription());
            report.setRemark(e.getRemark());
            reports.add(report);
        });
        workReportBusiness.inertBatchReports(reports);
        return true;
    }

    private Integer getWeekOfYear() {
        Calendar calendar = Calendar.getInstance();
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        return weekOfYear;
    }
}
