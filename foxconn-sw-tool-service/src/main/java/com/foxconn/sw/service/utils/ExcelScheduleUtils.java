package com.foxconn.sw.service.utils;

import com.foxconn.sw.data.dto.response.schedule.TeamScheduleListVo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelScheduleUtils {

    public static Workbook generateExcel(List<TeamScheduleListVo> vos) {
        // 创建Excel工作簿
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("行程信息");
        Map<Integer, List<TeamScheduleListVo>> map = vos.stream()
                .collect(Collectors.groupingBy(TeamScheduleListVo::getWeekInfo));
        Map<String, List<TeamScheduleListVo>> map2 = vos.stream()
                .collect(Collectors.groupingBy(TeamScheduleListVo::getDate));
        Map<String, List<TeamScheduleListVo>> map3 = vos.stream()
                .collect(Collectors.groupingBy(TeamScheduleListVo::getName));
        initHeader(sheet, map, map2.keySet().stream().sorted().collect(Collectors.toList()));

        // 填充数据
        int rowNum = 2;
        int number = 1;
        int length = 0;
        for (Map.Entry<String, List<TeamScheduleListVo>> entry : map3.entrySet()) {
            // 创建标题行
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(number++);
            row.createCell(1).setCellValue(entry.getKey());
            int index = 2;
            List<TeamScheduleListVo> list = entry.getValue().stream()
                    .sorted(Comparator.comparing(TeamScheduleListVo::getDate))
                    .collect(Collectors.toList());
            for (TeamScheduleListVo vo : list) {
                row.createCell(index++).setCellValue(vo.getDestination());
            }
            length = index;
        }
        autoSizeColumn(sheet, length);

        // 创建细边框样式
        CellStyle thinBorderStyle = workbook.createCellStyle();
        thinBorderStyle.setBorderTop(BorderStyle.THIN);
        thinBorderStyle.setBorderBottom(BorderStyle.THIN);
        thinBorderStyle.setBorderLeft(BorderStyle.THIN);
        thinBorderStyle.setBorderRight(BorderStyle.THIN);

        for (int rowIndex = 0; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
                Cell cell = row.getCell(colNum);
                cell.setCellStyle(thinBorderStyle);
            }
        }
        return workbook;
    }

    private static void autoSizeColumn(Sheet sheet, int length) {
        for (int columnIndex = 0; columnIndex < length; columnIndex++) {
            if (columnIndex > 0) {
                sheet.setColumnWidth(columnIndex, sheet.getColumnWidth(columnIndex) * 14 / 10);
            }
        }
    }

    private static void initHeader(Sheet sheet, Map<Integer, List<TeamScheduleListVo>> map, List<String> dateSet) {
        // 创建标题行
        Row headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue("成員");
        int i = 1;
        for (Integer weekInfo : map.keySet()) {
            headerRow.createCell(i++).setCellValue(String.format("第%s周", weekInfo));
        }

        Row headerRow2 = sheet.createRow(1);
        headerRow2.createCell(0).setCellValue("NO.");
        headerRow2.createCell(1).setCellValue("姓名");
        int j = 2;
        for (String date : dateSet) {
            headerRow2.createCell(j++).setCellValue(date);
        }
    }
}
