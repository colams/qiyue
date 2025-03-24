package com.foxconn.sw.data.dto.request.project;

import lombok.Data;

@Data
public class SysProjectUpdateParams {

    private Integer projectId;

    private ProjectUpdateMeParams projectMeParams;

    private ProjectUpdateNpdParams projectNpdParams;

    private ProjectUpdateOtParams projectOtParams;

    private ProjectUpdateEeParams projectEeParams;

    private ProjectUpdateTeParams projectTeParams;

}
