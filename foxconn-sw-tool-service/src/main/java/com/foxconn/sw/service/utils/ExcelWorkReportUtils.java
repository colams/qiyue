package com.foxconn.sw.service.utils;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.WeekUtils;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.oa.WorkReportDetail;
import com.foxconn.sw.data.dto.entity.oa.WorkReportVo;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import java.awt.Color;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ExcelWorkReportUtils {

    private static String Font_Arial = "Arial";
    private static String Font_標楷體 = "標楷體";
    private static String Font_ArialUnicodeMS = "Arial Unicode MS";
    private static Integer FontSize_6 = 6;
    private static Integer FontSize_8 = 8;
    private static Integer FontSize_9 = 9;
    private static Integer FontSize_10 = 10;
    private static Integer FontSize_12 = 12;
    private static Integer FontSize_14 = 14;
    private static Integer FontSize_16 = 16;

    // RGB值 -标题颜色
    private static XSSFColor Title_Color = new XSSFColor(new Color(153, 204, 255), new DefaultIndexedColorMap());
    // RGB值 -计划颜色
    private static XSSFColor Plan_Color = new XSSFColor(new Color(255, 204, 153), new DefaultIndexedColorMap());
    // RGB值 -当前周颜色
    private static XSSFColor Current_Color = new XSSFColor(new Color(204, 255, 204), new DefaultIndexedColorMap());
    // RGB值 -历史颜色
    private static XSSFColor History_Color = new XSSFColor(new Color(192, 192, 192), new DefaultIndexedColorMap());

    // RGB值 -计划颜色
    private static XSSFColor S_Plan_Color = new XSSFColor(new Color(255, 204, 153), new DefaultIndexedColorMap());
    // RGB值 -历史颜色
    private static XSSFColor S_History_Color = new XSSFColor(new Color(192, 192, 192), new DefaultIndexedColorMap());
    // RGB值 -计划颜色
    private static XSSFColor Items_Color = new XSSFColor(new Color(255, 153, 204), new DefaultIndexedColorMap());

    // RGB值 -计划颜色
    private static XSSFColor Red_Color = new XSSFColor(new Color(255, 0, 0), new DefaultIndexedColorMap());

    // RGB值 -计划颜色
    private static XSSFColor Reach_Color = new XSSFColor(new Color(51, 153, 102), new DefaultIndexedColorMap());

    // RGB值 -计划颜色
    private static XSSFColor Gray_Color = new XSSFColor(new Color(192, 192, 192), new DefaultIndexedColorMap());

    public static Workbook generateExcel(List<WorkReportVo> vos, String startDate, String endDate) {
        Map<String, List<WorkReportVo>> map = vos.stream()
                .collect(Collectors.groupingBy(e -> e.getEmployee().getName()));

        // 创建Excel工作簿
        Workbook workbook = new XSSFWorkbook();
        Sheet statusSheet = workbook.createSheet("Status");
        processStatusSheet(workbook, statusSheet, map);

        for (Map.Entry<String, List<WorkReportVo>> entry : map.entrySet()) {
            Sheet sheet = workbook.createSheet(entry.getKey());
            processReport(workbook, sheet, entry.getValue(), startDate, endDate);
        }

        return workbook;
    }

    private static void processReport(Workbook workbook, Sheet sheet, List<WorkReportVo> vos, String startDate, String endDate) {
        int rowNum = 0;
        Row headerRow0 = sheet.createRow(rowNum++);

        CellStyle l_style = style(workbook, Font_Arial, FontSize_6, Title_Color, false);
        CellStyle m_style = style(workbook, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, true, Font_Arial, FontSize_14, Title_Color, false);
        CellStyle r_style = style(workbook, Font_Arial, FontSize_8, true, Title_Color, false);

        Cell cell0 = headerRow0.createCell(0);
        cell0.setCellValue("Template_WeeklyReport_V3.0");
        cell0.setCellStyle(l_style);
        Cell cell1 = headerRow0.createCell(1);
        cell1.setCellValue("Weekly Report");
        cell1.setCellStyle(m_style);
        Cell cell2 = headerRow0.createCell(6);
        cell2.setCellValue("Week: WK" + vos.get(0).getWeek());
        cell2.setCellStyle(r_style);

        CellRangeAddress cellAddressTitle = new CellRangeAddress(
                0, 1, 1, 5);
        sheet.addMergedRegion(cellAddressTitle);

        Row headerRow1 = sheet.createRow(rowNum++);
        Cell cell20 = headerRow1.createCell(0);
        cell20.setCellStyle(r_style);

        Cell cell26 = headerRow1.createCell(6);
        cell26.setCellValue(String.format("Date:%s~%s", DateTimeUtils.format(startDate, "MM/dd"), DateTimeUtils.format(endDate, "MM/dd")));
        cell26.setCellStyle(r_style);

        if (rowNum == 2) {
            Row headerRow = sheet.createRow(rowNum++);

            Cell cell30 = headerRow.createCell(0);
            Cell cell31 = headerRow.createCell(1);
            Cell cell32 = headerRow.createCell(2);
            Cell cell33 = headerRow.createCell(3);
            Cell cell34 = headerRow.createCell(4);
            Cell cell35 = headerRow.createCell(5);
            Cell cell36 = headerRow.createCell(6);

            CellStyle titleStyle = style(workbook, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, true, Font_Arial, FontSize_10, true, Title_Color);

            cell30.setCellValue("Week");
            cell30.setCellStyle(titleStyle);
            cell31.setCellValue("Name");
            cell31.setCellStyle(titleStyle);
            cell32.setCellValue("Tasks description");
            cell32.setCellStyle(titleStyle);
            cell33.setCellValue("Effort Paid in Days");
            cell33.setCellStyle(titleStyle);
            cell34.setCellValue("Target Progress (%)");
            cell34.setCellStyle(titleStyle);
            cell35.setCellValue("Current Progress (%)");
            cell35.setCellStyle(titleStyle);
            cell36.setCellValue("Remark");
            cell36.setCellStyle(titleStyle);
        }

        int colorFlag = 0;
        for (int index = 0; index < vos.size(); index++) {
            WorkReportVo vo = vos.get(index);
            if (CollectionUtils.isEmpty(vo.getReportDetailList())) {
                continue;
            }
            XSSFColor baseColor = Gray_Color;
            if (colorFlag == 0) {
                baseColor = Plan_Color;
            } else if (colorFlag == 1) {
                baseColor = Current_Color;
            }

            CellStyle baseCellStyle = contentStyle(workbook, baseColor, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            CellStyle baseContentCellStyle = contentStyle(workbook, baseColor, HorizontalAlignment.LEFT, VerticalAlignment.TOP);

            CellStyle redStyle = contentStyle(workbook, Red_Color);
            CellStyle reachStyle = contentStyle(workbook, Reach_Color);


            int lastRow = rowNum;
            Row row = sheet.createRow(rowNum++);
            Cell contentCell0 = row.createCell(0);
            Cell contentCell1 = row.createCell(1);

            contentCell0.setCellValue("WK" + vo.getWeek());
            contentCell0.setCellStyle(baseCellStyle);

            contentCell1.setCellValue(vo.getEmployee().getName());
            contentCell1.setCellStyle(baseCellStyle);

            for (int i = 0; i < vo.getReportDetailList().size(); i++) {
                Row currentRow = i == 0 ? row : sheet.createRow(rowNum++);
                if (i > 0) {
                    Cell contentCell00 = currentRow.createCell(0);
                    contentCell00.setCellStyle(baseCellStyle);
                    Cell contentCell01 = currentRow.createCell(1);
                    contentCell01.setCellStyle(baseCellStyle);
                }
                boolean isFinish = vo.getReportDetailList().get(i).getCurrent() == 100;
                boolean isReach = vo.getReportDetailList().get(i).getCurrent() == vo.getReportDetailList().get(i).getTarget();


                Cell contentCell2 = currentRow.createCell(2);
                contentCell2.setCellValue(vo.getReportDetailList().get(i).getDescription());
                contentCell2.setCellStyle(baseContentCellStyle);
                Cell contentCell3 = currentRow.createCell(3);
                contentCell3.setCellValue(vo.getReportDetailList().get(i).getDay());
                contentCell3.setCellStyle(baseCellStyle);

                Cell contentCell4 = currentRow.createCell(4);
                contentCell4.setCellValue(vo.getReportDetailList().get(i).getTarget());
                contentCell4.setCellStyle(baseCellStyle);
                Cell contentCell5 = currentRow.createCell(5);
                contentCell5.setCellValue(vo.getReportDetailList().get(i).getCurrent());

                CellStyle curStyle = baseCellStyle;
                if (colorFlag > 0) {
                    curStyle = isFinish ? baseCellStyle : isReach ? reachStyle : redStyle;
                }
                contentCell5.setCellStyle(curStyle);

                Cell contentCell6 = currentRow.createCell(6);
                contentCell6.setCellValue(vo.getReportDetailList().get(i).getRemark());
                contentCell6.setCellStyle(baseContentCellStyle);
            }
            lastRow += vo.getReportDetailList().size() - 1;
            colorFlag++;

            if (row.getRowNum() < lastRow) {
                CellRangeAddress cellAddressWeek = new CellRangeAddress(
                        row.getRowNum(), lastRow, 0, 0);
                CellRangeAddress cellAddressName = new CellRangeAddress(
                        row.getRowNum(), lastRow, 1, 1);
                sheet.addMergedRegion(cellAddressWeek);
                sheet.addMergedRegion(cellAddressName);
            }
        }
        autoSizeColumn(sheet, 7);
    }


    private static void processStatusSheet(Workbook workbook, Sheet sheet, Map<String, List<WorkReportVo>> map) {
        Integer weekNum = WeekUtils.getWeekNumberOfYear(LocalDateTime.now());
        UserInfo userInfo = RequestContext.getUserInfo();
        // 创建标题行
        CellStyle style1 = style(workbook, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, Font_Arial, FontSize_16, true, Title_Color);
        CellStyle style2 = style(workbook, HorizontalAlignment.RIGHT, VerticalAlignment.CENTER, Font_Arial, FontSize_9, true, Title_Color);
        CellStyle style3 = style(workbook, Font_Arial, FontSize_6, Title_Color);
        CellStyle style4 = style(workbook, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, Font_Arial, FontSize_12, true, History_Color, true);
        CellStyle style5 = style(workbook, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, Font_標楷體, FontSize_12, Current_Color, true);
        CellStyle style6 = style(workbook, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, Font_標楷體, FontSize_12, S_Plan_Color, true);
        CellStyle style7 = style(workbook, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, Font_標楷體, FontSize_12, Items_Color, true);

        Row titleRow0 = sheet.createRow(0);
        createCell(titleRow0, 0, "Template_WeeklyReportConsolidation_v2.0", style3);
        defaultCell(titleRow0, 1, style2);
        defaultCell(titleRow0, 2, style2);
        defaultCell(titleRow0, 3, style2);
        defaultCell(titleRow0, 4, style2);
        defaultCell(titleRow0, 5, style2);
        defaultCell(titleRow0, 6, style2);
        createCell(titleRow0, 7, "部門名稱", style2);
        createCell(titleRow0, 8, userInfo.getDepartName(), style2);

        Row titleRow1 = sheet.createRow(1);
        defaultCell(titleRow1, 0, style2);
        defaultCell(titleRow1, 1, style2);
        defaultCell(titleRow1, 2, style2);
        defaultCell(titleRow1, 3, style2);
        defaultCell(titleRow1, 4, style2);
        defaultCell(titleRow1, 5, style2);
        defaultCell(titleRow1, 6, style2);
        createCell(titleRow1, 7, "負責主管", style2);
        createCell(titleRow1, 8, userInfo.getEmployeeName(), style2);

        Row titleRow2 = sheet.createRow(2);
        defaultCell(titleRow2, 0, style2);
        createCell(titleRow2, 1, "Weekly Report Consolidation Status", style1);
        defaultCell(titleRow2, 2, style2);
        defaultCell(titleRow2, 3, style2);
        defaultCell(titleRow2, 4, style2);
        defaultCell(titleRow2, 5, style2);
        defaultCell(titleRow2, 6, style2);
        createCell(titleRow2, 7, "當前周號", style2);
        createCell(titleRow2, 8, weekNum, style2);

        CellRangeAddress cellTopicName = new CellRangeAddress(
                2, 2, 1, 4);
        sheet.addMergedRegion(cellTopicName);

        Row headerRow = sheet.createRow(3);
        headerRow.setHeightInPoints((short) 30);
        createCell(headerRow, 0, "序號", style4);
        createCell(headerRow, 1, "姓名", style4);
        createCell(headerRow, 2, "提交總週數", style4);
        createCell(headerRow, 3, "最新提交週號", style4);
        createCell(headerRow, 4, "本週記錄天數", style4);
        createCell(headerRow, 5, "本週工作項次", style4);
        createCell(headerRow, 6, "本週結案項次", style4);
        createCell(headerRow, 7, "狀態", style4);
        createCell(headerRow, 8, "Remark", style4);

        int rowNum = 4;
        int i = 0;
        for (Map.Entry<String, List<WorkReportVo>> entry : map.entrySet()) {
            Row row = sheet.createRow(rowNum + (i++));

            boolean isSubmit = entry.getValue().stream()
                    .anyMatch(e -> e.getWeek().equals(weekNum) && !CollectionUtils.isEmpty(e.getReportDetailList()));
            CellStyle style = isSubmit ? style5 : style6;

            int reportItems = getReportItems(entry.getValue(), weekNum);
            int finishItems = getFinishReportItems(entry.getValue(), weekNum);

            createCell(row, 0, i, style);
            createCell(row, 1, entry.getKey(), style);
            createCell(row, 2, entry.getValue().size(), style);
            createCell(row, 3, getLastWeekNumber(entry.getValue()), style);
            createCell(row, 4, getReportDays(entry.getValue(), weekNum), style);
            createCell(row, 5, reportItems, style);
            createCell(row, 6, finishItems, reportItems == finishItems ? style : style7);
            createCell(row, 7, getSubmitStatus(isSubmit), style);
            defaultCell(row, 8, style);
        }

        autoSizeColumn(sheet, 9);

        // 设置区域的边框，例如：第一行的A1:J1单元格区域
        int firstRow = 0;
        int firstCol = 0;

        int lastRow = sheet.getLastRowNum();
        int lastCol = 8; // 注意：列是从0开始的，所以10表示J列
        CellRangeAddress cellRangeAddress = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);

        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, cellRangeAddress, sheet);
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, cellRangeAddress, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, cellRangeAddress, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, cellRangeAddress, sheet);
    }

    private static Double getReportDays(List<WorkReportVo> reportVos, Integer weekNum) {
        return reportVos.stream()
                .filter(e -> e.getWeek() == weekNum)
                .flatMap(e -> e.getReportDetailList().stream())
                .mapToDouble(WorkReportDetail::getDay)
                .sum();
    }

    private static int getReportItems(List<WorkReportVo> reportVos, Integer weekNum) {
        return (int) reportVos.stream()
                .filter(e -> e.getWeek() == weekNum)
                .map(e -> {
                    e.setReportDetailList(Optional.ofNullable(e.getReportDetailList()).orElse(Lists.newArrayList()));
                    return e;
                })
                .flatMap(e -> e.getReportDetailList().stream())
                .count();
    }

    private static int getFinishReportItems(List<WorkReportVo> reportVos, Integer weekNum) {
        return (int) reportVos.stream()
                .filter(e -> e.getWeek() == weekNum)
                .map(e -> {
                    e.setReportDetailList(Optional.ofNullable(e.getReportDetailList()).orElse(Lists.newArrayList()));
                    return e;
                })
                .flatMap(e -> e.getReportDetailList().stream())
                .filter(e -> e.getCurrent() == 100)
                .count();
    }

    private static Integer getLastWeekNumber(List<WorkReportVo> reportVos) {
        return reportVos.stream()
                .filter(e -> !CollectionUtils.isEmpty(e.getReportDetailList()))
                .map(WorkReportVo::getWeek)
                .sorted(Comparator.comparing(Integer::intValue).reversed())
                .findFirst()
                .orElse(0);
    }

    private static String getSubmitStatus(boolean isSubmit) {
        return isSubmit ? "已提交" : "尚未提交";
    }

    private static void defaultCell(Row row, Integer number, CellStyle cellStyle) {
        Cell cell = row.createCell(number);
        cell.setCellStyle(cellStyle);
    }

    private static void createCell(Row row, Integer number, String text, CellStyle cellStyle) {
        Cell cell = row.createCell(number);
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);
    }

    private static void createCell(Row row, Integer number, Double text, CellStyle cellStyle) {
        Cell cell = row.createCell(number);
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);
    }

    private static void createCell(Row row, Integer number, Integer text, CellStyle cellStyle) {
        Cell cell = row.createCell(number);
        cell.setCellValue(text);
        cell.setCellStyle(cellStyle);
    }

    private static void autoSizeColumn(Sheet sheet, int length) {
        for (int columnIndex = 0; columnIndex < length; columnIndex++) {
            if (columnIndex > 0) {
                sheet.autoSizeColumn(columnIndex);
            }
        }
    }

    private static CellStyle contentStyle(Workbook workbook, XSSFColor color) {
        return style(workbook, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, true, Font_ArialUnicodeMS, FontSize_10, false, color);
    }

    private static CellStyle contentStyle(Workbook workbook,
                                          XSSFColor color,
                                          HorizontalAlignment hAlignment,
                                          VerticalAlignment vAlignment) {
        return style(workbook, hAlignment, vAlignment, true, Font_ArialUnicodeMS, FontSize_10, false, color);
    }

    /**
     * @param workbook
     * @param fontName Arial
     * @param fontSize 6
     * @param fontSize isBold
     * @return
     */
    private static Font font(Workbook workbook, String fontName, Integer fontSize, boolean isBold) {
        // 创建字体
        Font font = workbook.createFont();
        font.setFontName(fontName); // 设置字体名称
        font.setFontHeightInPoints(fontSize.shortValue()); // 设置字体大小
        font.setBold(isBold);
        return font;
    }

    /**
     * @param workbook
     * @param fontName
     * @param fontSize
     * @param color
     * @return
     */
    private static CellStyle style(Workbook workbook,
                                   String fontName,
                                   Integer fontSize,
                                   XSSFColor color,
                                   boolean hasBorder) {
        return style(workbook, HorizontalAlignment.LEFT, VerticalAlignment.BOTTOM, fontName, fontSize, false, color, hasBorder);
    }

    /**
     * @param workbook
     * @param fontName
     * @param fontSize
     * @param color
     * @return
     */
    private static CellStyle style(Workbook workbook,
                                   String fontName,
                                   Integer fontSize,
                                   XSSFColor color) {
        return style(workbook, HorizontalAlignment.LEFT, VerticalAlignment.BOTTOM, fontName, fontSize, false, color, false);
    }


    /**
     * @param workbook
     * @param fontName
     * @param fontSize
     * @param color
     * @return
     */
    private static CellStyle style(Workbook workbook,
                                   String fontName,
                                   Integer fontSize,
                                   boolean isBold,
                                   XSSFColor color,
                                   boolean hasBorder) {
        return style(workbook, HorizontalAlignment.LEFT, VerticalAlignment.BOTTOM, fontName, fontSize, isBold, color, hasBorder);
    }

    /**
     * 工作
     *
     * @param workbook
     * @param hAlignment
     * @param vAlignment
     * @param color
     * @return
     */
    private static CellStyle style(Workbook workbook,
                                   HorizontalAlignment hAlignment,
                                   VerticalAlignment vAlignment,
                                   String fontName,
                                   Integer fontSize,
                                   XSSFColor color,
                                   boolean hasBorder) {
        return style(workbook, hAlignment, vAlignment, false, fontName, fontSize, color, hasBorder);
    }

    /**
     * 工作
     *
     * @param workbook
     * @param hAlignment
     * @param vAlignment
     * @param color
     * @return
     */
    private static CellStyle style(Workbook workbook,
                                   HorizontalAlignment hAlignment,
                                   VerticalAlignment vAlignment,
                                   String fontName,
                                   Integer fontSize,
                                   boolean isBold,
                                   XSSFColor color,
                                   boolean hasBorder) {
        return style(workbook, hAlignment, vAlignment, false, fontName, fontSize, isBold, color, hasBorder);
    }

    /**
     * 工作
     *
     * @param workbook
     * @param hAlignment
     * @param vAlignment
     * @param color
     * @return
     */
    private static CellStyle style(Workbook workbook,
                                   HorizontalAlignment hAlignment,
                                   VerticalAlignment vAlignment,
                                   String fontName,
                                   Integer fontSize,
                                   boolean isBold,
                                   XSSFColor color) {
        return style(workbook, hAlignment, vAlignment, false, fontName, fontSize, isBold, color, false);
    }


    /**
     * 工作
     *
     * @param workbook
     * @param hAlignment
     * @param vAlignment
     * @param color
     * @return
     */
    private static CellStyle style(Workbook workbook,
                                   HorizontalAlignment hAlignment,
                                   VerticalAlignment vAlignment,
                                   boolean isBold,
                                   String fontName,
                                   Integer fontSize,
                                   XSSFColor color,
                                   boolean hasBorder) {
        return style(workbook, hAlignment, vAlignment, false, fontName, fontSize, isBold, color, hasBorder);
    }

    /**
     * 工作
     *
     * @param workbook
     * @param hAlignment
     * @param vAlignment
     * @param isWrap
     * @param color
     * @return
     */
    private static CellStyle style(Workbook workbook,
                                   HorizontalAlignment hAlignment,
                                   VerticalAlignment vAlignment,
                                   boolean isWrap,
                                   String fontName,
                                   Integer fontSize,
                                   boolean isBold,
                                   XSSFColor color) {
        return style(workbook, hAlignment, vAlignment, isWrap, fontName, fontSize, isBold, color, true);
    }

    /**
     * 工作
     *
     * @param workbook
     * @param hAlignment
     * @param vAlignment
     * @param isWrap
     * @param color
     * @return
     */
    private static CellStyle style(Workbook workbook,
                                   HorizontalAlignment hAlignment,
                                   VerticalAlignment vAlignment,
                                   boolean isWrap,
                                   String fontName,
                                   Integer fontSize,
                                   boolean isBold,
                                   XSSFColor color,
                                   boolean hasBorder) {
        CellStyle style = workbook.createCellStyle();

        style.setAlignment(hAlignment); // 设置水平居中
        style.setVerticalAlignment(vAlignment); // 设置垂直居中

        // 设置自动换行
        style.setWrapText(isWrap);
        // 设置文字样式大小
        style.setFont(font(workbook, fontName, fontSize, isBold));

        // 填充背景色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(color);

        // 设置边框
        if (hasBorder) {
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
        } else {
            style.setBorderTop(BorderStyle.NONE);
            style.setBorderBottom(BorderStyle.NONE);
            style.setBorderLeft(BorderStyle.NONE);
            style.setBorderRight(BorderStyle.NONE);
        }

        return style;
    }
}
