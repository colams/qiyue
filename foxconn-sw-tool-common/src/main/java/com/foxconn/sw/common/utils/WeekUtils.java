package com.foxconn.sw.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class WeekUtils {

    /**
     * @return Left：week，Right：year
     */
    public static Pair<Integer, Integer> getWeekYearInfo() {
        return getWeekYearInfo(null);
    }

    /**
     * @return Left：week，Right：year
     */
    public static Pair<Integer, Integer> getWeekYearInfo(String weekOfStart) {
        LocalDate date = LocalDate.now();
        if (!StringUtils.isEmpty(weekOfStart)) {
            date = LocalDate.parse(weekOfStart);
        }
        WeekFields weekFields = WeekFields.of(Locale.CHINA);
        int week = date.get(weekFields.weekOfWeekBasedYear());
        int year = date.get(weekFields.weekBasedYear());
        return Pair.of(week, year);
    }

    /**
     * @return
     */
    public static int getWeekOfYear(String weekOfStart) {
        LocalDate date = LocalDate.now();
        if (!StringUtils.isEmpty(weekOfStart)) {
            date = LocalDate.parse(weekOfStart);
        }
        return getWeekOfYearAndYear(date).getLeft();
    }

    /**
     * @return
     */
    public static int getWeekNumberOfYear(LocalDate date) {
        LocalDate startOfYear = LocalDate.of(date.getYear(), 1, 1);
        int daysSinceStart = date.getDayOfYear() - startOfYear.getDayOfYear();
        int adjustedDaysSinceStart = daysSinceStart + ((startOfYear.getDayOfWeek().getValue() == 7) ? 1 : (startOfYear.getDayOfWeek().getValue() >= 2 ? 8 - startOfYear.getDayOfWeek().getValue() : 1 - startOfYear.getDayOfWeek().getValue()));
        return adjustedDaysSinceStart / 7 + 1;
    }

    /**
     * 根据日期获取对应的周数以及对应的年份
     *
     * @param date 日期 如果为null 默认为请求日期
     * @return Left：week，Right：year
     */
    public static Pair<Integer, Integer> getWeekOfYearAndYear(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(DateTimeUtils.DateTimePattern.yyyyMMdd1));
        return getWeekOfYearAndYear(localDate);
    }

    /**
     * 根据日期获取对应的周数以及对应的年份
     *
     * @param date 日期 如果为null 默认为请求日期
     * @return Left：week，Right：year
     */
    public static String getWeekOfYearAndYearStr(LocalDate date) {
        Pair<Integer, Integer> pair = WeekUtils.getWeekOfYearAndYear(date);
        return String.format("%s%02d", pair.getRight(), pair.getLeft());
    }

    /**
     * 根据日期获取对应的周数以及对应的年份
     *
     * @param date 日期 如果为null 默认为请求日期
     * @return Left：week，Right：year
     */
    public static Pair<Integer, Integer> getWeekOfYearAndYear(LocalDate date) {
        int weekNumber = 0;
        int year = date.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
        weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);
        if (date.getMonthValue() == 12 && weekNumber == 1) {
            year += 1;
        }
        return Pair.of(weekNumber, year);
    }

    public static LocalDateTime getLastDayOfNextWeek(String weekOfStart) {
        LocalDate date = LocalDate.parse(weekOfStart).plusWeeks(1);
        LocalDate startOfWeek = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return LocalDateTime.of(startOfWeek, LocalTime.MAX);
    }

    public static LocalDateTime getStartDayOfWeek(String weekOfStart) {
        LocalDate date = LocalDate.parse(weekOfStart);
        LocalDate startOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return LocalDateTime.of(startOfWeek, LocalTime.MIN);
    }


    public static LocalDateTime getFirstDayOfYearFromDateString(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate startDate = LocalDate.of(date.getYear(), 1, 1);
        return LocalDateTime.of(startDate, LocalTime.MIN);
    }

    public static List<Integer> getWeeksOfYearInfo(String weekOfStart) {
        LocalDate date = LocalDate.parse(weekOfStart);
        List<Integer> results = new ArrayList<>();
        results.add(getWeekOfYearAndYear(date).getLeft());
        results.add(getWeekOfYearAndYear(date.plusWeeks(1)).getLeft());
        return results;
    }
}
