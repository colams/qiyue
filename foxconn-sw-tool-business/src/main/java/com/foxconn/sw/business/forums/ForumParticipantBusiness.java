package com.foxconn.sw.business.forums;

import com.foxconn.sw.data.entity.ForumParticipant;
import com.foxconn.sw.data.mapper.extension.forums.ForumParticipantExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

}
