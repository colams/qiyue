package com.foxconn.sw.data.dto.request.project.team;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ImportPjTeamParams {

    @ExcelProperty("Admin Division*")
    private String adminDivision;

    @ExcelProperty("Dept*")
    private String dept;

    @ExcelProperty("Job Title*")
    private String jobTitle;

    @ExcelProperty("Emp No*")
    private String empNo;

    @ExcelProperty("Name(CN)*")
    private String nameCn;

    @ExcelProperty("Name(EN)*")
    private String nameEn;

    @ExcelProperty("Email*")
    private String email;

    @ExcelProperty("Tel")
    private String tel;

    @ExcelProperty("Process")
    private String processor;

    @ExcelProperty("Station")
    private String station;

    @ExcelProperty("Remark")
    private String remark;
}
