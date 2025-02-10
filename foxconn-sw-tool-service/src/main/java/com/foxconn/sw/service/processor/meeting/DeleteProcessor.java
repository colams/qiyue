package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.business.meeting.MeetingCycleDetailBusiness;
import com.foxconn.sw.business.meeting.MeetingMemberBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.common.utils.LocalDateExtUtils;
import com.foxconn.sw.data.constants.enums.retcode.RetCode;
import com.foxconn.sw.data.dto.request.meeting.DeleteParams;
import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class DeleteProcessor {

    @Autowired
    MeetingBusiness meetingBusiness;
    @Autowired
    MeetingCycleDetailBusiness meetingCycleDetailBusiness;
    @Autowired
    MeetingMemberBusiness meetingMemberBusiness;

    public boolean delete(DeleteParams data) {
        SwMeeting meeting = meetingBusiness.getMeetingByID(data.getMeetingID());

        if (Objects.isNull(meeting)) {
            throw new BizException(RetCode.VALIDATE_FAILED);
        }

        return processByRepeat(data, meeting);
    }

    private boolean processByRepeat(DeleteParams data, SwMeeting meeting) {
        if (data.getOperateType().equals(NumberConstants.TWO)) {
            return meetingBusiness.updateMeetingStatus(meeting);
        } else {
            if (meeting.getMeetingDate().equalsIgnoreCase(data.getDeleteDate())) {
                return meetingCycleDetailBusiness.addCycleCancelDate(data.getDeleteDate(), meeting);
            }
            List<Integer> weekOfDays = JsonUtils.deserialize(meeting.getCycle(), List.class, Integer.class);
            Integer weekOfDay = LocalDateExtUtils.toLocalDate(data.getDeleteDate()).getDayOfWeek().getValue();
            if (!weekOfDays.contains(weekOfDay)) {
                throw new BizException(RetCode.VALIDATE_FAILED);
            }
            return meetingCycleDetailBusiness.addCycleCancelDate(data.getDeleteDate(), meeting);
        }
    }

}
