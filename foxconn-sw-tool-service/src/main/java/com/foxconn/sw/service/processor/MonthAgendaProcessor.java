package com.foxconn.sw.service.processor;

import com.foxconn.sw.common.utils.LocalDateExtUtils;
import com.foxconn.sw.data.dto.entity.home.AgendaVo;
import com.foxconn.sw.data.dto.request.schedule.MyScheduleParams;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class MonthAgendaProcessor {

    public List<AgendaVo> agenda(MyScheduleParams data) {
        List<String> days = getMonthDays(data);
        List<AgendaVo> agendaVos = new ArrayList<>();
        return agendaVos;
    }

    public List<String> getMonthDays(MyScheduleParams data) {
        YearMonth yearMonth = YearMonth.of(data.getYear(), data.getMonth());

        LocalDate startDay = yearMonth.atDay(1);
        LocalDate endDay = yearMonth.atEndOfMonth();

        int i = 0;
        long daysBetween = ChronoUnit.DAYS.between(startDay, endDay);
        List<String> days = new ArrayList<>();
        while (i <= daysBetween) {
            String day = LocalDateExtUtils.toString(startDay.plusDays(i++));
            System.out.println(day);
            days.add(day);
        }
        return days;
    }
}
