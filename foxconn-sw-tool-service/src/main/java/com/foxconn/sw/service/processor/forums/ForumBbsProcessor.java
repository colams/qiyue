package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.forums.*;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.entity.*;
import com.foxconn.sw.service.utils.ResponseUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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
    @Autowired
    ForumParticipantBusiness participantBusiness;
    @Autowired
    ForumFavoriteBusiness favoriteBusiness;
    @Autowired
    ForumPostsAttachmentBusiness attachmentBusiness;

    public Response refresh() {
        List<ForumPosts> postsList = postsBusiness.selectAll();
        Map<Integer, Integer> integerMap = new HashMap<>();
        postsList.forEach(e -> {
            Integer newBbsId = createForumBbs(e);
            integerMap.put(e.getId(), newBbsId);
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
            Map<Integer, Integer> map = new HashMap<>();
            comments.forEach(c -> {
                ForumBbsComment comment1 = new ForumBbsComment();
                comment1.setFbId(newBbsId);
                comment1.setParentId(map.getOrDefault(c.getId(), 0));
                comment1.setAuthorNo(c.getAuthorNo());
                comment1.setIsDelete(c.getIsDelete());
                comment1.setCreateTime(c.getCreateTime());
                comment1.setDatetimeLastchange(c.getDatetimeLastchange());
                comment1.setContent(c.getContent());
                bbsComments.add(comment1);
                map.put(c.getId(), comment1.getId());
            });

            bbsComments.forEach(m -> {
                bbsCommentBusiness.addComment(m);
            });

            List<ForumParticipant> participantList = participantBusiness.selectParticipants(e.getId());
            Optional.ofNullable(participantList).orElse(Lists.newArrayList())
                    .forEach(p -> {
                        ForumParticipant participant = new ForumParticipant();
                        participant.setPostsId(integerMap.getOrDefault(p.getPostsId(), p.getPostsId()));
                        participant.setId(p.getId());
                        participantBusiness.update(participant);
                    });

            List<ForumFavorite> favorites = favoriteBusiness.selectAllFavorites(e.getId());
            Optional.ofNullable(favorites).orElse(Lists.newArrayList())
                    .forEach(p -> {
                        ForumFavorite participant = new ForumFavorite();
                        participant.setPostsId(integerMap.getOrDefault(p.getPostsId(), p.getPostsId()));
                        participant.setId(p.getId());
                        favoriteBusiness.update(participant);
                    });

            List<ForumPostsAttachment> attachments = attachmentBusiness.selectPostsAttachment(e.getId());
            Optional.ofNullable(attachments).orElse(Lists.newArrayList())
                    .forEach(p -> {
                        ForumPostsAttachment attachment = new ForumPostsAttachment();
                        attachment.setPostsId(integerMap.getOrDefault(p.getPostsId(), p.getPostsId()));
                        attachment.setId(p.getId());
                        attachmentBusiness.update(attachment);
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
