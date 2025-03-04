package com.foxconn.sw.service.processor.meeting;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.business.meeting.MeetingBusiness;
import com.foxconn.sw.business.meeting.MeetingCycleDetailBusiness;
import com.foxconn.sw.business.meeting.MeetingMemberBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.common.utils.LocalDateExtUtils;
import com.foxconn.sw.data.dto.communal.MeetingDateTimeVo;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.meeting.MeetingV2Vo;
import com.foxconn.sw.data.dto.entity.oa.InfoColorVo;
import com.foxconn.sw.data.dto.entity.universal.OptionsVo;
import com.foxconn.sw.data.dto.request.meeting.ListMeetingV2Params;
import com.foxconn.sw.data.dto.response.meeting.MeetListVo;
import com.foxconn.sw.data.entity.SwMeetingCycleDetail;
import com.foxconn.sw.data.entity.SwMeetingMember;
import com.foxconn.sw.data.entity.extension.MeetingEntity;
import com.foxconn.sw.service.processor.MeetingRoomConfig;
import com.foxconn.sw.service.processor.resource.GetAppendResourcesProcessor;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.foxconn.sw.data.constants.enums.MeetingRoleFlagEnums.Chairman_Flag;

@Component
public class MeetListProcessor {
    @Autowired
    MeetingBusiness meetingBusiness;
    @Autowired
    MeetingMemberBusiness memberBusiness;
    @Autowired
    UserBusiness employeeUtils;
    @Autowired
    MeetingCycleDetailBusiness meetingCycleDetailBusiness;
    @Autowired
    SwAppendResourceBusiness appendResourceBusiness;
    @Autowired
    GetAppendResourcesProcessor getAppendResourcesProcessor;


    public MeetListVo meetList(ListMeetingV2Params data) {
        String searchStartDate = getCurrentMonday(data.getSearchStartDate());
        String searchEndDate = getEndSunday(data.getSearchEndDate());

        List<MeetingEntity> meetings = meetingBusiness.selectMeetingList(data, searchStartDate, searchEndDate);

        List<MeetingV2Vo> vos = new ArrayList<>();
        meetings.forEach(e -> {
            List<MeetingV2Vo> list;
            if (StringUtils.isEmpty(e.getCycle())) {
                list = map2MeetingV2Vo(e);
            } else {
                List<Integer> cycles = JsonUtils.deserialize(e.getCycle(), List.class, Integer.class);
                String startDate = searchStartDate.compareTo(e.getCycleStart()) >= 0 ? searchStartDate : e.getCycleStart();
                String endDate = searchEndDate.compareTo(e.getCycleExpire()) >= 0 && StringUtils.isNotEmpty(e.getCycleExpire()) ? e.getCycleExpire() : searchEndDate;
                list = map2MeetingV2Vo(e, cycles, startDate, endDate);
            }

            if (!CollectionUtils.isEmpty(list)) {
                vos.addAll(list);
            }
        });


        Collections.sort(vos, (a, b) -> {
            return a.getMeetingDateVo().getMeetingDate().compareTo(b.getMeetingDateVo().getMeetingDate());
        });

        MeetListVo vo = new MeetListVo();
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

        while (endDate.compareTo(startDate) >= 0) {
            int week = startDate.getDayOfWeek().getValue();
            if (cycles.contains(week)) {
                MeetingV2Vo vo = map2MeetingV2Vo(meetingEntity, startDate);
                vos.add(vo);
            }
            startDate = startDate.plusDays(1);
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
        meetingDateTimeVo.setWeekInfo(startDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault()));
        meetingDateTimeVo.setMeetingDate(LocalDateExtUtils.toString(startDate));
        meetingDateTimeVo.setStartTime(meetingEntity.getStartTime());
        meetingDateTimeVo.setEndTime(meetingEntity.getEndTime());


        List<SwMeetingMember> members = memberBusiness.queryMeetingMemberByMeetingID(meetingEntity.getMeetingId());
        List<SwMeetingMember> memberList = members.stream()
                .filter(e -> Chairman_Flag.test(e.getRole()))
                .collect(Collectors.toList());

        EmployeeVo employeeVo = null;
        String employeeNo = memberList.stream()
                .filter(e -> e.getMeetingDetailId().equals(NumberConstants.ZERO))
                .findFirst()
                .map(e -> e.getEmployeeNo())
                .orElse("");
        if (StringUtils.isNotEmpty(meetingEntity.getCycle())) {
            List<SwMeetingCycleDetail> details = meetingCycleDetailBusiness
                    .queryCycleDetailWithDate(meetingEntity.getMeetingId(), meetingDateTimeVo.getMeetingDate());
            if (CollectionUtils.isEmpty(details)) {
                employeeVo = employeeUtils.mapEmployee(employeeNo);
            } else {
                employeeNo = memberList.stream()
                        .filter(e -> e.getMeetingDetailId().equals(details.get(0).getId()))
                        .findFirst()
                        .map(e -> e.getEmployeeNo())
                        .orElse(employeeNo);
                employeeVo = employeeUtils.mapEmployee(employeeNo);
            }
        }

        MeetingV2Vo vo = new MeetingV2Vo();
        vo.setMeetingID(meetingEntity.getMeetingId());
        vo.setRoomVo(getRoomVo(meetingEntity.getRoom()));
        vo.setTitle(meetingEntity.getTitle());
        vo.setMeetingDateVo(meetingDateTimeVo);
        vo.setChairman(employeeVo);
        vo.setResource(getAppendResourcesProcessor.getAppendResourcesVo(meetingEntity.getResources()));
        vo.setStatusVo(mapColorVo(meetingDateTimeVo));
        return vo;
    }

    private InfoColorVo mapColorVo(MeetingDateTimeVo meetingDateTimeVo) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String currentDate = DateTimeUtils.formatYMD(localDateTime);
        String nowTime = DateTimeUtils.formatHm(localDateTime);
        if (meetingDateTimeVo.getMeetingDate().compareTo(currentDate) > 0
                || (meetingDateTimeVo.getMeetingDate().equalsIgnoreCase(currentDate)
                && meetingDateTimeVo.getStartTime().compareTo(nowTime) > 0)) {
            return new InfoColorVo(2, "待開始");
        } else if (meetingDateTimeVo.getMeetingDate().equalsIgnoreCase(currentDate)
                && meetingDateTimeVo.getStartTime().compareTo(nowTime) <= 0
                && meetingDateTimeVo.getEndTime().compareTo(nowTime) > 0) {
            return new InfoColorVo(1, "进行中");
        } else {
            return new InfoColorVo(3, "已过期");
        }
    }

    private OptionsVo getRoomVo(String roomKey) {
        return new OptionsVo(roomKey, MeetingRoomConfig.getText(roomKey));
    }

    private String getEndSunday(String searchEndDate) {
        if (StringUtils.isNotEmpty(searchEndDate)) {
            return searchEndDate;
        }

        LocalDate t = LocalDate.now().plusWeeks(1);

        DayOfWeek currentDayOfWeek = t.getDayOfWeek();
        // 计算距离周一的天数差
        int daysUntilMonday = DayOfWeek.SUNDAY.getValue() - currentDayOfWeek.getValue();
        // 获取当前周的周日
        LocalDate monday = t.plusDays(daysUntilMonday);
        return LocalDateExtUtils.toString(monday);
    }

    private String getCurrentMonday(String searchStartDate) {
        if (StringUtils.isNotEmpty(searchStartDate)) {
            return searchStartDate;
        }

        LocalDate t = LocalDate.now();
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
