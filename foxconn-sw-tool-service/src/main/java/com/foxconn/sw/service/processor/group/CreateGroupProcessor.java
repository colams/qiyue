package com.foxconn.sw.service.processor.group;

import com.foxconn.sw.business.group.SwCustomGroupBusiness;
import com.foxconn.sw.business.group.SwCustomGroupMemberBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.group.GroupBriefVo;
import com.foxconn.sw.data.dto.entity.group.GroupMemberVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.group.CreateGroupParams;
import com.foxconn.sw.data.dto.request.group.MemberBrief;
import com.foxconn.sw.data.entity.SwCustomGroup;
import com.foxconn.sw.data.entity.SwCustomGroupMember;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

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

    public boolean disband(IntegerParams data) {
        return groupBusiness.disband(data);
    }

    public List<GroupMemberVo> listMember(IntegerParams data) {
        List<SwCustomGroupMember> members = groupMemberBusiness.getGroupMember(data.getParams());
        if (CollectionUtils.isEmpty(members)) {
            return Lists.newArrayList();
        }

        return members.stream().map(e ->
                mapGroupVo(e)
        ).collect(Collectors.toList());
    }

    private GroupMemberVo mapGroupVo(SwCustomGroupMember groupMember) {
        EmployeeVo employeeVo = employeeUtils.mapEmployee(groupMember.getMember());
        GroupMemberVo vo = new GroupMemberVo();
        vo.setName(employeeVo.getName());
        vo.setEmployeeNo(groupMember.getMember());
        vo.setDepartmentId(employeeVo.getDepartmentId());
        vo.setDepartmentName(employeeVo.getDepartmentName());
        if (NumberConstants.ONE.equals(groupMember.getMemberType())) {
            vo.setRole("群主");
        } else if (NumberConstants.TWO.equals(groupMember.getMemberType())) {
            vo.setRole("管理員");
        } else {
            vo.setRole("成員");
        }
        return vo;
    }
}
