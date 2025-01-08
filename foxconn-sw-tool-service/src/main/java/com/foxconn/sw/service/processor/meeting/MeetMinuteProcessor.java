package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.meeting.SwMeetingMinutesBusiness;
import com.foxconn.sw.business.meeting.SwMeetingMinutesDetailBusiness;
import com.foxconn.sw.data.dto.entity.meeting.MeetingMinuteItemVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingMinuteVo;
import com.foxconn.sw.data.dto.enums.MeetingItemTypeEnums;
import com.foxconn.sw.data.dto.request.meeting.MeetingMinuteParams;
import com.foxconn.sw.data.entity.SwMeetingMinutes;
import com.foxconn.sw.data.entity.SwMeetingMinutesDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeetMinuteProcessor {
    @Autowired
    SwMeetingMinutesBusiness meetingMinutesBusiness;
    @Autowired
    SwMeetingMinutesDetailBusiness meetingMinutesDetailBusiness;

    public Boolean meetMinute(MeetingMinuteParams minuteParams) {

        SwMeetingMinutes meetingMinutes = toMeetingMinutes(minuteParams.getMinuteVo());
        List<SwMeetingMinutesDetail> detailList = new ArrayList<>();
        toMinutesDetail(minuteParams.getMinuteVo(),
                minuteParams.getDecisionVo(),
                MeetingItemTypeEnums.Decision.getCode(),
                detailList);
        toMinutesDetail(minuteParams.getMinuteVo(),
                minuteParams.getDecisionVo(),
                MeetingItemTypeEnums.Other.getCode(),
                detailList);

        Long meetingMinutesId = meetingMinutesBusiness.insertOrUpdate(meetingMinutes);

        meetingMinutesDetailBusiness.batchInsert(detailList, meetingMinutesId);
        return true;
    }


    private void toMinutesDetail(MeetingMinuteVo vo,
                                 List<MeetingMinuteItemVo> itemVos,
                                 String itemType,
                                 List<SwMeetingMinutesDetail> otherVo) {
        if (CollectionUtils.isEmpty(itemVos)) {
            return;
        }

        List<SwMeetingMinutesDetail> result = itemVos.stream().map(e -> {
            SwMeetingMinutesDetail detail = new SwMeetingMinutesDetail();
            detail.setId(e.getId());
            detail.setItemType(itemType);
            detail.setIndexNo(e.getIndex());
            detail.setItem(e.getItemTitle());
            detail.setDirectEno(e.getDirector());
            detail.setDueDate(e.getDueDate());
            detail.setStatus(e.getStatus());
            detail.setRemark(e.getRemark());
            return detail;
        }).collect(Collectors.toList());
        otherVo.addAll(result);
    }

    private SwMeetingMinutes toMeetingMinutes(MeetingMinuteVo vo) {
        SwMeetingMinutes meetingMinutes = new SwMeetingMinutes();
        meetingMinutes.setId(vo.getId());
        meetingMinutes.setMeetingId(vo.getMeetingID());
        meetingMinutes.setRoom(vo.getMeetingRoomKey());
        meetingMinutes.setTitle(vo.getMeetingTitle());
        meetingMinutes.setMeetingDate(vo.getDateTimeVo().getMeetingDate());
        meetingMinutes.setStartTime(vo.getDateTimeVo().getStartTime());
        meetingMinutes.setEndTime(vo.getDateTimeVo().getEndTime());
        return meetingMinutes;

    }
}
