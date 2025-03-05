package com.foxconn.sw.service.processor.schedule;

import com.foxconn.sw.business.SwScheduleInfoBusiness;
import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.LocalDateExtUtils;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.enums.ScheduleTypeEnums;
import com.foxconn.sw.data.dto.request.schedule.ScheduleListParams;
import com.foxconn.sw.data.dto.response.schedule.TeamScheduleListVo;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwScheduleInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TeamScheduleProcessor {
    @Autowired
    SwScheduleInfoBusiness scheduleInfoBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    DepartmentBusiness departmentBusiness;

    public List<TeamScheduleListVo> teamSchedule(ScheduleListParams data) {
        List<SwEmployee> employees = getEmployeeNos2(data);
        if (CollectionUtils.isEmpty(employees)) {
            return Lists.newArrayList();
        }

        List<String> employeeNos = employees.stream()
                .map(SwEmployee::getEmployeeNo)
                .collect(Collectors.toList());

        List<SwScheduleInfo> scheduleInfos = scheduleInfoBusiness.getTeamScheduleInfos(employeeNos, data);

        LocalDate startDay = LocalDateExtUtils.toLocalDate(data.getStartDate());
        long daysBetween = DateTimeUtils.getBetweenDay(data.getStartDate(), data.getEndDate());

        List<TeamScheduleListVo> scheduleListVos = Lists.newArrayList();

        for (SwEmployee employee : employees) {
            int i = 0;

            if (StringUtils.isNotEmpty(data.getDestination())
                    && !scheduleInfos.stream().anyMatch(e -> employee.getEmployeeNo().equalsIgnoreCase(e.getEmployeeNo()))) {
                continue;
            }

            if (employee.getEmployeeNo().equalsIgnoreCase(RequestContext.getEmployeeNo())) {
                continue;
            }

            while (i <= daysBetween) {
                LocalDate current = startDay.plusDays(i++);
                String currentDate = LocalDateExtUtils.toString(current);

                Optional<SwScheduleInfo> optional = scheduleInfos
                        .stream()
                        .filter(e -> e.getEmployeeNo().equalsIgnoreCase(employee.getEmployeeNo()) && currentDate.equalsIgnoreCase(e.getDate()))
                        .sorted(Comparator.comparing(SwScheduleInfo::getCreateTime))
                        .findFirst();

                TeamScheduleListVo vo = new TeamScheduleListVo();
                // 定义周的计算规则（示例使用ISO标准：每周从周一开始，第一周至少有4天在当年）
                WeekFields weekFields = WeekFields.ISO;
                vo.setWeekInfo(current.get(weekFields.weekOfWeekBasedYear()));
                vo.setName(employee.getName());
                vo.setDate(currentDate);
                vo.setDestination(optional.map(SwScheduleInfo::getPlace).orElse(""));
                vo.setType(optional.map(SwScheduleInfo::getType).orElse(ScheduleTypeEnums.Working.name()));
                vo.setCurrent(currentDate.equalsIgnoreCase(LocalDateExtUtils.toString(LocalDate.now())));

                scheduleListVos.add(vo);
            }
        }

        return scheduleListVos;
    }

    private List<SwEmployee> getEmployeeNos2(ScheduleListParams data) {

        List<SwEmployee> employees = employeeBusiness.getSubordinateEmployee(RequestContext.getEmployeeNo());

        if (!CollectionUtils.isEmpty(data.getManageLeve())) {
            // 管理層級過濾
            employees = employees.stream()
                    .filter(e -> data.getManageLeve().contains(e.getManagerLevel()))
                    .collect(Collectors.toList());
        }

        if (Objects.nonNull(data.getDepartmentId()) && data.getDepartmentId() > 0) {
            // 部門過濾
            List<Integer> departmentIds = departmentBusiness.getSubDepartID(data.getDepartmentId());
            employees = employees.stream()
                    .filter(e -> departmentIds.contains(e.getDepartmentId()))
                    .collect(Collectors.toList());
        }

        if (StringUtils.isNotEmpty(data.getEmployeeNo())) {
            // 人員過濾
            employees = employees.stream()
                    .filter(e -> data.getEmployeeNo().contains(e.getEmployeeNo()))
                    .collect(Collectors.toList());
        }

        if (!CollectionUtils.isEmpty(data.getIdentityOfCadre())) {
            // 幹部身份
            employees = employees.stream()
                    .filter(e -> data.getIdentityOfCadre().contains(e.getIdentityOfCadre()))
                    .collect(Collectors.toList());
        }

//        if (StringUtils.isNotEmpty(data.getStationedPlace())) {
//            // 常駐地身份
//            employees = employees.stream()
//                    .filter(e -> data.getStationedPlace().equalsIgnoreCase(e.getStationedPlace()))
//                    .collect(Collectors.toList());
//        }

        return employees;
    }

}
