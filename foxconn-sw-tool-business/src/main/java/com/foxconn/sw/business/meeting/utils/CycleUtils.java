package com.foxconn.sw.business.meeting.utils;

import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.common.utils.StringExtUtils;
import com.foxconn.sw.data.dto.communal.CycleMeetingVo;
import com.foxconn.sw.data.entity.SwMeeting;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.Objects;

public class CycleUtils {

    public static void processCycle(SwMeeting meeting, CycleMeetingVo cycleMeetingVo) {
        if (Objects.nonNull(cycleMeetingVo) && Objects.nonNull(cycleMeetingVo.getCycle())) {
            meeting.setCycle(JsonUtils.serialize(cycleMeetingVo.getCycle()));
            meeting.setCycleExpire(cycleMeetingVo.getCycleExpire());
            if (StringUtils.isEmpty(cycleMeetingVo.getCycleStart())) {
                if (StringUtils.isEmpty(meeting.getMeetingDate())) {
                    meeting.setCycleStart(StringExtUtils.toString(LocalDate.now()));
                } else {
                    meeting.setCycleStart(meeting.getMeetingDate());
                }
            } else {
                meeting.setCycleStart(cycleMeetingVo.getCycleStart());
            }
            meeting.setIsRepeat(1);
        }
    }

}
