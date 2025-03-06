package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.data.dto.entity.meeting.MeetingVo;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class ExportMeetingProcessor {
    @Autowired
    HttpServletResponse response;

    public Workbook generateExcel(List<MeetingVo>[] vos) {
        // 创建Excel工作簿
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("会议信息");
        int i = 0;
        for (List<MeetingVo> meetings : vos) {
            if (CollectionUtils.isEmpty(meetings)) {
                continue;
            }

            for (MeetingVo meeting : meetings) {
                // 创建标题行
                Row row = sheet.createRow(i++);
                row.createCell(0).setCellValue(String.valueOf(i - 1));
                row.createCell(1).setCellValue(meeting.getMeetingDate());
                row.createCell(2).setCellValue(String.format("%s-%s", meeting.getStartTime(), meeting.getEndTime()));
                row.createCell(3).setCellValue(meeting.getTitle());
                row.createCell(4).setCellValue(meeting.getDescription());
                row.createCell(5).setCellValue(meeting.getRoomName());
            }
        }

        autoSizeColumn(sheet, 6);

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
}