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
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ForumParticipantBusiness {

    @Autowired
    ForumParticipantExtMapper forumParticipantExtMapper;
    @Autowired
    UserBusiness userBusiness;

    public boolean addForumParticipant(int postsID, List<String> participants) {
        participants.forEach(e -> {
            ForumParticipant participant = new ForumParticipant();
            participant.setPostsId(postsID);
            participant.setEmployeeNo(e);
            forumParticipantExtMapper.insertSelective(participant);
        });
        return true;
    }

    public List<ForumsParticipantVo> queryParticipants(Integer id) {
        ForumParticipantExample example = new ForumParticipantExample();
        ForumParticipantExample.Criteria criteria = example.createCriteria();
        criteria.andPostsIdEqualTo(id);
        List<ForumParticipant> forumParticipants = forumParticipantExtMapper.selectByExample(example);
        return Optional.ofNullable(forumParticipants).orElse(Lists.newArrayList())
                .stream()
                .map(e -> convert2Employee(e))
                .collect(Collectors.toList());
    }

    private ForumsParticipantVo convert2Employee(ForumParticipant e) {
        UserInfo userInfo = userBusiness.queryUserInfo(e.getEmployeeNo());
        ForumsParticipantVo participantVo = new ForumsParticipantVo();
        participantVo.setName(userInfo.getEmployeeName());
        participantVo.setEmployeeNo(e.getEmployeeNo());
        participantVo.setDepartmentName(userInfo.getDepartName());
        participantVo.setCreateTime(DateTimeUtils.format(e.getCreateTime()));
        return participantVo;
    }
}
