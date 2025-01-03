package com.foxconn.sw.data.dto.response.meeting;

import com.foxconn.sw.data.dto.entity.meeting.MeetingMinuteItemVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingMinuteVo;

import java.util.List;

public class MeetingMinuteDetailVo {

    private MeetingMinuteVo minuteVo;
    private List<MeetingMinuteItemVo> decisionVo;
    private List<MeetingMinuteItemVo> otherVo;

    public MeetingMinuteVo getMinuteVo() {
        return minuteVo;
    }

    public void setMinuteVo(MeetingMinuteVo minuteVo) {
        this.minuteVo = minuteVo;
    }

    public List<MeetingMinuteItemVo> getDecisionVo() {
        return decisionVo;
    }

    public void setDecisionVo(List<MeetingMinuteItemVo> decisionVo) {
        this.decisionVo = decisionVo;
    }

    public List<MeetingMinuteItemVo> getOtherVo() {
        return otherVo;
    }

    public void setOtherVo(List<MeetingMinuteItemVo> otherVo) {
        this.otherVo = otherVo;
    }
}
