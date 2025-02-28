package com.foxconn.sw.data.dto.request.meeting;

import java.util.List;

public class UpdateMeetingAttachParams extends MeetingAttachmentParams {

    private List<Integer> resourceIds;

    public List<Integer> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }
}
