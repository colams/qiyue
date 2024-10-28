package com.foxconn.sw.service.utils;

import com.foxconn.sw.data.dto.entity.collaboration.CollaborationVo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.CollectionUtils;

import java.util.Map;

public class ExcelCollaborationUtils {


    public static Workbook generateExcel(CollaborationVo collaborationVo) {
        // 创建Excel工作簿
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("任務");
        processReport(workbook, sheet, collaborationVo);
        return workbook;
    }

    private static void processReport(Workbook workbook, Sheet sheet, CollaborationVo collaborationVo) {
        int rowNum = 0;

        if (!CollectionUtils.isEmpty(collaborationVo.getHeaders())) {
            Row sheetRow = sheet.createRow(rowNum++);
            for (int i = 0; i < collaborationVo.getHeaders().size(); i++) {
                CellStyle l_style = style(workbook, ExcelConstants.Font.Arial, ExcelConstants.FontSize.Size_6, ExcelConstants.Color.Title_Color, false);
                Cell cell0 = sheetRow.createCell(i);
                cell0.setCellValue(collaborationVo.getHeaders().get(i));
                cell0.setCellStyle(l_style);
            }
        }

        if (!CollectionUtils.isEmpty(collaborationVo.getContent())) {
            for (Map<String, Object> map : collaborationVo.getContent()) {
                Row sheetRow = sheet.createRow(rowNum++);
                for (int i = 0; i < collaborationVo.getHeaders().size(); i++) {
                    CellStyle l_style = style(workbook, ExcelConstants.Font.Arial, ExcelConstants.FontSize.Size_6, ExcelConstants.Color.Title_Color, false);
                    Cell cell0 = sheetRow.createCell(i);
                    cell0.setCellValue((String) map.getOrDefault(collaborationVo.getHeaders().get(i), ""));
                    cell0.setCellStyle(l_style);
                }
            }


//            for (int i = 0; i < collaborationVo.getContent().size(); i++) {
//
//
//                CellStyle l_style = style(workbook, ExcelConstants.Font.Arial, ExcelConstants.FontSize.Size_6, ExcelConstants.Color.Title_Color, false);
//                Cell cell0 = headerRow0.createCell(i);
//                cell0.setCellValue(collaborationVo.getHeaders().get(i));
//                cell0.setCellStyle(l_style);
//            }
        }

//
//        CellStyle l_style = style(workbook, Font_Arial, FontSize_6, Title_Color, false);
//        CellStyle m_style = style(workbook, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, true, Font_Arial, FontSize_14, Title_Color, false);
//        CellStyle r_style = style(workbook, Font_Arial, FontSize_8, true, Title_Color, false);

//        Cell cell0 = headerRow0.createCell(0);
//        cell0.setCellValue("Template_WeeklyReport_V3.0");
//        cell0.setCellStyle(l_style);
//        Cell cell1 = headerRow0.createCell(1);
//        cell1.setCellValue("Weekly Report");
//        cell1.setCellStyle(m_style);
//        Cell cell2 = headerRow0.createCell(6);
//        cell2.setCellValue("Week: WK33");
//        cell2.setCellStyle(r_style);
//
//        CellRangeAddress cellAddressTitle = new CellRangeAddress(
//                0, 1, 1, 5);
//        sheet.addMergedRegion(cellAddressTitle);
//
//        Row headerRow1 = sheet.createRow(rowNum++);
//        Cell cell20 = headerRow1.createCell(0);
//        cell20.setCellStyle(r_style);
//
//        Cell cell26 = headerRow1.createCell(6);
//        cell26.setCellValue("Date:8/10~8/16");
//        cell26.setCellStyle(r_style);

        if (rowNum == 2) {
            Row headerRow = sheet.createRow(rowNum++);

            Cell cell30 = headerRow.createCell(0);
            Cell cell31 = headerRow.createCell(1);
        }
//            Cell cell32 = headerRow.createCell(2);
//            Cell cell33 = headerRow.createCell(3);
//            Cell cell34 = headerRow.createCell(4);
//            Cell cell35 = headerRow.createCell(5);
//            Cell cell36 = headerRow.createCell(6);
//
//            CellStyle titleStyle = style(workbook, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, true, Font_Arial, FontSize_10, true, Title_Color);
//
//            cell30.setCellValue("Week");
//            cell30.setCellStyle(titleStyle);
//            cell31.setCellValue("Name");
//            cell31.setCellStyle(titleStyle);
//            cell32.setCellValue("Tasks description");
//            cell32.setCellStyle(titleStyle);
//            cell33.setCellValue("Effort Paid in Days");
//            cell33.setCellStyle(titleStyle);
//            cell34.setCellValue("Target Progress (%)");
//            cell34.setCellStyle(titleStyle);
//            cell35.setCellValue("Current Progress (%)");
//            cell35.setCellStyle(titleStyle);
//            cell36.setCellValue("Remark");
//            cell36.setCellStyle(titleStyle);
//        }
//
//        int colorFlag = 0;
//        for (int index = 0; index < vos.size(); index++) {
//            WorkReportVo vo = vos.get(index);
//            if (CollectionUtils.isEmpty(vo.getReportDetailList())) {
//                continue;
//            }
//            XSSFColor baseColor = Gray_Color;
//            if (colorFlag == 0) {
//                baseColor = Plan_Color;
//            } else if (colorFlag == 1) {
//                baseColor = Current_Color;
//            }
//
//            CellStyle baseCellStyle = contentStyle(workbook, baseColor, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
//            CellStyle baseContentCellStyle = contentStyle(workbook, baseColor, HorizontalAlignment.LEFT, VerticalAlignment.TOP);
//
//            CellStyle redStyle = contentStyle(workbook, Red_Color);
//            CellStyle reachStyle = contentStyle(workbook, Reach_Color);
//
//
//            int lastRow = rowNum;
//            Row row = sheet.createRow(rowNum++);
//            Cell contentCell0 = row.createCell(0);
//            Cell contentCell1 = row.createCell(1);
//
//            contentCell0.setCellValue("WK" + vo.getWeek());
//            contentCell0.setCellStyle(baseCellStyle);
//
//            contentCell1.setCellValue(vo.getEmployee().getName());
//            contentCell1.setCellStyle(baseCellStyle);
//
//            for (int i = 0; i < vo.getReportDetailList().size(); i++) {
//                Row currentRow = i == 0 ? row : sheet.createRow(rowNum++);
//                if (i > 0) {
//                    Cell contentCell00 = currentRow.createCell(0);
//                    contentCell00.setCellStyle(baseCellStyle);
//                    Cell contentCell01 = currentRow.createCell(1);
//                    contentCell01.setCellStyle(baseCellStyle);
//                }
//                boolean isFinish = vo.getReportDetailList().get(i).getCurrent() == 100;
//                boolean isReach = vo.getReportDetailList().get(i).getCurrent() == vo.getReportDetailList().get(i).getTarget();
//
//
//                Cell contentCell2 = currentRow.createCell(2);
//                contentCell2.setCellValue(vo.getReportDetailList().get(i).getDescription());
//                contentCell2.setCellStyle(baseContentCellStyle);
//                Cell contentCell3 = currentRow.createCell(3);
//                contentCell3.setCellValue(vo.getReportDetailList().get(i).getDay());
//                contentCell3.setCellStyle(baseCellStyle);
//
//                Cell contentCell4 = currentRow.createCell(4);
//                contentCell4.setCellValue(vo.getReportDetailList().get(i).getTarget());
//                contentCell4.setCellStyle(baseCellStyle);
//                Cell contentCell5 = currentRow.createCell(5);
//                contentCell5.setCellValue(vo.getReportDetailList().get(i).getCurrent());
//
//                CellStyle curStyle = baseCellStyle;
//                if (colorFlag > 0) {
//                    curStyle = isFinish ? baseCellStyle : isReach ? reachStyle : redStyle;
//                }
//                contentCell5.setCellStyle(curStyle);
//
//                Cell contentCell6 = currentRow.createCell(6);
//                contentCell6.setCellValue(vo.getReportDetailList().get(i).getRemark());
//                contentCell6.setCellStyle(baseContentCellStyle);
//            }
//            lastRow += vo.getReportDetailList().size() - 1;
//            colorFlag++;
//
//            if (row.getRowNum() < lastRow) {
//                CellRangeAddress cellAddressWeek = new CellRangeAddress(
//                        row.getRowNum(), lastRow, 0, 0);
//                CellRangeAddress cellAddressName = new CellRangeAddress(
//                        row.getRowNum(), lastRow, 1, 1);
//                sheet.addMergedRegion(cellAddressWeek);
//                sheet.addMergedRegion(cellAddressName);
//            }
//        }
//        autoSizeColumn(sheet, 7);
    }


    private static void autoSizeColumn(Sheet sheet, int length) {
        for (int columnIndex = 0; columnIndex < length; columnIndex++) {
            if (columnIndex > 0) {
                sheet.autoSizeColumn(columnIndex);
            }
        }
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
