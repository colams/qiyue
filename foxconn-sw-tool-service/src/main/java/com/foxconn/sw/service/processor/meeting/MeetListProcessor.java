package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.business.meeting.MeetingMemberBusiness;
import com.foxconn.sw.common.utils.IntegerExtUtils;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.common.utils.LocalDateExtUtils;
import com.foxconn.sw.data.dto.communal.MeetingDateTimeVo;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingV2Vo;
import com.foxconn.sw.data.dto.entity.oa.InfoColorVo;
import com.foxconn.sw.data.dto.entity.universal.OptionsVo;
import com.foxconn.sw.data.dto.request.meeting.ListMeetingV2Params;
import com.foxconn.sw.data.dto.response.meeting.MeetListVo;
import com.foxconn.sw.data.entity.SwMeetingMember;
import com.foxconn.sw.data.entity.extension.MeetingEntity;
import com.foxconn.sw.service.processor.MeetingRoomConfig;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.foxconn.sw.data.constants.enums.MeetingRoleFlagEnums.Chairman_Flag;

@Component
public class MeetListProcessor {
    @Autowired
    MeetingBusiness meetingBusiness;
    @Autowired
    MeetingMemberBusiness memberBusiness;
    @Autowired
    EmployeeUtils employeeUtils;

    public MeetListVo meetList(ListMeetingV2Params data) {
        MeetListVo vo = new MeetListVo();

        if (Objects.isNull(data.getValue())) {
            vo.setMeetingList(Lists.newArrayList());
            vo.setChairmanFilter(Lists.newArrayList());
            return vo;
        }

        String searchStartDate = getCurrentMonday(data.getSearchStartDate());
        String searchEndDate = getEndSunday(data.getSearchEndDate());

        List<MeetingEntity> meetings = meetingBusiness.selectMeetingList(data, searchStartDate, searchEndDate);

        List<MeetingV2Vo> vos = new ArrayList<>();
        meetings.forEach(e -> {
            List<Integer> cycles = JsonUtils.deserialize(e.getCycle(), Lists.class, Integer.class);
            List<MeetingV2Vo> list;
            if (CollectionUtils.isEmpty(cycles)) {
                list = map2MeetingV2Vo(e);
            } else {
                list = map2MeetingV2Vo(e, cycles, searchStartDate, searchEndDate);
            }

            if (!CollectionUtils.isEmpty(list)) {
                vos.addAll(list);
            }
        });
        vo.setMeetingList(vos);
        vo.setChairmanFilter(Lists.newArrayList());
        return vo;
    }

    private List<MeetingV2Vo> map2MeetingV2Vo(MeetingEntity meetingEntity,
                                              List<Integer> cycles,
                                              String searchStartDate,
                                              String searchEndDate) {
        List<MeetingV2Vo> vos = new ArrayList<>();

        LocalDate startDate = LocalDateExtUtils.toLocalDate(searchStartDate);
        LocalDate endDate = LocalDateExtUtils.toLocalDate(searchEndDate);

        while (endDate.isAfter(startDate)) {
            int week = startDate.getDayOfWeek().getValue();
            if (cycles.contains(week)) {
                MeetingV2Vo vo = map2MeetingV2Vo(meetingEntity, startDate);
                vos.add(vo);
            }
        }

        return vos;
    }

    private List<MeetingV2Vo> map2MeetingV2Vo(MeetingEntity meetingEntity) {
        LocalDate startDate = LocalDateExtUtils.toLocalDate(meetingEntity.getMeetingDate());
        MeetingV2Vo vo = map2MeetingV2Vo(meetingEntity, startDate);
        return Lists.newArrayList(vo);
    }

    private MeetingV2Vo map2MeetingV2Vo(MeetingEntity meetingEntity, LocalDate startDate) {
        MeetingDateTimeVo meetingDateTimeVo = new MeetingDateTimeVo();
        meetingDateTimeVo.setWeekInfo(startDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault()));
        meetingDateTimeVo.setMeetingDate(LocalDateExtUtils.toString(startDate));
        meetingDateTimeVo.setStartTime(meetingDateTimeVo.getStartTime());
        meetingDateTimeVo.setEndTime(meetingDateTimeVo.getEndTime());


        List<SwMeetingMember> members = memberBusiness.queryMeetingMember(meetingEntity.getMeetingId());
        List<SwMeetingMember> memberList = members.stream()
                .filter(e -> Chairman_Flag.test(e.getRole()))
                .collect(Collectors.toList());
        EmployeeVo employeeVo;
        String employeeNo = null;
        if (IntegerExtUtils.isPk(meetingEntity.getMeetingDetailId())) {
            employeeNo = memberList.stream()
                    .filter(e -> meetingEntity.getMeetingDetailId().equals(e.getMeetingDetailId()))
                    .findFirst()
                    .map(e -> e.getEmployeeNo())
                    .orElse("");
        }

        if (StringUtils.isEmpty(employeeNo)) {
            employeeNo = memberList.stream()
                    .filter(e -> !IntegerExtUtils.isPk(e.getMeetingDetailId()))
                    .findFirst()
                    .map(e -> e.getEmployeeNo())
                    .orElse("");
        }
        employeeVo = employeeUtils.mapEmployee(employeeNo);

        MeetingV2Vo vo = new MeetingV2Vo();
        vo.setMeetingID(meetingEntity.getMeetingId());
        vo.setRoomVo(getRoomVo(meetingEntity.getRoom()));
        vo.setTitle(meetingEntity.getTitle());
        vo.setMeetingDateVo(meetingDateTimeVo);
        vo.setChairman(employeeVo);
        vo.setResource(Lists.newArrayList());
        vo.setStatusVo(new InfoColorVo(0, "待開始"));
        return vo;
    }

    private OptionsVo getRoomVo(String roomKey) {
        return new OptionsVo(roomKey, MeetingRoomConfig.getText(roomKey));
    }

    private String getEndSunday(String searchEndDate) {
        LocalDate t;
        if (StringUtils.isEmpty(searchEndDate)) {
            t = LocalDate.now().plusWeeks(2);
        } else {
            t = LocalDateExtUtils.toLocalDate(searchEndDate);
        }

        DayOfWeek currentDayOfWeek = t.getDayOfWeek();
        // 计算距离周一的天数差
        int daysUntilMonday = DayOfWeek.SUNDAY.getValue() - currentDayOfWeek.getValue();
        // 获取当前周的周日
        LocalDate monday = t.plusDays(daysUntilMonday);
        return LocalDateExtUtils.toString(monday);
    }

    private String getCurrentMonday(String searchStartDate) {
        LocalDate t;
        if (StringUtils.isEmpty(searchStartDate)) {
            t = LocalDate.now();
        } else {
            t = LocalDateExtUtils.toLocalDate(searchStartDate);
        }
        DayOfWeek currentDayOfWeek = t.getDayOfWeek();
        // 计算距离周一的天数差
        int daysUntilMonday = currentDayOfWeek.getValue() - DayOfWeek.MONDAY.getValue();
        if (daysUntilMonday < 0) {
            daysUntilMonday += 7;
        }
        // 获取当前周的周一
        LocalDate monday = t.minusDays(daysUntilMonday);
        return LocalDateExtUtils.toString(monday);
    }


}
