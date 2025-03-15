package com.foxconn.sw.data.dto.request.state;

import lombok.Data;

import java.util.List;

@Data
public class StateListParams {
    private Integer projectId;

    private List<StateInfoParams> stateInfoParamsList;
}
