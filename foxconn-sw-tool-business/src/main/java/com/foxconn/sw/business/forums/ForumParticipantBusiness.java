package com.foxconn.sw.business.forums;

import com.foxconn.sw.business.account.UserBusiness;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.forums.ForumsParticipantVo;
import com.foxconn.sw.data.entity.ForumParticipant;
import com.foxconn.sw.data.entity.ForumParticipantExample;
import com.foxconn.sw.data.mapper.extension.forums.ForumParticipantExtMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ForumParticipantBusiness {

    @Autowired
    ForumParticipantExtMapper forumParticipantExtMapper;
    @Autowired
    UserBusiness userBusiness;

    public boolean addForumParticipant(int postsID, List<String> participants) {
        List<ForumParticipant> participantList = selectParticipants(postsID);
        participants.forEach(e -> {
            boolean hasEmployee = participantList.stream().anyMatch(p -> p.getEmployeeNo().equalsIgnoreCase(e));
            if (hasEmployee) {
                return;
            }
            ForumParticipant participant = new ForumParticipant();
            participant.setPostsId(postsID);
            participant.setEmployeeNo(e);
            forumParticipantExtMapper.insertSelective(participant);
        });
        return true;
    }

    public List<ForumParticipant> selectParticipants(Integer id) {
        ForumParticipantExample example = new ForumParticipantExample();
        ForumParticipantExample.Criteria criteria = example.createCriteria();
        criteria.andPostsIdEqualTo(id);
        criteria.andIsDeleteEqualTo(0);
        return forumParticipantExtMapper.selectByExample(example);
    }

    public List<ForumParticipant> getBbsParticipantByEno(List<Integer> bbsIds, String employeeNo) {
        ForumParticipantExample example = new ForumParticipantExample();
        ForumParticipantExample.Criteria criteria = example.createCriteria();
        criteria.andPostsIdIn(bbsIds);
        criteria.andEmployeeNoEqualTo(employeeNo);
        criteria.andIsDeleteEqualTo(0);
        return forumParticipantExtMapper.selectByExample(example);
    }

    public List<ForumsParticipantVo> queryParticipants(Integer id) {
        ForumParticipantExample example = new ForumParticipantExample();
        ForumParticipantExample.Criteria criteria = example.createCriteria();
        criteria.andPostsIdEqualTo(id);
        example.setOrderByClause(" create_time ");
        List<ForumParticipant> forumParticipants = forumParticipantExtMapper.selectByExample(example);
        return Optional.ofNullable(forumParticipants).orElse(Lists.newArrayList())
                .stream()
                .map(e -> convert2Employee(e))
                .filter(e -> Objects.nonNull(e))
                .collect(Collectors.toList());
    }

    public Integer queryParticipantCount(Integer id) {
        ForumParticipantExample example = new ForumParticipantExample();
        ForumParticipantExample.Criteria criteria = example.createCriteria();
        criteria.andPostsIdEqualTo(id);
        List<ForumParticipant> forumParticipants = forumParticipantExtMapper.selectByExample(example);
        return Optional.ofNullable(forumParticipants).orElse(Lists.newArrayList()).size();
    }

    private ForumsParticipantVo convert2Employee(ForumParticipant e) {
        UserInfo userInfo = userBusiness.queryUserInfo(e.getEmployeeNo());
        if (Objects.isNull(userInfo)) {
            return null;
        }

        ForumsParticipantVo participantVo = new ForumsParticipantVo();
        participantVo.setName(userInfo.getEmployeeName());
        participantVo.setEmployeeNo(e.getEmployeeNo());
        participantVo.setDepartmentName(userInfo.getDepartName());
        participantVo.setCreateTime(DateTimeUtils.format(e.getCreateTime()));
        return participantVo;
    }

    public boolean update(ForumParticipant participant) {
        return forumParticipantExtMapper.updateByPrimaryKeySelective(participant) > 0;
    }
}
