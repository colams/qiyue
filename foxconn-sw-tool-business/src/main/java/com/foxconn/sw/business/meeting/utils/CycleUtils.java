package com.foxconn.sw.business.meeting.utils;

import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.communal.CycleMeetingVo;
import com.foxconn.sw.data.entity.SwMeeting;

import java.util.Objects;

public class CycleUtils {

    public static void processCycle(SwMeeting meeting, CycleMeetingVo cycleMeetingVo) {
        if (Objects.nonNull(cycleMeetingVo) && Objects.nonNull(cycleMeetingVo.getCycle())) {
            meeting.setCycle(JsonUtils.serialize(cycleMeetingVo.getCycle()));
            meeting.setCycleExpire(cycleMeetingVo.getCycleExpire());
            meeting.setCycleStart(cycleMeetingVo.getCycleStart());
            meeting.setIsRepeat(1);
        }
    }

}
