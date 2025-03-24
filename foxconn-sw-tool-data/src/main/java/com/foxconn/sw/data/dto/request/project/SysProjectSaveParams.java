package com.foxconn.sw.data.dto.request.project;

import com.foxconn.sw.data.dto.request.state.StateSaveParams;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SysProjectSaveParams {

    private Integer years;

    private String customerName;

    private String fullName;

    private String manufacturingModel;

    private String status;

    private String rfqTime;

    private String customer;

    private String customerPartNo;

    private String application;

    private String driId;

    private String opmId;

    private ProjectMeParams projectMeParams;

    private ProjectNpdParams projectNpdParams;

    private ProjectOtParams projectOtParams;

    private ProjectEeParams projectEeParams;

    private ProjectTeParams projectTeParams;

    private List<StateSaveParams> stateSaveParams;
}
