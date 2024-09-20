package com.foxconn.sw.service.processor.oa.utils;

import com.foxconn.sw.common.utils.WeekUtils;
import com.foxconn.sw.data.dto.entity.oa.ReportSearchParams;
import com.foxconn.sw.data.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static com.foxconn.sw.common.utils.DateTimeUtils.DateTimePattern.yyyyMMdd1;
import static com.foxconn.sw.data.constants.enums.retcode.RetCode.VALIDATE_FAILED;

public class ReportSearchParamsUtils {

    public static List<String> getYearWeekPair(ReportSearchParams searchParams, boolean isExport) {
        LocalDate startDate;
        LocalDate endDate;
        if (isExport) {
            int timeSpan = getTimeSpan(searchParams.getStartDate(), searchParams.getEndDate());
            if (timeSpan < 7) {
                startDate = getFirstWeekMonday(LocalDate.now().getYear());
                endDate = LocalDate.parse(searchParams.getEndDate(),
                        DateTimeFormatter.ofPattern(yyyyMMdd1));
            } else {
                startDate = LocalDate.parse(searchParams.getStartDate(),
                        DateTimeFormatter.ofPattern(yyyyMMdd1));
                endDate = LocalDate.parse(searchParams.getEndDate(),
                        DateTimeFormatter.ofPattern(yyyyMMdd1));
            }
        } else {
            if (StringUtils.isNotEmpty(searchParams.getWeekOfStart())) {
                startDate = LocalDate.parse(searchParams.getWeekOfStart(),
                        DateTimeFormatter.ofPattern(yyyyMMdd1));
                endDate = LocalDate.parse(searchParams.getWeekOfStart(),
                        DateTimeFormatter.ofPattern(yyyyMMdd1)).plusWeeks(1);
            } else {
                if (StringUtils.isEmpty(searchParams.getStartDate()) || StringUtils.isEmpty(searchParams.getEndDate())) {
                    throw new BizException(VALIDATE_FAILED);
                }

                startDate = LocalDate.parse(searchParams.getStartDate(), DateTimeFormatter.ofPattern(yyyyMMdd1));
                endDate = LocalDate.parse(searchParams.getEndDate(), DateTimeFormatter.ofPattern(yyyyMMdd1));
            }
        }
        List<String> result = new ArrayList<>();
        while (startDate.compareTo(endDate) < 1) {
            String yearWeek = processDate(startDate);
            result.add(yearWeek);
            startDate = startDate.plusWeeks(1);
        }
        return result;
    }

    public static int getTimeSpan(String startDate, String endDate) {
        LocalDate date_s = LocalDate.parse(startDate, DateTimeFormatter.ofPattern(yyyyMMdd1));
        LocalDate date_e = LocalDate.parse(endDate, DateTimeFormatter.ofPattern(yyyyMMdd1));
        long daysBetween = ChronoUnit.DAYS.between(date_s, date_e);
        return (int) daysBetween;
    }

    private static LocalDate getFirstWeekMonday(int year) {
        LocalDate date = LocalDate.of(year, 1, 1);
        while (date.getDayOfWeek() != DayOfWeek.MONDAY) {
            date = date.plusDays(1);
        }
        return date;
    }


    private static String processDate(LocalDate searchDate) {
        Pair<Integer, Integer> pair = WeekUtils.getWeekOfYearAndYear(searchDate);
        return String.format("%s-%02d", pair.getRight(), pair.getLeft());
    }

    public static String processDate(String searchDate) {
        Pair<Integer, Integer> pair = WeekUtils.getWeekOfYearAndYear(searchDate);
        return String.format("%s%02d", pair.getRight(), pair.getLeft());
    }
}
