package com.foxconn.sw.data.dto.request.project.mp;

import lombok.Data;

@Data
public class HandoverSearchParams {
    private Integer projectId;

    private Integer stateId;
}
