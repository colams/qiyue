package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.forums.*;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.forums.BbsListVo;
import com.foxconn.sw.data.dto.entity.forums.PostsBriefVo;
import com.foxconn.sw.data.dto.request.forums.ListPostsParams;
import com.foxconn.sw.data.entity.ForumBbs;
import com.foxconn.sw.data.entity.ForumBbsComment;
import com.foxconn.sw.data.entity.ForumParticipant;
import com.foxconn.sw.data.entity.ForumPosts;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ListPostsProcessor {


    @Autowired
    ForumBbsBusiness forumBbsBusiness;
    @Autowired
    ForumBbsCommentBusiness forumBbsCommentBusiness;

    @Autowired
    ForumPostsBusiness forumPostsBusiness;
    @Autowired
    EmployeeUtils employeeUtils;
    @Autowired
    ForumParticipantBusiness participantBusiness;
    @Autowired
    ForumCommentBusiness forumCommentBusiness;


    public List<PostsBriefVo> list(PageParams<ListPostsParams> data) {
        List<ForumPosts> forumPosts = forumPostsBusiness.queryPosts(data.getParams().getPostsType(),
                data.getParams().getWords(),
                data.getPageSize(),
                data.getCurrentPage());
        if (CollectionUtils.isEmpty(forumPosts)) {
            return Lists.newArrayList();
        }

        return map(forumPosts);
    }

    private List<PostsBriefVo> map(List<ForumPosts> forumPosts) {
        List<PostsBriefVo> vos = new ArrayList<>();
        forumPosts.forEach(e -> {
            PostsBriefVo vo = new PostsBriefVo();
            vo.setId(e.getId());
            vo.setAuthor(employeeUtils.mapEmployee(e.getAuthorNo()));
            vo.setCreateTime(DateTimeUtils.format(e.getCreateTime()));
            if (StringUtils.isNotEmpty(e.getProject())) {
                vo.setTitle(String.format("【%s】%s", e.getProject(), e.getTitle()));
            } else {
                vo.setTitle(e.getTitle());
            }
            vo.setContent(e.getDescription());
            vo.setMemberCount(participantBusiness.queryParticipantCount(e.getId()));
            vo.setCommentCount(forumCommentBusiness.queryCommentCountByPostsID(e.getId()));
            vos.add(vo);
        });
        return vos;
    }

    public PageEntity<List<BbsListVo>> listV2(PageParams<ListPostsParams> data) {
        List<ForumBbs> forumPosts = forumBbsBusiness.queryPosts(data.getParams().getPostsType(),
                data.getParams().getWords(),
                data.getPageSize(),
                data.getCurrentPage());
        if (CollectionUtils.isEmpty(forumPosts)) {
            return new PageEntity(0L, Lists.newArrayList());
        }

        List<Integer> bbsIds = forumPosts.stream().map(ForumBbs::getId).collect(Collectors.toList());

        List<ForumBbsComment> comments = forumBbsCommentBusiness.queryCommentByBbsIds(bbsIds);
        List<ForumParticipant> forumParticipants = participantBusiness.getBbsParticipantByEno(bbsIds,
                RequestContext.getEmployeeNo());


        Map<Integer, ForumBbsComment> commentMap = comments.stream()
                .collect(Collectors.toMap(e -> e.getFbId(), e -> e));
        Map<Integer, ForumParticipant> participantMap = forumParticipants.stream()
                .collect(Collectors.toMap(e -> e.getId(), e -> e));

        List<BbsListVo> listVo = forumPosts.stream()
                .map(e -> toListVo(e, commentMap, participantMap))
                .collect(Collectors.toList());

        Long count = forumBbsBusiness.getPostsCount(data.getParams().getPostsType(),
                data.getParams().getWords());

        PageEntity entity = new PageEntity(count, listVo);
        return entity;
    }

    private BbsListVo toListVo(ForumBbs bbs,
                               Map<Integer, ForumBbsComment> commentMap,
                               Map<Integer, ForumParticipant> participantMap) {
        ForumBbsComment comment = commentMap.get(bbs.getId());
        ForumParticipant participant = participantMap.get(bbs.getId());
        String content = Optional.ofNullable(comment).map(e -> e.getContent()).orElse("");
        int read = Optional.ofNullable(participant).map(e -> e.getIsRead()).orElse(1);

        BbsListVo vo = new BbsListVo();
        vo.setId(bbs.getId());
        vo.setAuthor(employeeUtils.mapEmployee(bbs.getAuthorNo()));
        vo.setCreateTime(DateTimeUtils.format(bbs.getCreateTime(), "yyyy-MM-dd HH:mm"));
        vo.setTitle(bbs.getTitle());
        vo.setContent(content);
        vo.setRead(NumberConstants.ONE.equals(read));
        return vo;
    }
}
