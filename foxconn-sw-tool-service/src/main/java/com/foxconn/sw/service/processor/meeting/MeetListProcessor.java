package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.data.dto.communal.MeetingDateTimeVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingV2Vo;
import com.foxconn.sw.data.dto.entity.oa.InfoColorVo;
import com.foxconn.sw.data.dto.entity.universal.OptionsVo;
import com.foxconn.sw.data.dto.enums.MeetingStatusEnums;
import com.foxconn.sw.data.dto.request.meeting.ListMeetingV2Params;
import com.foxconn.sw.data.entity.extension.SwMeetingExtension;
import com.foxconn.sw.service.processor.MeetingRoomConfig;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MeetListProcessor {
    @Autowired
    MeetingBusiness meetingBusiness;


    public List<MeetingV2Vo> meetList(ListMeetingV2Params data) {
        if (Objects.isNull(data.getValue())) {
            return Lists.newArrayList();
        }

        List<SwMeetingExtension> meetings = meetingBusiness.selectMeetingList(data);
        return meetings.stream().map(e -> map2MeetingV2Vo(e))
                .collect(Collectors.toList());
    }

    private MeetingV2Vo map2MeetingV2Vo(SwMeetingExtension meeting) {
        MeetingV2Vo vo = new MeetingV2Vo();
        vo.setMeetingID(meeting.getId());
        vo.setRoomVo(getRoomVo(meeting.getRoom()));
        vo.setTitle(meeting.getTitle());
        if (Objects.nonNull(meeting.getCycle()) && StringUtils.isNotEmpty(meeting.getMeetingDate2())) {
            vo.setMeetingDateVo(new MeetingDateTimeVo(meeting.getMeetingDate2(), meeting.getStartTime2(), meeting.getEndTime2()));
        } else {
            vo.setMeetingDateVo(new MeetingDateTimeVo(meeting.getMeetingDate(), meeting.getStartTime(), meeting.getEndTime()));
        }
        vo.setChairman(null);
        vo.setResource(Lists.newArrayList());
        MeetingStatusEnums meetingStatusEnums = MeetingStatusEnums.getEnumByCode(meeting.getStatus());
        vo.setStatusVo(new InfoColorVo(meetingStatusEnums.getCode(), meeting.getDescription()));
        return vo;
    }

    private OptionsVo getRoomVo(String roomKey) {
        return new OptionsVo(roomKey, MeetingRoomConfig.getText(roomKey));
    }



}
