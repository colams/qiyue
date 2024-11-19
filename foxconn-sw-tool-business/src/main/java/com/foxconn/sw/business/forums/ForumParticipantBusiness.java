package com.foxconn.sw.business.forums;

import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
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

    public boolean addForumParticipant(int postsID, List<String> participants) {
        participants.forEach(e -> {
            ForumParticipant participant = new ForumParticipant();
            participant.setPostsId(postsID);
            participant.setEmployeeNo(e);
            forumParticipantExtMapper.insertSelective(participant);
        });
        return true;
    }

    public List<EmployeeVo> queryParticipants(Integer id) {
        ForumParticipantExample example = new ForumParticipantExample();
        ForumParticipantExample.Criteria criteria = example.createCriteria();
        criteria.andPostsIdEqualTo(id);
        List<ForumParticipant> forumParticipants = forumParticipantExtMapper.selectByExample(example);
        return Optional.ofNullable(forumParticipants).orElse(Lists.newArrayList())
                .stream()
                .map(e -> convert2Employee(e))
                .collect(Collectors.toList());
    }

    private EmployeeVo convert2Employee(ForumParticipant e) {
        EmployeeVo employeeVo = new EmployeeVo();
//        employeeVo.setName();
        employeeVo.setEmployeeNo(e.getEmployeeNo());
//        employeeVo.setDepartmentId();
//        employeeVo.setDepartmentName();
//        employeeVo.setFirstLetter();
//        employeeVo.setPinyin();
//        employeeVo.setAvatar();
        return employeeVo;
    }
}
