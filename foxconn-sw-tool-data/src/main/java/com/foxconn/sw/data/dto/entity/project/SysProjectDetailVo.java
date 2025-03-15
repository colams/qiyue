package com.foxconn.sw.data.dto.entity.project;

import com.foxconn.sw.data.dto.request.project.*;
import lombok.Data;

@Data
public class SysProjectDetailVo {
    private Integer id;

    private Integer years;

    private String projectCode;

    private String customerName;

    private String fullName;

    private String manufacturingModel;

    private String status;

    private String rfqTime;

    private String customer;

    private String customerPartNo;

    private String application;

    private String priName;

    private String uriName;

    private ProjectMeParams projectMeParams;

    private ProjectNpdParams projectNpdParams;

    private ProjectOtParams projectOtParams;

    private ProjectEeParams projectEeParams;

    private ProjectTeParams projectTeParams;
}
