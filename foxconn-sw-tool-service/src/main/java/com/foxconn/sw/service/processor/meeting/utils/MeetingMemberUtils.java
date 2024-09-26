package com.foxconn.sw.service.processor.meeting.utils;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.data.dto.communal.MeetingMemberEnoVo;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
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

        return employeeRoleMap;
    }

}
