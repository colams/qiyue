package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.oa.SwWorkReportBusiness;
import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.oa.ReportSearchParams;
import com.foxconn.sw.data.dto.entity.oa.WorkReportDetail;
import com.foxconn.sw.data.dto.entity.oa.WorkReportVo;
import com.foxconn.sw.data.entity.SwDepartment;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwWorkReport;
import com.foxconn.sw.data.exception.BizException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public List<WorkReportVo> listReport(ReportSearchParams searchParams) {
        List<String> employees = getEmployeeNos(searchParams.getSearchType());

        if (CollectionUtils.isEmpty(employees)) {
            throw new BizException(VALIDATE_FAILED);
        }

        List<SwWorkReport> reports = reportBusiness.queryReport(searchParams, employees);
        List<WorkReportVo> vos = new ArrayList<>();
        reports.forEach(e -> {
            WorkReportVo vo = vos.stream().filter(v -> e.getWeek() == v.getWeek() && v.getEmployee().getEmployeeNo().equalsIgnoreCase(e.getEmployeeNo())).findFirst().orElse(null);
            WorkReportDetail detail = initDetail(e);
            if (Objects.isNull(vo)) {
                vo = new WorkReportVo();
                vo.setWeek(e.getWeek());
                EmployeeVo employeeVo = new EmployeeVo();
                employeeVo.setEmployeeNo(e.getEmployeeNo());
                employeeVo.setName(getEmployeeName(e.getEmployeeNo()));
                vo.setEmployee(employeeVo);
                vo.setReportDetailList(Lists.newArrayList(detail));
                vos.add(vo);
            } else {
                vo.getReportDetailList().add(detail);
            }
        });

        employees.forEach(e -> {
            List<Integer> listWeeks = vos.stream().map(WorkReportVo::getWeek).collect(Collectors.toList());
            for (Integer week : listWeeks) {
                vos.stream()
                        .filter(o -> o.getWeek() == week && e.equalsIgnoreCase(o.getEmployee().getEmployeeNo()))
                        .findFirst()
                        .orElseGet(() -> {
                            WorkReportVo defaultVo = getDefaultVo(week, e);
                            vos.add(defaultVo);
                            return defaultVo;
                        });
            }
        });

        return vos;
    }

    private WorkReportVo getDefaultVo(Integer week, String employeeNo) {
        EmployeeVo employeeVo = new EmployeeVo();
        employeeVo.setEmployeeNo(employeeNo);
        employeeVo.setName(getEmployeeName(employeeNo));

        WorkReportVo vo = new WorkReportVo();
        vo.setWeek(week);
        vo.setEmployee(employeeVo);
        vo.setMessage("週報未提交");
        return vo;
    }

    private WorkReportDetail initDetail(SwWorkReport e) {
        WorkReportDetail detail = new WorkReportDetail();
        detail.setId(e.getId());
        detail.setNum(e.getNum());
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

    private List<String> getEmployeeNos(Integer searchType) {
        List<String> list = Lists.newArrayList(RequestContext.getEmployeeNo());
        if (Objects.nonNull(searchType) && searchType == 2) {
            List<Integer> departIds = getMangeDepart(RequestContext.getEmployeeNo());
            list = employeeBusiness.getEmployeeList()
                    .stream()
                    .filter(e -> departIds.contains(e.getDepartmentId()))
                    .map(SwEmployee::getEmployeeNo)
                    .collect(Collectors.toList());
        }
        return list;
    }

    public List<Integer> getMangeDepart(String employeeNo) {
        List<SwDepartment> departments = departmentBusiness.getDepartment();
        List<SwDepartment> directDepartments = departmentBusiness.getDepartment(employeeNo);
        List<Integer> departIds = getAllMangeDepart(departments, directDepartments)
                .stream()
                .map(SwDepartment::getId)
                .collect(Collectors.toList());
        return departIds;
    }

    private List<SwDepartment> getAllMangeDepart(List<SwDepartment> departments, List<SwDepartment> directDepartments) {
        List<SwDepartment> departmentList = new ArrayList<>();
        departmentList.addAll(directDepartments);
        List<SwDepartment> temps = departments.stream()
                .filter(e -> directDepartments.stream()
                        .anyMatch(ed -> e.getParentId() == ed.getId()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(temps)) {
            return departmentList;
        }
        List<SwDepartment> nextDeparts = getAllMangeDepart(departments, temps);
        if (!CollectionUtils.isEmpty(nextDeparts)) {
            departmentList.addAll(nextDeparts);
        }
        departmentList.addAll(temps);
        return departmentList;
    }
}
