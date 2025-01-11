package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.business.meeting.SwMeetingMinutesBusiness;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.common.utils.LocalDateExtUtils;
import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.dto.response.meeting.MeetingLineVo;
import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.entity.SwMeetingMinutes;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeetingLineProcessor {

    @Autowired
    SwMeetingMinutesBusiness meetingMinutesBusiness;
    @Autowired
    MeetingBusiness meetingBusiness;
    @Autowired
    SwAppendResourceBusiness appendResourceBusiness;


    public List<MeetingLineVo> meetingLines(Integer meetingId) {
        SwMeeting meeting = meetingBusiness.getMeetingByID(meetingId);
        if (StringUtils.isEmpty(meeting.getCycle())) {
            List<ResourceVo> resourceVos = appendResourceBusiness.getAppendResourcesVo(meeting.getResourceIds());
            MeetingLineVo meetingLineVo = new MeetingLineVo();
            meetingLineVo.setMeetingDate(meeting.getMeetingDate());
            meetingLineVo.setResourceVos(resourceVos);
            return Lists.newArrayList(meetingLineVo);
        }

        List<MeetingLineVo> result = Lists.newArrayList();
        List<SwMeetingMinutes> meetingMinutes = meetingMinutesBusiness.queryMeetingMinuteByMeetingId(meetingId);
        if (!CollectionUtils.isEmpty(meetingMinutes)) {
            result = meetingMinutes.stream().map(e -> {
                return map2MeetingLine(e);
            }).collect(Collectors.toList());
        }
        String startDate = result.stream()
                .max((e, f) -> e.getMeetingDate().compareTo(f.getMeetingDate()))
                .map(e -> e.getMeetingDate())
                .orElse("");
        List<MeetingLineVo> futures = getFutures(meeting, startDate);
        result.addAll(futures);
        return result;
    }

    private List<MeetingLineVo> getFutures(SwMeeting meeting, String startDate) {
        if (StringUtils.isNotEmpty(meeting.getCycleExpire()) && meeting.getCycleExpire().compareTo(startDate) <= 0) {
            return Lists.newArrayList();
        }

        LocalDate start = LocalDateExtUtils.toLocalDate(startDate);
        List<Integer> cycles = JsonUtils.deserialize(meeting.getCycle(), List.class, Integer.class);

        List<MeetingLineVo> meetingLineVos = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            LocalDate date = start.plusDays(i + 1);
            int weekOfDay = date.getDayOfWeek().getValue();
            if (cycles.contains(weekOfDay)) {
                MeetingLineVo meetingLineVo = new MeetingLineVo();
                meetingLineVo.setMeetingId(meeting.getId());
                meetingLineVo.setMeetingDate(LocalDateExtUtils.toString(date));
                meetingLineVos.add(meetingLineVo);
            }
        }
        return meetingLineVos;
    }

    private MeetingLineVo map2MeetingLine(SwMeetingMinutes e) {
        List<ResourceVo> resourceVos = appendResourceBusiness.getAppendResourcesVo(e.getResourceIds());
        MeetingLineVo meetingLineVo = new MeetingLineVo();
        meetingLineVo.setMeetingId(e.getMeetingId());
        meetingLineVo.setMeetingDate(e.getMeetingDate());
        meetingLineVo.setResourceVos(resourceVos);
        return meetingLineVo;
    }


}
