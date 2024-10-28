package com.foxconn.sw.service.processor.report;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.report.SwWorkReportBusiness;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.entity.oa.WorkReportParams;
import com.foxconn.sw.data.entity.SwWorkReport;
import com.foxconn.sw.data.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
        Map<String, List<WorkReportParams>> paramsMap = data.stream()
                .collect(Collectors.groupingBy(WorkReportParams::getYearWeek));
        checkParams(paramsMap);

        String employeeNo = RequestContext.getEmployeeNo();

        String firstYearWeek = paramsMap.keySet().toArray(new String[]{})[0];

        for (Map.Entry<String, List<WorkReportParams>> entry : paramsMap.entrySet()) {
            int reportType = firstYearWeek.compareToIgnoreCase(entry.getKey()) < 0 ? 1 : 0;
            List<SwWorkReport> reports = workReportBusiness.queryReport(entry.getKey(), employeeNo);
            processReports(entry.getValue(), reports, entry.getKey(), reportType);
        }
        return true;
    }

    private void processReports(List<WorkReportParams> workReportParams,
                                List<SwWorkReport> reports,
                                String yearWeek,
                                int reportType) {

        List<SwWorkReport> updateReports = workReportParams.stream()
                .filter(e -> Objects.nonNull(e.getId()) && e.getId() > 0 && reports.stream().anyMatch(f -> f.getId().equals(e.getId())))
                .map(e -> convertReport(e, yearWeek, reportType))
                .collect(Collectors.toList());

        List<SwWorkReport> insertReports = workReportParams.stream()
                .map(e -> {
                    boolean hasID = reports.stream().anyMatch(f -> f.getId().equals(e.getId()));
                    if (Objects.nonNull(e.getId()) && e.getId() > 0 && !hasID) {
                        e.setId(0);
                    }
                    return e;
                })
                .filter(e -> Objects.isNull(e.getId()) || e.getId() <= 0)
                .map(e -> convertReport(e, yearWeek, reportType))
                .collect(Collectors.toList());

        List<SwWorkReport> deleteReports = reports.stream()
                .filter(e -> updateReports.stream()
                        .allMatch(report -> !report.getId().equals(e.getId())))
                .map(e -> {
                    SwWorkReport report = new SwWorkReport();
                    report.setId(e.getId());
                    report.setStatus(0);
                    report.setIsDelete(1);
                    report.setReportType(reportType);
                    return report;
                })
                .collect(Collectors.toList());
        processReport(updateReports, insertReports, deleteReports);
    }

    private void checkParams(Map<String, List<WorkReportParams>> paramsMap) {
        if (paramsMap.size() != 2) {
            throw new BizException(LESS_ITEM_EXCEPTION);
        }

        boolean hasEmpty = paramsMap.entrySet().stream().anyMatch(e -> CollectionUtils.isEmpty(e.getValue()));
        if (hasEmpty) {
            throw new BizException(LESS_ITEM_EXCEPTION);
        }
    }

    private boolean processReport(List<SwWorkReport> updateReports,
                                  List<SwWorkReport> insertReports,
                                  List<SwWorkReport> deleteReports) {
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

    private SwWorkReport convertReport(WorkReportParams e, String yearWeek, int reportType) {
        SwWorkReport report = new SwWorkReport();
        report.setId(e.getId());
        report.setEmployeeNo(RequestContext.getEmployeeNo());
        report.setYearWeek(yearWeek);
        report.setWeek(e.getWeek());
        if (!CollectionUtils.isEmpty(e.getProjectCode())) {
            report.setProject(JsonUtils.serialize(e.getProjectCode()));
        } else {
            report.setProject(e.getProject());
        }
        report.setDays(e.getDay());
        report.setTarget(e.getTarget());
        report.setCurrent(e.getCurrent());
        report.setStatus(1);
        report.setDescription(e.getDescription());
        report.setRemark(e.getRemark());
        report.setReportType(reportType);
        return report;
    }
}
