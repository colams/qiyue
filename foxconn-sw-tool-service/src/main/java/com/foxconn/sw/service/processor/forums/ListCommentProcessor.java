package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.forums.ForumBbsBusiness;
import com.foxconn.sw.business.forums.ForumBbsCommentBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.forums.CommentsVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.entity.ForumBbs;
import com.foxconn.sw.data.entity.ForumBbsComment;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ListCommentProcessor {

    @Autowired
    ForumBbsCommentBusiness forumBbsCommentBusiness;
    @Autowired
    ForumBbsBusiness forumBbsBusiness;
    @Autowired
    EmployeeUtils employeeUtils;


    public List<CommentsVo> list(PageParams<IntegerParams> data) {
        List<ForumBbsComment> comments = forumBbsCommentBusiness.queryCommentByPostsID(data.getParams().getParams());
        ForumBbs forumBbs = forumBbsBusiness.getForumPosts(data.getParams().getParams());
        if (CollectionUtils.isEmpty(comments)) {
            return Lists.newArrayList();
        }
        List<CommentsVo> vos = mapComments(comments, forumBbs.getAuthorNo());
        return buildTree(vos);
    }

    private List<CommentsVo> buildTree(List<CommentsVo> vos) {
        List<CommentsVo> comments = vos.stream()
                .filter(e -> Objects.isNull(e.getParentId()) || e.getParentId() <= 0)
                .collect(Collectors.toList());
        comments.forEach(e -> {
            e.setReplies(vos.stream().filter(f -> e.getId().equals(f.getParentId())).collect(Collectors.toList()));
        });
        return comments;
    }

    private List<CommentsVo> mapComments(List<ForumBbsComment> comments, String postAuthNo) {
        List<CommentsVo> vos = new ArrayList<>();
        comments.forEach(e -> {
            CommentsVo vo = new CommentsVo();
            vo.setPostsId(e.getFbId());
            vo.setId(e.getId());
            vo.setTargetId(e.getTargetId());
            vo.setParentId(e.getParentId());
            vo.setContent(e.getContent());
            vo.setEmployee(employeeUtils.mapEmployee(e.getAuthorNo()));
            vo.setCreateTime(DateTimeUtils.format(e.getCreateTime()));
            vo.setCanDel(RequestContext.getEmployeeNo().equalsIgnoreCase(e.getAuthorNo())
                    || RequestContext.getEmployeeNo().equalsIgnoreCase(postAuthNo));
            vo.setReplies(Lists.newArrayList());
            vos.add(vo);
        });
        return vos;
    }

    public PageEntity<List<CommentsVo>> listV2(PageParams<IntegerParams> data) {
        List<ForumBbsComment> comments = forumBbsCommentBusiness.queryCommentByPostsID(data.getParams().getParams());
        ForumBbs forumBbs = forumBbsBusiness.getForumPosts(data.getParams().getParams());
        if (CollectionUtils.isEmpty(comments)) {
            return new PageEntity<>();
        }
        List<CommentsVo> vos = mapComments(comments, forumBbs.getAuthorNo());
        Long count = forumBbsCommentBusiness.queryCountByBbsId(data.getParams().getParams());
        return new PageEntity(count, vos);
    }
}
