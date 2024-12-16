package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.forums.ForumBbsBusiness;
import com.foxconn.sw.business.forums.ForumBbsCommentBusiness;
import com.foxconn.sw.business.forums.ForumCommentBusiness;
import com.foxconn.sw.business.forums.ForumPostsBusiness;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.entity.ForumBbs;
import com.foxconn.sw.data.entity.ForumBbsComment;
import com.foxconn.sw.data.entity.ForumComment;
import com.foxconn.sw.data.entity.ForumPosts;
import com.foxconn.sw.service.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ForumBbsProcessor {

    @Autowired
    ForumPostsBusiness postsBusiness;
    @Autowired
    ForumCommentBusiness forumCommentBusiness;
    @Autowired
    ForumBbsBusiness bbsBusiness;
    @Autowired
    ForumBbsCommentBusiness bbsCommentBusiness;

    public Response refresh() {
        List<ForumPosts> postsList = postsBusiness.selectAll();

        postsList.forEach(e -> {
            Integer newBbsId = createForumBbs(e);
            List<ForumComment> comments = forumCommentBusiness.queryCommentByPostsID(e.getId());
            List<ForumBbsComment> bbsComments = new ArrayList<>();
            ForumBbsComment comment = new ForumBbsComment();
            comment.setFbId(newBbsId);
            comment.setAuthorNo(e.getAuthorNo());
            comment.setIsDelete(e.getIsDelete());
            comment.setCreateTime(e.getCreateTime());
            comment.setDatetimeLastchange(e.getLastchangeDatetime());
            comment.setContent(e.getDescription());
            bbsComments.add(comment);
            comments.forEach(c -> {
                ForumBbsComment comment1 = new ForumBbsComment();
                comment1.setFbId(c.getPostsId());
                comment1.setAuthorNo(c.getAuthorNo());
                comment1.setIsDelete(c.getIsDelete());
                comment1.setCreateTime(c.getCreateTime());
                comment1.setDatetimeLastchange(c.getDatetimeLastchange());
                comment1.setContent(c.getContent());
                bbsComments.add(comment1);
            });

            bbsComments.forEach(m -> {
                bbsCommentBusiness.addComment(m);
            });
        });

        return ResponseUtils.success("123456");
    }

    public Integer createForumBbs(ForumPosts e) {
        ForumBbs forumBbs = new ForumBbs();
        forumBbs.setProject(e.getProject());
        forumBbs.setTitle(e.getTitle());
        forumBbs.setAuthorNo(e.getAuthorNo());
        forumBbs.setIsDelete(e.getIsDelete());
        forumBbs.setCreateTime(e.getCreateTime());
        forumBbs.setDatetimeLastchange(e.getLastchangeDatetime());
        return bbsBusiness.createPosts(forumBbs);
    }

}
