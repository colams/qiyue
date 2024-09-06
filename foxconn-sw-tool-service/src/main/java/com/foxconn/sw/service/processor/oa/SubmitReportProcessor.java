package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.oa.SwWorkReportBusiness;
import com.foxconn.sw.data.dto.entity.oa.WorkReportParams;
import com.foxconn.sw.data.entity.SwWorkReport;
import com.foxconn.sw.data.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.foxconn.sw.data.constants.enums.retcode.OAExceptionCode.LESS_ITEM_EXCEPTION;

@Component
public class SubmitReportProcessor {

    @Autowired
    SwWorkReportBusiness workReportBusiness;


    public boolean submitReport(List<WorkReportParams> data) {
        Map<Integer, List<WorkReportParams>> paramsMap = data.stream().collect(Collectors.groupingBy(WorkReportParams::getWeek));
        checkParams(paramsMap);

        String employeeNo = RequestContext.getEmployeeNo();
        for (Map.Entry<Integer, List<WorkReportParams>> entry : paramsMap.entrySet()) {

            int currentWeekOfYear = entry.getKey();
            List<WorkReportParams> workReportParams = entry.getValue();

            List<SwWorkReport> reports = workReportBusiness.queryReport(currentWeekOfYear, employeeNo);
            processReports(workReportParams, reports);
        }
        return true;
    }

    private void processReports(List<WorkReportParams> workReportParams, List<SwWorkReport> reports) {
        List<SwWorkReport> updateReports = workReportParams.stream()
                .filter(e -> Objects.nonNull(e.getId()) && e.getId() > 0)
                .map(e -> convertReport(e)).collect(Collectors.toList());

        List<SwWorkReport> insertReports = workReportParams.stream()
                .filter(e -> Objects.isNull(e.getId()) || e.getId() <= 0)
                .map(e -> convertReport(e)).collect(Collectors.toList());

        List<SwWorkReport> deleteReports = reports.stream()
                .filter(e -> updateReports.stream()
                        .allMatch(report -> report.getId() != e.getId()))
                .map(e -> {
                    SwWorkReport report = new SwWorkReport();
                    report.setId(e.getId());
                    report.setStatus(0);
                    return report;
                })
                .collect(Collectors.toList());
        processReport(updateReports, insertReports, deleteReports);
    }

    private void checkParams(Map<Integer, List<WorkReportParams>> paramsMap) {
        if (paramsMap.size() != 2) {
            throw new BizException(LESS_ITEM_EXCEPTION);
        }

        int weekOfYear = getWeekOfYear();
        List<WorkReportParams> currentWeeks = paramsMap.get(weekOfYear);
        List<WorkReportParams> nextWeeks = paramsMap.get(weekOfYear + 1);

        if (CollectionUtils.isEmpty(currentWeeks) || CollectionUtils.isEmpty(nextWeeks)) {
            throw new BizException(LESS_ITEM_EXCEPTION);
        }
    }

    private boolean processReport(List<SwWorkReport> updateReports, List<SwWorkReport> insertReports, List<SwWorkReport> deleteReports) {
        if (Objects.nonNull(updateReports)) {
            workReportBusiness.updateBatchReports(updateReports);
        }
        if (Objects.nonNull(insertReports)) {
            workReportBusiness.inertBatchReports(insertReports);
        }
        if (Objects.nonNull(deleteReports)) {
            workReportBusiness.deleteBatchReports(deleteReports);
        }
        return true;
    }

    private SwWorkReport convertReport(WorkReportParams e) {
        SwWorkReport report = new SwWorkReport();
        report.setId(e.getId());
        report.setEmployeeNo(RequestContext.getEmployeeNo());
        report.setWeek(e.getWeek());
        report.setNum(e.getNum());
        report.setProject(e.getProject());
        report.setDays(e.getDay());
        report.setTarget(e.getTarget());
        report.setCurrent(e.getCurrent());
        report.setStatus(1);
        report.setDescription(e.getDescription());
        report.setRemark(e.getRemark());
        return report;
    }

    private Integer getWeekOfYear() {
        Calendar calendar = Calendar.getInstance();
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        return weekOfYear;
    }
}
