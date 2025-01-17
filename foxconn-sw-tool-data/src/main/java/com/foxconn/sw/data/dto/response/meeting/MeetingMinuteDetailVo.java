package com.foxconn.sw.data.dto.response.meeting;

import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingMinuteItemVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingMinuteVo;

import java.util.List;

public class MeetingMinuteDetailVo {

    private MeetingMinuteVo minuteVo;
    private List<MeetingMinuteItemVo> decisionVo;
    private List<MeetingMinuteItemVo> otherVo;
    private List<ResourceVo> resourceVos;

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

    public List<ResourceVo> getResourceVos() {
        return resourceVos;
    }

    public void setResourceVos(List<ResourceVo> resourceVos) {
        this.resourceVos = resourceVos;
    }
}
