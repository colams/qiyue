package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.common.utils.ExcelUtils;
import com.foxconn.sw.data.dto.entity.acount.LoginStateVo;
import com.foxconn.sw.data.dto.entity.acount.UserBriefParams;
import com.foxconn.sw.data.entity.SwDepartment;
import com.foxconn.sw.service.BaseTest;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class RegisterProcessorTest extends BaseTest {

    @Autowired
    RegisterProcessor registerProcessor;
    @Autowired
    DepartmentBusiness departmentBusiness;

    @Test
    public void registerTest() {
        String excelFilePath = "C:/Users/G1658973/Desktop/檢測/CMA帳號需求.xlsx";
        Workbook workbook = null;

        try (InputStream in = new FileInputStream(excelFilePath)) {
            workbook = new XSSFWorkbook(in);
            Sheet sheet = workbook.getSheet("工作表1");
            for (Row row : sheet) {
                if (ExcelUtils.getCellValueAsString(row.getCell(0)).equalsIgnoreCase("管理職")) {
                    continue;
                }

                if (!ExcelUtils.getCellValueAsString(row.getCell(7)).equalsIgnoreCase("unknown")) {
                    continue;
                }

                UserBriefParams vo = row2Vo(row);

                String result = register(vo);

                Cell eno = row.getCell(7);
                eno.setCellValue(result);
            }
        } catch (IOException e) {
            System.out.println("allMembers---------" + e);
        }

        if (Objects.nonNull(workbook)) {
            try (FileOutputStream fo = new FileOutputStream(excelFilePath)) {
                workbook.write(fo);
                fo.close();
                workbook.close();
            } catch (IOException e) {
                System.out.println("saveExcel---------" + e);
            }
        }
    }


    public String register(UserBriefParams params) {
        if (params.getDepartmentId() <= 0) {
            return "unknown";
        }

        try {
            LoginStateVo vo = registerProcessor.register(params);
            System.out.println(String.format("-----------------%s,%s", vo.getUser().getEmployeeNo(), vo.getToken()));
            return "success";
        } catch (Exception e) {
            System.out.println(String.format("*****************%s,%s", params.getEmployeeNo(), e.toString()));
        }
        return "error";
    }

    public UserBriefParams row2Vo(Row row) {
        UserBriefParams vo = new UserBriefParams();
        vo.setName(ExcelUtils.getCellValueAsString(row.getCell(2)));
        vo.setEmployeeNo(ExcelUtils.getCellValueAsString(row.getCell(1)));
        vo.setPassword(ExcelUtils.getCellValueAsString(row.getCell(1)));

        String chuJiname = ExcelUtils.getCellValueAsString(row.getCell(3));
        String buJiname = ExcelUtils.getCellValueAsString(row.getCell(4));
        String keJiname = ExcelUtils.getCellValueAsString(row.getCell(5));

        String levelString = ExcelUtils.getCellValueAsString(row.getCell(0));
        String depart = String.format("%s-%s-%s", chuJiname, buJiname, keJiname);

        vo.setDepart(depart);
        if (StringUtils.isEmpty(chuJiname) && StringUtils.isEmpty(buJiname) && StringUtils.isEmpty(keJiname)) {
            vo.setDepartmentId(1);
            vo.setManagerLevel(0);
        } else if (StringUtils.isEmpty(buJiname) && StringUtils.isEmpty(keJiname)) {
            vo.setDepartmentId(getDepartID(chuJiname));
            vo.setManagerLevel("處級".equalsIgnoreCase(levelString) ? 2 : 0);
        } else if (StringUtils.isEmpty(keJiname)) {
            vo.setDepartmentId(getDepartID(buJiname));
            vo.setManagerLevel("部級".equalsIgnoreCase(levelString) ? 3 : 0);
        } else {
            vo.setDepartmentId(getDepartID(keJiname));
            vo.setManagerLevel("課級".equalsIgnoreCase(levelString) ? 4 : 0);
        }
        return vo;
    }

    private Integer getDepartID(String departmentName) {
        List<SwDepartment> departmentList = departmentBusiness.getDepartment();
        String newer = departmentName.replace("(", "（").replace(")", "）");
        SwDepartment department = departmentList.stream().filter(e -> e.getName().indexOf(departmentName) >= 0 ||
                e.getName().indexOf(newer) >= 0).findFirst().orElse(null);
        if (Objects.isNull(department)) {
            return 0;
        }
        return department.getId();
    }


}