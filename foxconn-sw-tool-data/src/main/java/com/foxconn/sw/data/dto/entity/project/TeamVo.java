package com.foxconn.sw.data.dto.entity.project;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TeamVo {
    private int id;
    private String adminDivision;
    private String dept;
    private String jobTitle;
    private String empNo;
    private String nameCn;
    private String nameEn;
    private String email;
    private String tel;
    private String processor;
    private String station;
    private String remark;
}
