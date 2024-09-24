package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.business.meeting.MeetingCycleDetailBusiness;
import com.foxconn.sw.business.meeting.MeetingMemberBusiness;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.common.utils.StringExtensionUtils;
import com.foxconn.sw.data.constants.enums.retcode.RetCode;
import com.foxconn.sw.data.dto.request.meeting.DeleteParams;
import com.foxconn.sw.data.entity.SwMeeting;
import com.foxconn.sw.data.exception.BizException;
import org.apache.commons.lang3.StringUtils;
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
        if (StringUtils.isEmpty(data.getDeleteDate()) || meeting.getRepeat() == 0) {
            return meetingBusiness.updateMeetingStatus(meeting);
        } else {
            List<Integer> weekOfDays = ConvertUtils.stringToListInt(meeting.getCycle());
            Integer weekOfDay = StringExtensionUtils.toLocalDate(data.getDeleteDate()).getDayOfWeek().getValue() + 1;
            if (!weekOfDays.contains(weekOfDay)){
                throw new BizException(RetCode.VALIDATE_FAILED);
            }
            return meetingCycleDetailBusiness.addCycleCancelDate(data.getDeleteDate(), meeting);
        }
    }

}
