package com.foxconn.sw.service.processor.schedule;

import com.foxconn.sw.business.SwScheduleInfoBusiness;
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

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TeamScheduleProcessor {
    @Autowired
    SwScheduleInfoBusiness scheduleInfoBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;

    public List<TeamScheduleListVo> teamSchedule(ScheduleListParams data) {

        List<SwEmployee> employees = employeeBusiness.getSubordinateEmployee(RequestContext.getEmployeeNo());
        List<String> employeeNos = employees.stream().map(SwEmployee::getEmployeeNo).collect(Collectors.toList());
        List<SwScheduleInfo> scheduleInfos = scheduleInfoBusiness.getTeamScheduleInfos(employeeNos, data.getStartDate(), data.getEndDate());

        LocalDate startDay = LocalDateExtUtils.toLocalDate(data.getStartDate());
        long daysBetween = DateTimeUtils.getBetweenDay(data.getStartDate(), data.getEndDate());

        List<TeamScheduleListVo> scheduleListVos = Lists.newArrayList();

        for (SwEmployee employee : employees) {

            int i = 0;
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
                vo.setDestination(optional.map(SwScheduleInfo::getPlace).orElse(employee.getStationedPlace()));
                vo.setPlace(StringUtils.isEmpty(employee.getStationedPlace()) ? "龍華" : employee.getStationedPlace());
                vo.setType(optional.map(SwScheduleInfo::getType).orElse(ScheduleTypeEnums.Working.name()));
                vo.setCurrent(currentDate.equalsIgnoreCase(LocalDateExtUtils.toString(LocalDate.now())));

                scheduleListVos.add(vo);
            }
        }

        return scheduleListVos;
    }
}
