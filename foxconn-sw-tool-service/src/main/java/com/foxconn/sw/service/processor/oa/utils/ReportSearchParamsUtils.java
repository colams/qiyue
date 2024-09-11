package com.foxconn.sw.service.processor.oa.utils;

import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.WeekUtils;
import com.foxconn.sw.data.dto.entity.oa.ReportSearchParams;
import com.foxconn.sw.data.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.VALIDATE_FAILED;

public class ReportSearchParamsUtils {

    public static List<String> getYearWeekPair(ReportSearchParams searchParams, boolean isExport) {
        LocalDate startDate;
        LocalDate endDate;
        if (isExport) {
            startDate = getFirstWeekMonday(LocalDate.now().getYear());
            endDate = LocalDate.now().plusWeeks(1);
        } else {

            if (StringUtils.isNotEmpty(searchParams.getWeekOfStart())) {
                startDate = LocalDate.parse(searchParams.getWeekOfStart(), DateTimeFormatter.ofPattern(DateTimeUtils.DateTimePattern.yyyyMMdd1));
                endDate = LocalDate.parse(searchParams.getWeekOfStart(), DateTimeFormatter.ofPattern(DateTimeUtils.DateTimePattern.yyyyMMdd1)).plusWeeks(1);
            } else {
                if (StringUtils.isEmpty(searchParams.getStartDate()) || StringUtils.isEmpty(searchParams.getEndDate())) {
                    throw new BizException(VALIDATE_FAILED);
                }

                startDate = LocalDate.parse(searchParams.getStartDate(), DateTimeFormatter.ofPattern(DateTimeUtils.DateTimePattern.yyyyMMdd1));
                endDate = LocalDate.parse(searchParams.getEndDate(), DateTimeFormatter.ofPattern(DateTimeUtils.DateTimePattern.yyyyMMdd1));
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

    private static LocalDate getFirstWeekMonday(int year) {
        LocalDate date = LocalDate.of(year, 1, 1);
        while (date.getDayOfWeek() != DayOfWeek.MONDAY) {
            date = date.plusDays(1);
        }
        return date;
    }

    private static String processStartDate(String searchDate) {
        Pair<Integer, Integer> pair = WeekUtils.getWeekOfYearAndYear(searchDate);
        return String.format("%s-%02d", pair.getRight(), pair.getLeft());
    }

    private static String processDate(String searchDate, int adjustWeeks) {
        LocalDate localDate = LocalDate.parse(searchDate, DateTimeFormatter.ofPattern(DateTimeUtils.DateTimePattern.yyyyMMdd1));
        return processDate(localDate, adjustWeeks);
    }


    private static String processDate(LocalDate searchDate, int adjustWeeks) {
        Pair<Integer, Integer> pair = WeekUtils.getWeekOfYearAndYear(searchDate.plusWeeks(adjustWeeks));
        return String.format("%s-%02d", pair.getRight(), pair.getLeft());
    }

    private static String processDate(LocalDate searchDate) {
        Pair<Integer, Integer> pair = WeekUtils.getWeekOfYearAndYear(searchDate);
        return String.format("%s-%02d", pair.getRight(), pair.getLeft());
    }

    private static String processEndDate(String searchDate, int adjustWeeks) {
        return processDate(searchDate, adjustWeeks);
    }

}
