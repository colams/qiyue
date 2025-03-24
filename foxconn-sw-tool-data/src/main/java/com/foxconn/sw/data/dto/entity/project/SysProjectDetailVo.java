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

    private String driId;

    private String uriName;

    private ProjectUpdateMeParams projectMeParams;

    private ProjectUpdateEeParams projectEeParams;

    private ProjectUpdateOtParams projectOtParams;

    private ProjectUpdateNpdParams projectNpdParams;

    private ProjectUpdateTeParams projectTeParams;
}
