package com.foxconn.sw.data.dto.request.forums;

import java.util.List;

public class UpdateAttachParams {

    private Integer id;
    private List<Integer> resourceIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }
}
