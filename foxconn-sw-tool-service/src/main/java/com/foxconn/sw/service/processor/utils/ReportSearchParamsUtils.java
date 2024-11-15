package com.foxconn.sw.service.processor.utils;

import com.foxconn.sw.common.utils.LocalDateExtUtils;
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
import java.util.Objects;

import static com.foxconn.sw.common.utils.DateTimeUtils.DateTimePattern.yyyyMMdd1;
import static com.foxconn.sw.data.constants.enums.retcode.RetCode.VALIDATE_FAILED;

public class ReportSearchParamsUtils {

    public static List<String> getYearWeekPair(ReportSearchParams searchParams, boolean isExport) {
        int searchType = Objects.isNull(searchParams.getSearchType()) ? 1 : searchParams.getSearchType();
        LocalDate startDate;
        LocalDate endDate;
        if (isExport) {
            int timeSpan = getTimeSpan(searchParams.getStartDate(), searchParams.getEndDate());
            if (timeSpan < 7) {
                startDate = getFirstWeekMonday(LocalDate.now().getYear());
                endDate = getSearchDate(searchParams.getEndDate(), searchType);
            } else {
                startDate = LocalDateExtUtils.toLocalDate(searchParams.getStartDate());
                endDate = LocalDateExtUtils.toLocalDate(searchParams.getEndDate());
            }
        } else {
            if (StringUtils.isNotEmpty(searchParams.getWeekOfStart())) {
                startDate = LocalDateExtUtils.toLocalDate(searchParams.getWeekOfStart());
                endDate = getSearchDate(searchParams.getEndDate(), searchType);
            } else {
                if (StringUtils.isEmpty(searchParams.getStartDate()) || StringUtils.isEmpty(searchParams.getEndDate())) {
                    throw new BizException(VALIDATE_FAILED);
                }

                startDate = LocalDateExtUtils.toLocalDate(searchParams.getStartDate());
                endDate = getSearchDate(searchParams.getEndDate(), searchType);
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

    public static LocalDate getSearchDate(String searchDateStr, int searchType) {
        LocalDate localDate = LocalDateExtUtils.toLocalDate(searchDateStr);
        if (searchType != 2) {
            localDate = localDate.plusWeeks(1);
        }
        return localDate;
    }

    public static int getTimeSpan(String startDate, String endDate) {
        LocalDate date_s = LocalDate.parse(startDate, DateTimeFormatter.ofPattern(yyyyMMdd1));
        LocalDate date_e = LocalDate.parse(endDate, DateTimeFormatter.ofPattern(yyyyMMdd1));
        long daysBetween = ChronoUnit.DAYS.between(date_s, date_e);
        return (int) daysBetween;
    }

    public static boolean lessThanWeek(String startDate, String endDate) {
        LocalDate date_s = LocalDate.parse(startDate, DateTimeFormatter.ofPattern(yyyyMMdd1));
        LocalDate date_e = LocalDate.parse(endDate, DateTimeFormatter.ofPattern(yyyyMMdd1));
        long daysBetween = ChronoUnit.DAYS.between(date_s, date_e);
        return daysBetween < 7;
    }

    private static LocalDate getFirstWeekMonday(int year) {
        LocalDate date = LocalDate.of(year, 1, 1);
        while (date.getDayOfWeek() != DayOfWeek.MONDAY) {
            date = date.plusDays(1);
        }
        return date;
    }


    public static String processDate(LocalDate searchDate) {
        Pair<Integer, Integer> pair = WeekUtils.getWeekOfYearAndYear(searchDate);
        return String.format("%s-%02d", pair.getRight(), pair.getLeft());
    }

    public static String processDate(String searchDate) {
        Pair<Integer, Integer> pair = WeekUtils.getWeekOfYearAndYear(searchDate);
        return String.format("%s%02d", pair.getRight(), pair.getLeft());
    }
}
