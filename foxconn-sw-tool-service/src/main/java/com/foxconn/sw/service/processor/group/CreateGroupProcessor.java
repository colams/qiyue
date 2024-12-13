package com.foxconn.sw.service.processor.group;

import com.foxconn.sw.business.group.SwCustomGroupBusiness;
import com.foxconn.sw.business.group.SwCustomGroupMemberBusiness;
import com.foxconn.sw.business.group.SwCustomGroupOperateBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.group.GroupBriefVo;
import com.foxconn.sw.data.dto.entity.group.GroupMemberVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.group.CreateGroupParams;
import com.foxconn.sw.data.dto.request.group.MemberBrief;
import com.foxconn.sw.data.dto.request.group.UpdateGroupParams;
import com.foxconn.sw.data.entity.SwCustomGroup;
import com.foxconn.sw.data.entity.SwCustomGroupMember;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CreateGroupProcessor {

    @Autowired
    SwCustomGroupBusiness groupBusiness;
    @Autowired
    SwCustomGroupMemberBusiness groupMemberBusiness;
    @Autowired
    SwCustomGroupOperateBusiness customGroupOperateBusiness;
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
        SwCustomGroup group = groupBusiness.getCustomGroup(data.getParams());
        boolean result = groupBusiness.disband(group);
        if (result) {
            customGroupOperateBusiness.disbandGroup(data, "解散群组 " + group.getName());
        }
        return result;
    }

    public List<GroupMemberVo> listMember(IntegerParams data) {
        List<SwCustomGroupMember> members = groupMemberBusiness.getCustomGroupMember(data.getParams());
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

    public Boolean updateGroup(UpdateGroupParams data) {
        SwCustomGroup customGroup = groupBusiness.getCustomGroup(data.getGroupID());
        if (Objects.isNull(customGroup)) {
            return false;
        }
        boolean result = groupBusiness.updateGroup(data);

        List<SwCustomGroupMember> groupMembers = groupMemberBusiness.getCustomGroupMember(data.getGroupID());

        if (!CollectionUtils.isEmpty(data.getMembers())) {

            List<String> allEmpNos = Stream.of(data.getMembers(), groupMembers)
                    .flatMap(e -> e.stream().map(m -> {
                        if (m instanceof SwCustomGroupMember) {
                            return ((SwCustomGroupMember) m).getMember();
                        } else if (m instanceof MemberBrief) {
                            return ((MemberBrief) m).getEmployeeNo();
                        } else {
                            return "";
                        }
                    }))
                    .collect(Collectors.toList());
            List<SwCustomGroupMember> newMembers = new ArrayList<>();
            allEmpNos.forEach(e -> {
                SwCustomGroupMember member = initMember(e, data, groupMembers);
                newMembers.add(member);
            });
            groupMemberBusiness.insertOrUpdate(newMembers);
        }
        return result;
    }

    private SwCustomGroupMember initMember(String eno, UpdateGroupParams data, List<SwCustomGroupMember> groupMembers) {
        SwCustomGroupMember member = groupMembers.stream()
                .filter(e -> e.getMember().equalsIgnoreCase(eno))
                .findFirst()
                .orElse(null);

        MemberBrief memberBrief = data.getMembers().stream()
                .filter(e -> e.getEmployeeNo().equalsIgnoreCase(eno))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(member)) {
            // 对于不存在旧数据的做 初始化成员信息
            member = new SwCustomGroupMember();
            member.setCustomGroupId(data.getGroupID());
            member.setMember(eno);
            member.setMemberType(memberBrief.getMemberType());
        } else {
            // 对于存在旧数据的 按照实际赋值，如果新增数据没有，则做删除操作
            boolean hasUpdate = Objects.nonNull(memberBrief);
            member.setIsDelete(!hasUpdate ? 1 : 0);
            if (hasUpdate) {
                member.setMemberType(memberBrief.getMemberType());
            }
        }
        return member;
    }
}
