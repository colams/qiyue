package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.SwReadStatusBusiness;
import com.foxconn.sw.business.forums.ForumBbsBusiness;
import com.foxconn.sw.business.forums.ForumBbsCommentBusiness;
import com.foxconn.sw.business.forums.ForumParticipantBusiness;
import com.foxconn.sw.business.forums.ForumPostsAttachmentBusiness;
import com.foxconn.sw.data.constants.enums.ModuleEnums;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.request.forums.PostsParams;
import com.foxconn.sw.data.entity.ForumBbsComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreatePostsProcessor {
    @Autowired
    ForumBbsBusiness forumBbsBusiness;
    @Autowired
    ForumBbsCommentBusiness forumBbsCommentBusiness;
    @Autowired
    ForumParticipantBusiness forumParticipantBusiness;
    @Autowired
    ForumPostsAttachmentBusiness postsAttachmentBusiness;
    @Autowired
    SwReadStatusBusiness readStatusBusiness;

    public Boolean create(PostsParams data) {
        int fbID = forumBbsBusiness.createPosts(data);
        if (fbID > 0) {
            List<String> participants = data.getParticipants();
            if (!participants.contains(RequestContext.getEmployeeNo())) {
                participants.add(RequestContext.getEmployeeNo());
            }

            Integer commentId = saveBbsComment(fbID, data.getContent());
            postsAttachmentBusiness.insertPostsAttachment(fbID, commentId, data.getResources());
            forumParticipantBusiness.insertForumParticipant(fbID, participants);
            readStatusBusiness.insertReadStatus(ModuleEnums.Forum, commentId);
        }
        return fbID > 0;
    }

    private Integer saveBbsComment(Integer postsID, String content) {
        ForumBbsComment comment = new ForumBbsComment();
        comment.setFbId(postsID);
        comment.setAuthorNo(RequestContext.getEmployeeNo());
        comment.setContent(content);
        Integer commentId = forumBbsCommentBusiness.insertComment(comment);
        return commentId;
    }
}
