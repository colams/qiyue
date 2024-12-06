package com.foxconn.sw.service.processor.group;

import com.foxconn.sw.business.group.SwCustomGroupBusiness;
import com.foxconn.sw.business.group.SwCustomGroupMemberBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.group.GroupBriefVo;
import com.foxconn.sw.data.dto.request.group.CreateGroupParams;
import com.foxconn.sw.data.dto.request.group.MemberBrief;
import com.foxconn.sw.data.entity.SwCustomGroup;
import com.foxconn.sw.data.entity.SwCustomGroupMember;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CreateGroupProcessor {

    @Autowired
    SwCustomGroupBusiness groupBusiness;
    @Autowired
    SwCustomGroupMemberBusiness groupMemberBusiness;
    @Autowired
    EmployeeUtils employeeUtils;


    public GroupBriefVo createGroup(CreateGroupParams groupParams) {
        SwCustomGroup group = groupBusiness.createGroup(groupParams.getName(),
                RequestContext.getEmployeeNo(),
                groupParams.getIsPrivate(),
                groupParams.getDescription());

        if (Objects.nonNull(group)) {
            createGroupMember(group.getId(), groupParams.getMembers());
        }
        EmployeeVo owner = employeeUtils.mapEmployee(RequestContext.getEmployeeNo());
        return CommonUtils.map(group, owner);
    }

    public boolean createGroupMember(Integer groupId, List<MemberBrief> memberBriefs) {
        List<SwCustomGroupMember> groupMembers = new ArrayList<>();
        Set<String> set = new HashSet<>();
        memberBriefs.forEach(e -> {
            if (set.add(e.getEmployeeNo())) {
                SwCustomGroupMember member = new SwCustomGroupMember();
                member.setCustomGroupId(groupId);
                member.setMember(e.getEmployeeNo());
                member.setMemberType(e.getMemberType());
                groupMembers.add(member);
            }
        });
        return groupMemberBusiness.batchInsert(groupMembers);
    }

}
