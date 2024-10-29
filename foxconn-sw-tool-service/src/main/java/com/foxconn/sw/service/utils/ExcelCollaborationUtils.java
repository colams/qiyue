package com.foxconn.sw.service.utils;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.collaboration.CollaborationVo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.util.Map;

public class ExcelCollaborationUtils {


    public static Workbook generateExcel(CollaborationVo collaborationVo) {
        // 创建Excel工作簿
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(collaborationVo.getTaskTitle());
        processReportHeaders(sheet, collaborationVo.getHeaders());
        processReport(sheet, collaborationVo);
        return workbook;
    }

    private static void processReportHeaders(Sheet sheet, List<String> headers) {
        Row headerRow = sheet.createRow(0);
        int index = 0;
        for (String header : headers) {
            headerRow.createCell(index++).setCellValue(header);
        }
        headerRow.createCell(index++).setCellValue("編輯人");
    }

    private static void processReport(Sheet sheet, CollaborationVo collaborationVo) {
        int rowIndex = 1;
        for (Map<String, Object> map : collaborationVo.getContent()) {
            Row row = sheet.createRow(rowIndex++);
            int colIndex = 0;
            for (String header : collaborationVo.getHeaders()) {
                row.createCell(colIndex++).setCellValue(map.get(header).toString());
            }
            row.createCell(colIndex++).setCellValue(((EmployeeVo) map.get("handler")).getName());
        }
    }
}
