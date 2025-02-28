package com.foxconn.sw.business.meeting;

import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.entity.SwMeetingMember;
import com.foxconn.sw.data.entity.SwMeetingMemberExample;
import com.foxconn.sw.data.mapper.extension.meeting.SwMeetingMemberExtensionMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MeetingMemberBusiness {

    @Autowired
    SwMeetingMemberExtensionMapper meetingMemberMapper;
    @Autowired
    EmployeeBusiness employeeBusiness;

    public Boolean updateMeetingMember(int meetingID, Map<String, Integer> memberMap) {
        List<SwMeetingMember> oldMemberList = queryMeetingMemberByMeetingID(meetingID);

        processUpdate(oldMemberList, memberMap);
        processInsert(oldMemberList, memberMap, meetingID);
        return true;
    }

    private boolean processUpdate(List<SwMeetingMember> oldMemberList, Map<String, Integer> memberMap) {
        List<SwMeetingMember> updateEnos = oldMemberList.stream()
                .filter(e -> diff(memberMap, e))
                .map(e -> toNewMember(e, memberMap))
                .collect(Collectors.toList());

        updateEnos.forEach(e -> {
            meetingMemberMapper.updateByPrimaryKeySelective(e);
        });
        return true;
    }

    private boolean processInsert(List<SwMeetingMember> oldMemberList, Map<String, Integer> memberMap, int meetingID) {
        List<String> newEnos = Lists.newArrayList(memberMap.keySet());
        List<SwMeetingMember> insertEnos = newEnos.stream()
                .filter(e -> shouldInsert(oldMemberList, e))
                .map(e -> toNewMember(meetingID, e, memberMap))
                .collect(Collectors.toList());


        insertEnos.forEach(e -> {
            meetingMemberMapper.insertSelective(e);
        });
        return true;
    }

    private SwMeetingMember toNewMember(int meetingID, String eNo, Map<String, Integer> memberMap) {
        SwMeetingMember meetingMember = new SwMeetingMember();
        meetingMember.setRole(memberMap.get(eNo));
        meetingMember.setEmployeeNo(eNo);
        meetingMember.setMeetingId(meetingID);
        meetingMember.setName(employeeBusiness.selectEmployeeByENo(eNo).getName());
        return meetingMember;
    }

    private SwMeetingMember toNewMember(SwMeetingMember e, Map<String, Integer> memberMap) {
        SwMeetingMember meetingMember = new SwMeetingMember();

        int deleteStatus = memberMap.containsKey(e.getEmployeeNo()) ? 0 : 1;

        meetingMember.setId(e.getId());
        meetingMember.setEmployeeNo(e.getEmployeeNo());
        meetingMember.setIsDelete(deleteStatus);
        if (deleteStatus == 0) {
            meetingMember.setRole(memberMap.get(e.getEmployeeNo()));
        }

        return meetingMember;
    }


    private boolean diff(Map<String, Integer> memberMap, SwMeetingMember meetingMember) {
        int role = memberMap.getOrDefault(meetingMember.getEmployeeNo(), 0);
        return role == 0 || meetingMember.getRole() != role;
    }

    private boolean shouldInsert(List<SwMeetingMember> memberList, String eNo) {
        return memberList.stream().allMatch(e -> !eNo.equalsIgnoreCase(e.getEmployeeNo()));
    }

    public List<SwMeetingMember> queryMeetingMemberByMeetingID(Integer meetingID) {
        SwMeetingMemberExample example = new SwMeetingMemberExample();
        SwMeetingMemberExample.Criteria criteria = example.createCriteria();
        criteria.andMeetingIdEqualTo(meetingID);
        criteria.andIsDeleteEqualTo(0);
        return meetingMemberMapper.selectByExample(example);
    }

    public List<SwMeetingMember> queryMeetingMemberByDetailId(Integer meetingDetailID) {
        SwMeetingMemberExample example = new SwMeetingMemberExample();
        SwMeetingMemberExample.Criteria criteria = example.createCriteria();
        criteria.andMeetingDetailIdEqualTo(meetingDetailID);
        criteria.andIsDeleteEqualTo(0);
        return meetingMemberMapper.selectByExample(example);
    }

    public List<SwMeetingMember> queryMeetingMember(List<Integer> meetingIDs) {
        SwMeetingMemberExample example = new SwMeetingMemberExample();
        SwMeetingMemberExample.Criteria criteria = example.createCriteria();
        criteria.andMeetingIdIn(meetingIDs);
        criteria.andIsDeleteEqualTo(0);
        return meetingMemberMapper.selectByExample(example);
    }
}
