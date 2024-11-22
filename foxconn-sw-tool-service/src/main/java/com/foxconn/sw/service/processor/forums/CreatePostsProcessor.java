package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.forums.ForumParticipantBusiness;
import com.foxconn.sw.business.forums.ForumPostsAttachmentBusiness;
import com.foxconn.sw.business.forums.ForumPostsBusiness;
import com.foxconn.sw.data.dto.request.forums.PostsParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class CreatePostsProcessor {

    @Autowired
    ForumPostsBusiness forumPostsBusiness;
    @Autowired
    ForumParticipantBusiness forumParticipantBusiness;
    @Autowired
    ForumPostsAttachmentBusiness postsAttachmentBusiness;


    public Boolean create(PostsParams data) {
        int postsID = forumPostsBusiness.createPosts(data);
        if (postsID > 0 && !CollectionUtils.isEmpty(data.getParticipants())) {
            postsAttachmentBusiness.insertPostsAttachment(postsID, data.getResources());
            forumParticipantBusiness.addForumParticipant(postsID, data.getParticipants());
        }
        return postsID > 0;
    }
}
