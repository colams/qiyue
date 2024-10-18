package com.foxconn.sw.service.processor.report;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.report.SwWorkReportBusiness;
import com.foxconn.sw.business.report.SwWorkReportLockBusiness;
import com.foxconn.sw.business.report.SwWorkReportScoreBusiness;
import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.oa.ReportSearchParams;
import com.foxconn.sw.data.dto.entity.oa.WorkReportDetail;
import com.foxconn.sw.data.dto.entity.oa.WorkReportVo;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwWorkReport;
import com.foxconn.sw.data.entity.SwWorkReportScore;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.service.processor.config.PositionConfig;
import com.foxconn.sw.service.processor.utils.ReportSearchParamsUtils;
import com.foxconn.sw.service.processor.utils.TaskProjectUtils;
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
        List<String> employees = getEmployeeNos(searchParams);

        if (CollectionUtils.isEmpty(employees)) {
            throw new BizException(VALIDATE_FAILED);
        }

        List<String> searchWeeks = ReportSearchParamsUtils.getYearWeekPair(searchParams, isExport);
        List<SwWorkReport> reports = reportBusiness.queryReport(searchWeeks, employees);
        List<SwWorkReport> plans = new ArrayList<>();
        boolean pState = false;
        if (searchParams.getSearchType() == 2 && searchWeeks.size() == 2) {
            plans = reports.stream().filter(e -> e.getYearWeek().equalsIgnoreCase(searchWeeks.get(1))).collect(Collectors.toList());
            reports = reports.stream().filter(e -> e.getYearWeek().equalsIgnoreCase(searchWeeks.get(0))).collect(Collectors.toList());
            pState = true;
            searchWeeks.remove(1);
        }

        boolean ppState = pState;
        List<SwWorkReport> plans1 = plans;
        List<WorkReportVo> vos = new ArrayList<>();
        reports.stream().forEach(e -> {
            if (ppState && plans1.stream().
                    filter(f -> e.getEmployeeNo().equalsIgnoreCase(f.getEmployeeNo()))
                    .collect(Collectors.toList()).size() <= 0) {
                return;
            }

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

        List<WorkReportVo> retValue = result.stream()
                .sorted(Comparator.comparing(WorkReportVo::getEmployeeNo)
                        .thenComparing(WorkReportVo::getYearWeek).reversed())
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(retValue.get(0).getReportDetailList()) && retValue.size() > 3) {
            List<WorkReportDetail> detailList = retValue.get(2).getReportDetailList()
                    .stream()
                    .filter(e -> e.getTarget() < 100)
                    .collect(Collectors.toList());

            if (!CollectionUtils.isEmpty(detailList)) {
                retValue.get(1).getReportDetailList().addAll(detailList);
            }
        }
        return retValue;
    }

    private void lockUpdate(ReportSearchParams searchParams, boolean isExport, List<String> searchWeeks) {
        boolean sameWeek = ReportSearchParamsUtils.getTimeSpan(searchParams.getStartDate(), searchParams.getEndDate()) < 7;
        if (isExport && sameWeek) {
            // 暂时先不锁定
            reportLockBusiness.updateLockStatusYearWeek(searchWeeks.get(searchWeeks.size() - 2));
        }
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
        detail.setProjectCode(JsonUtils.deserialize(e.getProject(), List.class, String.class));
        if (!CollectionUtils.isEmpty(detail.getProjectCode())) {
            detail.setProject(TaskProjectUtils.processProject(detail.getProjectCode().get(1)));
        } else {
            detail.setProject(e.getProject());
        }
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

    private List<String> getEmployeeNos(ReportSearchParams searchParams) {
        if (Objects.isNull(searchParams.getSearchType()) || searchParams.getSearchType() < 2) {
            return Lists.newArrayList(RequestContext.getEmployeeNo());
        }

        List<SwEmployee> employees = employeeBusiness.queryMembers(RequestContext.getEmployeeNo());
        if (CollectionUtils.isEmpty(employees)) {
            return Lists.newArrayList(RequestContext.getEmployeeNo());
        }

        employees = employees.stream()
                .filter(e -> !RequestContext.getEmployeeNo().equalsIgnoreCase(e.getEmployeeNo()))
                .collect(Collectors.toList());
        List<Integer> subDeptIds = departmentBusiness.getSubDepartID(searchParams.getDepartID());

        List<String> result = employees.stream()
                .filter(e -> StringUtils.isEmpty(searchParams.getEmployeeName())
                        || e.getEmployeeNo().equalsIgnoreCase(searchParams.getEmployeeName()))
                .filter(e -> CollectionUtils.isEmpty(subDeptIds) || subDeptIds.contains(e.getDepartmentId()))
                .map(SwEmployee::getEmployeeNo)
                .collect(Collectors.toList());

        List<Integer> levels = getDeptLevels(searchParams.getLevelType());
        if (!StringUtils.isEmpty(searchParams.getEmployeeName()) || CollectionUtils.isEmpty(levels)) {
            return result;
        } else {
            List<String> tempList = PositionConfig.getPositionEmployees(levels);
            result = employees.stream()
                    .filter(e -> tempList.contains(e.getEmployeeNo()))
                    .filter(e -> CollectionUtils.isEmpty(subDeptIds) || subDeptIds.contains(e.getDepartmentId()))
                    .map(SwEmployee::getEmployeeNo)
                    .collect(Collectors.toList());
        }
        return result;
    }

    private List<Integer> getDeptLevels(Integer levelType) {
        if (Objects.isNull(levelType) || levelType == 0) {
            return Lists.newArrayList();
        }

        return Lists.newArrayList(levelType);
    }
}
