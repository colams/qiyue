package com.foxconn.sw.service.processor.oa.report;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.report.SwWorkReportBusiness;
import com.foxconn.sw.common.utils.WeekUtils;
import com.foxconn.sw.data.dto.entity.oa.WorkReportParams;
import com.foxconn.sw.data.entity.SwWorkReport;
import com.foxconn.sw.data.exception.BizException;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
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
        Map<Integer, List<WorkReportParams>> paramsMap = data.stream()
                .collect(Collectors.groupingBy(WorkReportParams::getWeek));
        checkParams(paramsMap);

        String employeeNo = RequestContext.getEmployeeNo();
        int year = LocalDate.now().getYear();

        int nextWeek = paramsMap.keySet().toArray(new Integer[]{})[1];
        int currentWeek = paramsMap.keySet().toArray(new Integer[]{})[0];
        String nextYearWeek = getYearWeek(nextWeek, nextWeek, year);


        for (Map.Entry<Integer, List<WorkReportParams>> entry : paramsMap.entrySet()) {
            String yearWeek = getYearWeek(entry.getKey(), nextWeek, year);
            List<WorkReportParams> workReportParams = entry.getValue();
            List<SwWorkReport> reports = workReportBusiness.queryReport(yearWeek, employeeNo);
            processReports(workReportParams, reports, yearWeek);
        }
        return true;
    }

    private String getYearWeek(int week, int nextWeek, int year) {
        if (nextWeek == 1 && week != nextWeek) {
            year += 1;
        }
        return String.format("%s-%02d", year, week);
    }

    private void processReports(List<WorkReportParams> workReportParams, List<SwWorkReport> reports, String yearWeek) {
        List<SwWorkReport> updateReports = workReportParams.stream()
                .filter(e -> Objects.nonNull(e.getId()) && e.getId() > 0)
                .map(e -> convertReport(e, yearWeek)).collect(Collectors.toList());

        List<SwWorkReport> insertReports = workReportParams.stream()
                .filter(e -> Objects.isNull(e.getId()) || e.getId() <= 0)
                .map(e -> convertReport(e, yearWeek)).collect(Collectors.toList());

        List<SwWorkReport> deleteReports = reports.stream()
                .filter(e -> updateReports.stream()
                        .allMatch(report -> !report.getId().equals(e.getId())))
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

        Pair<Integer, Integer> pair = WeekUtils.getWeekYearInfo();
        int weekOfYear = pair.getLeft();
        List<WorkReportParams> currentWeeks = paramsMap.get(weekOfYear);
        List<WorkReportParams> nextWeeks = paramsMap.get(paramsMap.keySet().stream().toList().get(1));

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

    private SwWorkReport convertReport(WorkReportParams e, String yearWeek) {
        SwWorkReport report = new SwWorkReport();
        report.setId(e.getId());
        report.setEmployeeNo(RequestContext.getEmployeeNo());
        report.setYearWeek(yearWeek);
        report.setWeek(e.getWeek());
        report.setProject(e.getProject());
        report.setDays(e.getDay());
        report.setTarget(e.getTarget());
        report.setCurrent(e.getCurrent());
        report.setStatus(1);
        report.setDescription(e.getDescription());
        report.setRemark(e.getRemark());
        return report;
    }
}
