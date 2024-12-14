package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.forums.*;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.request.forums.PostsParams;
import com.foxconn.sw.data.entity.ForumBbsComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreatePostsProcessor {
    @Autowired
    ForumBbsBusiness forumBbsBusiness;
    @Autowired
    ForumBbsCommentBusiness forumBbsCommentBusiness;

    @Autowired
    ForumPostsBusiness forumPostsBusiness;
    @Autowired
    ForumParticipantBusiness forumParticipantBusiness;
    @Autowired
    ForumPostsAttachmentBusiness postsAttachmentBusiness;

    public Boolean create(PostsParams data) {
        int fbID = forumBbsBusiness.createPosts(data);
        if (fbID > 0) {
            saveBbsComment(fbID, data.getContent());
            postsAttachmentBusiness.insertPostsAttachment(fbID, data.getResources());
            forumParticipantBusiness.addForumParticipant(fbID, data.getParticipants());
        }
        return fbID > 0;
    }

    private Integer saveBbsComment(Integer postsID, String content) {
        ForumBbsComment comment = new ForumBbsComment();
        comment.setFbId(postsID);
        comment.setAuthorNo(RequestContext.getEmployeeNo());
        comment.setContent(content);
        Integer commentId = forumBbsCommentBusiness.addComment(comment);
        return commentId;
    }
}
