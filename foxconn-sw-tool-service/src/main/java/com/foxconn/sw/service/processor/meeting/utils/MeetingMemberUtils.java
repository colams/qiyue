package com.foxconn.sw.service.processor.meeting.utils;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.data.dto.communal.MeetingMemberEnoVo;
import com.foxconn.sw.data.entity.SwMeetingMember;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.foxconn.sw.data.constants.enums.MeetingRoleFlagEnums.*;

public class MeetingMemberUtils {

    public static Map<String, Integer> processMemberRole(MeetingMemberEnoVo eNoVo) {

        Map<String, Integer> employeeRoleMap = new HashMap<>();

        if (Objects.isNull(eNoVo)) {
            return employeeRoleMap;
        }

        if (StringUtils.isEmpty(eNoVo.getChairman())) {
            employeeRoleMap.put(RequestContext.getEmployeeNo(), Chairman_Flag.initFlag());
        } else {
            employeeRoleMap.put(eNoVo.getChairman(), Chairman_Flag.initFlag());
        }
        eNoVo.getMaintainers().forEach(e -> {
            Integer role = employeeRoleMap.getOrDefault(e, 0);
            role = Maintainer_Flag.setFlag(role);
            employeeRoleMap.put(e, role);
        });

        eNoVo.getMembers().forEach(e -> {
            Integer role = employeeRoleMap.getOrDefault(e, 0);
            role = Member_Flag.setFlag(role);
            employeeRoleMap.put(e, role);
        });

        Lists.newArrayList(RequestContext.getEmployeeNo()).forEach(e -> {
            Integer role = employeeRoleMap.getOrDefault(e, 0);
            role = Creator_Flag.setFlag(role);
            employeeRoleMap.put(e, role);
        });

        return employeeRoleMap;
    }

    public static Map<String, Integer> processMemberRole2(MeetingMemberEnoVo eNoVo, List<SwMeetingMember> members) {

        Map<String, Integer> employeeRoleMap = new HashMap<>();

        if (Objects.isNull(eNoVo)) {
            return employeeRoleMap;
        }

        if (!StringUtils.isEmpty(eNoVo.getChairman())) {
            employeeRoleMap.put(eNoVo.getChairman(), Chairman_Flag.initFlag());
        }
        if (!CollectionUtils.isEmpty(eNoVo.getMaintainers())) {
            eNoVo.getMaintainers().forEach(e -> {
                Integer role = employeeRoleMap.getOrDefault(e, 0);
                role = Maintainer_Flag.setFlag(role);
                employeeRoleMap.put(e, role);
            });
        }

        if (!CollectionUtils.isEmpty(eNoVo.getMaintainers())) {
            eNoVo.getMembers().forEach(e -> {
                Integer role = employeeRoleMap.getOrDefault(e, 0);
                role = Member_Flag.setFlag(role);
                employeeRoleMap.put(e, role);
            });
        }

        SwMeetingMember creator = members.stream().filter(e -> Creator_Flag.test(e.getRole())).findFirst().orElse(null);

        Lists.newArrayList(creator.getEmployeeNo()).forEach(e -> {
            Integer role = employeeRoleMap.getOrDefault(e, 0);
            role = Creator_Flag.setFlag(role);
            employeeRoleMap.put(e, role);
        });

        return employeeRoleMap;
    }


}
