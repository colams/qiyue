package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.forums.ForumBbsCommentBusiness;
import com.foxconn.sw.business.forums.ForumPostsAttachmentBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.request.forums.CommentsParams;
import com.foxconn.sw.data.entity.ForumBbsComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateCommentProcessor {

    @Autowired
    ForumBbsCommentBusiness forumBbsCommentBusiness;
    @Autowired
    ForumPostsAttachmentBusiness postsAttachmentBusiness;

    public Integer createComments(CommentsParams data) {
        ForumBbsComment comment = new ForumBbsComment();
        comment.setFbId(data.getPostsId());
        comment.setParentId(data.getParentId());
        comment.setTargetId(data.getTargetId());
        comment.setAuthorNo(RequestContext.getEmployeeNo());
        comment.setContent(data.getContent());
        Integer commentId = forumBbsCommentBusiness.addComment(comment);
        if (commentId > 0) {
            postsAttachmentBusiness.insertPostsAttachment(data.getPostsId(), commentId, data.getResources());
        }
        return commentId;
    }
}
