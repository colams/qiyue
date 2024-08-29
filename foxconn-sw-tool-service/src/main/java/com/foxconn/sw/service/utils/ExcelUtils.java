package com.foxconn.sw.service.utils;

import com.foxconn.sw.data.dto.entity.acount.AddressBookVo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class ExcelUtils {


    public static Workbook generateExcel(List<AddressBookVo> bookVoList) {

        // 创建Excel工作簿
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("通讯录");

        // 创建标题行
        Row headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue("處級");
        headerRow.createCell(1).setCellValue("部門");
        headerRow.createCell(2).setCellValue("工號");
        headerRow.createCell(3).setCellValue("姓名");
        headerRow.createCell(4).setCellValue("英文名");
        headerRow.createCell(5).setCellValue("性別");
        headerRow.createCell(6).setCellValue("手機號碼");
        headerRow.createCell(7).setCellValue("公司座機");
        headerRow.createCell(8).setCellValue("內部郵箱");
        headerRow.createCell(9).setCellValue("外部郵箱");
        headerRow.createCell(10).setCellValue("收藏");

        // 填充数据
        int rowNum = 1;
        for (AddressBookVo vo : bookVoList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(vo.getSeniorDepart());
            row.createCell(1).setCellValue(vo.getDepartment());
            row.createCell(2).setCellValue(vo.getEmployeeNo());
            row.createCell(3).setCellValue(vo.getName());
            row.createCell(4).setCellValue(vo.getEnName());
            row.createCell(5).setCellValue(vo.getGender());
            row.createCell(6).setCellValue(vo.getPhoneMobile());
            row.createCell(7).setCellValue(vo.getLandLine());
            row.createCell(8).setCellValue(vo.getInnerMail());
            row.createCell(9).setCellValue(vo.getOuterMail());
            row.createCell(10).setCellValue(vo.getStatus() == 1 ? "★" : "☆");
        }

        return workbook;
    }

}
