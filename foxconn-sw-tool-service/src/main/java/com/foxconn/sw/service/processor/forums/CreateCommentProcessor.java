package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.forums.ForumCommentBusiness;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.request.forums.CommentsParams;
import com.foxconn.sw.data.entity.ForumComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class CreateCommentProcessor {

    @Autowired
    ForumCommentBusiness forumCommentBusiness;

    public boolean createComments(CommentsParams data) {
        ForumComment comment = new ForumComment();
        comment.setPostsId(data.getPostsId());
        comment.setParentId(data.getParentId());
        comment.setTargetId(data.getTargetId());
        comment.setAuthorNo(RequestContext.getEmployeeNo());
        if (!CollectionUtils.isEmpty(data.getResources())) {
            comment.setResources(JsonUtils.serialize(data.getResources()));
        }
        comment.setContent(data.getContent());
        return forumCommentBusiness.addComment(comment);
    }
}
