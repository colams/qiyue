package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.SwReadStatusBusiness;
import com.foxconn.sw.business.forums.ForumBbsBusiness;
import com.foxconn.sw.business.forums.ForumBbsCommentBusiness;
import com.foxconn.sw.business.forums.ForumParticipantBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.forums.BbsListVo;
import com.foxconn.sw.data.dto.request.forums.ListPostsParams;
import com.foxconn.sw.data.entity.ForumBbs;
import com.foxconn.sw.data.entity.ForumBbsComment;
import com.foxconn.sw.data.entity.extension.ForumBbsExtension;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ListPostsProcessor {


    @Autowired
    ForumBbsBusiness forumBbsBusiness;
    @Autowired
    ForumBbsCommentBusiness forumBbsCommentBusiness;
    @Autowired
    EmployeeUtils employeeUtils;
    @Autowired
    ForumParticipantBusiness participantBusiness;
    @Autowired
    SwReadStatusBusiness readStatusBusiness;


    public PageEntity<List<BbsListVo>> listV2(PageParams<ListPostsParams> data) {
        // 查询bbs 帖子信息
        List<ForumBbsExtension> forumPosts = forumBbsBusiness.queryPosts(data.getParams().getPostsType(),
                data.getParams().getWords(),
                data.getPageSize(),
                data.getCurrentPage());
        if (CollectionUtils.isEmpty(forumPosts)) {
            return new PageEntity(0L, Lists.newArrayList());
        }

        Map<Integer, ForumBbsComment> bbsCommentMap = getCommentIdAndBbsId(forumPosts);

        List<BbsListVo> listVo = forumPosts.stream()
                .map(e -> toListVo(e, bbsCommentMap))
                .collect(Collectors.toList());

        Long count = forumBbsBusiness.getPostsCount(data.getParams().getPostsType(),
                data.getParams().getWords());
        PageEntity entity = new PageEntity(count, listVo);
        return entity;
    }

    private BbsListVo toListVo(ForumBbsExtension bbs,
                               Map<Integer, ForumBbsComment> bbsCommentMap) {
        ForumBbsComment bbsComment = bbsCommentMap.get(bbs.getId());

        BbsListVo vo = new BbsListVo();
        vo.setId(bbs.getId());
        vo.setAuthor(employeeUtils.mapEmployee(bbs.getAuthorNo()));
        vo.setCreateTime(DateTimeUtils.format(bbs.getCreateTime(), "yyyy-MM-dd HH:mm"));
        vo.setTitle(Optional.ofNullable(String.format("【%s】%s", bbs.getProject(), bbs.getTitle())).orElse(bbs.getTitle()));
        vo.setContent(Optional.ofNullable(bbsComment).map(ForumBbsComment::getContent).orElse(""));
        vo.setNewCount(Optional.ofNullable(bbs.getSum()).orElse(NumberConstants.ZERO));
        vo.setRead(vo.getNewCount().intValue() > 0);
        vo.setDiscussantVo(employeeUtils.mapEmployee(Optional.ofNullable(bbsComment).map(ForumBbsComment::getAuthorNo).orElse("")));
        return vo;
    }

    public Map<Integer, ForumBbsComment> getCommentIdAndBbsId(List<ForumBbsExtension> forumPosts) {
        Set<Integer> bbsIds = forumPosts.stream().map(ForumBbs::getId).collect(Collectors.toSet());
        List<ForumBbsComment> comments = forumBbsCommentBusiness.queryCommentByBbsIds(bbsIds.stream().toList());
        return comments.stream().collect(Collectors.toMap(ForumBbsComment::getFbId, e -> e));
    }
}
