package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.forums.ForumCommentBusiness;
import com.foxconn.sw.business.forums.ForumPostsAttachmentBusiness;
import com.foxconn.sw.data.dto.request.forums.CommentsParams;
import com.foxconn.sw.data.entity.ForumComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateCommentProcessor {

    @Autowired
    ForumCommentBusiness forumCommentBusiness;
    @Autowired
    ForumPostsAttachmentBusiness postsAttachmentBusiness;

    public Integer createComments(CommentsParams data) {
        ForumComment comment = new ForumComment();
        comment.setPostsId(data.getPostsId());
        comment.setParentId(data.getParentId());
        comment.setTargetId(data.getTargetId());
        comment.setAuthorNo(RequestContext.getEmployeeNo());
        comment.setContent(data.getContent());
        Integer commentId = forumCommentBusiness.addComment(comment);
        if (commentId > 0) {
            postsAttachmentBusiness.insertPostsAttachment(data.getPostsId(), commentId, data.getResources());
        }
        return commentId;
    }
}
