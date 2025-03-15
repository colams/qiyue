package com.foxconn.sw.data.dto.request.state;

import lombok.Data;

import java.util.List;

@Data
public class StateAddParams {

    private Integer projectId;

    private List<StateParams> stateParamsList;
}
