package com.foxconn.sw.service.processor.oa.report;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.report.SwWorkReportBusiness;
import com.foxconn.sw.business.report.SwWorkReportLockBusiness;
import com.foxconn.sw.business.report.SwWorkReportScoreBusiness;
import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.oa.ReportSearchParams;
import com.foxconn.sw.data.dto.entity.oa.WorkReportDetail;
import com.foxconn.sw.data.dto.entity.oa.WorkReportVo;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwWorkReport;
import com.foxconn.sw.data.entity.SwWorkReportScore;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.service.processor.oa.utils.ReportSearchParamsUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.VALIDATE_FAILED;

@Component
public class ListReportProcessor {

    @Autowired
    SwWorkReportBusiness reportBusiness;
    @Autowired
    DepartmentBusiness departmentBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    SwWorkReportLockBusiness reportLockBusiness;
    @Autowired
    SwWorkReportScoreBusiness scoreBusiness;

    public List<WorkReportVo> listReport(ReportSearchParams searchParams) {
        return listReport(searchParams, false);
    }

    public List<WorkReportVo> listReport(ReportSearchParams searchParams, boolean isExport) {
        List<String> employees = getEmployeeNos(searchParams.getSearchType(),
                searchParams.getEmployeeName(),
                searchParams.getDepartID());

        if (CollectionUtils.isEmpty(employees)) {
            throw new BizException(VALIDATE_FAILED);
        }

        List<String> searchWeeks = ReportSearchParamsUtils.getYearWeekPair(searchParams, isExport);
        List<SwWorkReport> reports = reportBusiness.queryReport(searchWeeks, employees);
        if (isExport && ReportSearchParamsUtils.getTimeSpan(searchParams.getStartDate(), searchParams.getEndDate()) < 7) {
            // 暂时先不锁定
            // reportLockBusiness.updateLockStatusYearWeek(searchWeeks.get(searchWeeks.size() - 2));
        }

        List<WorkReportVo> vos = new ArrayList<>();
        reports.stream().forEach(e -> {
            WorkReportVo vo = vos.stream()
                    .filter(v -> e.getYearWeek().equalsIgnoreCase(v.getYearWeek())
                            && v.getEmployee().getEmployeeNo().equalsIgnoreCase(e.getEmployeeNo()))
                    .findFirst()
                    .orElse(null);
            WorkReportDetail detail = initDetail(e);
            if (Objects.isNull(vo)) {
                vo = new WorkReportVo();
                vo.setWeek(e.getWeek());
                vo.setYearWeek(e.getYearWeek());
                EmployeeVo employeeVo = new EmployeeVo();
                employeeVo.setEmployeeNo(e.getEmployeeNo());
                employeeVo.setName(getEmployeeName(e.getEmployeeNo()));
                vo.setEmployee(employeeVo);
                vo.setEmployeeNo(e.getEmployeeNo());
                vo.setReportDetailList(Lists.newArrayList(detail));
                vos.add(vo);
            } else {
                vo.getReportDetailList().add(detail);
            }
        });

        employees.forEach(e -> {
            for (String yearWeek : searchWeeks) {
                Optional<WorkReportVo> vo = vos.stream().filter(r -> r.getYearWeek().equalsIgnoreCase(yearWeek)
                                && r.getEmployeeNo().equalsIgnoreCase(e))
                        .findFirst();
                if (!vo.isPresent()) {
                    vos.add(getDefaultVo(yearWeek, e));
                }
            }
        });


        Map<String, Integer> map = queryScore(searchWeeks);
        List<WorkReportVo> result = vos.stream().map(e -> {
            Integer score = map.getOrDefault(yearWeekEno(e.getYearWeek(), e.getEmployeeNo()), 0);
            e.setCanScore(employeeBusiness.isDRIHigher(e.getEmployeeNo(), RequestContext.getEmployeeNo()));
            e.setScore(score);
            return e;
        }).collect(Collectors.toList());
        return result.stream()
                .sorted(Comparator.comparing(WorkReportVo::getEmployeeNo)
                        .thenComparing(WorkReportVo::getYearWeek).reversed())
                .collect(Collectors.toList());
    }

    private Map<String, Integer> queryScore(List<String> yearWeeks) {
        List<SwWorkReportScore> scores = scoreBusiness.queryScores(yearWeeks);
        return scores.stream()
                .collect(Collectors.toMap(e -> yearWeekEno(e.getYearWeek(), e.getEmployeeNo()),
                        SwWorkReportScore::getScore));
    }

    private String yearWeekEno(String yearWeek, String eno) {
        return yearWeek + "_" + eno;
    }

    private WorkReportVo getDefaultVo(String yearWeek, String employeeNo) {
        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo.setEmployeeNo(employeeNo);
        employeeVo.setName(getEmployeeName(employeeNo));

        WorkReportVo vo = new WorkReportVo();
        vo.setWeek(NumberUtils.toInt(yearWeek.split("-")[1]));
        vo.setEmployee(employeeVo);
        vo.setMessage("週報未提交");
        vo.setEmployeeNo(employeeNo);
        vo.setYearWeek(yearWeek);
        vo.setReportDetailList(Lists.newArrayList());
        vo.setScore(0);
        vo.setCanScore(false);
        return vo;
    }

    private WorkReportDetail initDetail(SwWorkReport e) {
        WorkReportDetail detail = new WorkReportDetail();
        detail.setId(e.getId());
        detail.setProject(e.getProject());
        detail.setDescription(e.getDescription());
        detail.setDay(e.getDays());
        detail.setTarget(e.getTarget());
        detail.setCurrent(e.getCurrent());
        detail.setRemark(e.getRemark());
        return detail;
    }

    private String getEmployeeName(String employeeNo) {
        return employeeBusiness.getEmployeeList()
                .stream()
                .filter(e -> e.getEmployeeNo().equalsIgnoreCase(employeeNo))
                .map(e -> e.getName())
                .findFirst()
                .orElse("");
    }

    private List<String> getEmployeeNos(Integer searchType, String employeeName, Integer departID) {
        if (Objects.isNull(searchType) || searchType < 2) {
            return Lists.newArrayList(RequestContext.getEmployeeNo());
        }

        List<SwEmployee> employees = employeeBusiness.queryMembers(RequestContext.getEmployeeNo());
        if (CollectionUtils.isEmpty(employees)) {
            return Lists.newArrayList(RequestContext.getEmployeeNo());
        }

        List<Integer> subDeptIds = departmentBusiness.getSubDepartID(departID);

        return employees.stream()
                .filter(e -> StringUtils.isEmpty(employeeName) || e.getEmployeeNo().equalsIgnoreCase(employeeName))
                .filter(e -> CollectionUtils.isEmpty(subDeptIds) || subDeptIds.contains(e.getDepartmentId()))
                .map(SwEmployee::getEmployeeNo)
                .collect(Collectors.toList());
    }
}
