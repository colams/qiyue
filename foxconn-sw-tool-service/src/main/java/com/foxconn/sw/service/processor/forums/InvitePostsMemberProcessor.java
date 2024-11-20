package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.forums.ForumParticipantBusiness;
import com.foxconn.sw.data.dto.request.forums.InvitePostsMemberParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class InvitePostsMemberProcessor {


    @Autowired
    ForumParticipantBusiness forumParticipantBusiness;


    public boolean inviteMembers(InvitePostsMemberParams data) {

        if (CollectionUtils.isEmpty(data.getEmployeeNos())) {
            return false;
        }

        return forumParticipantBusiness.addForumParticipant(data.getId(), data.getEmployeeNos());
    }
}
