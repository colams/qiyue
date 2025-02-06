package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.SwReadStatusBusiness;
import com.foxconn.sw.business.forums.ForumBbsBusiness;
import com.foxconn.sw.business.forums.ForumBbsCommentBusiness;
import com.foxconn.sw.business.forums.ForumParticipantBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.constants.enums.ModuleEnums;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.forums.BbsListVo;
import com.foxconn.sw.data.dto.request.forums.ListPostsParams;
import com.foxconn.sw.data.entity.ForumBbs;
import com.foxconn.sw.data.entity.ForumBbsComment;
import com.foxconn.sw.data.entity.extension.ForumBbsExtension;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
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
    EmployeeUtils employeeUtils;
    @Autowired
    ForumParticipantBusiness participantBusiness;
    @Autowired
    SwReadStatusBusiness readStatusBusiness;


    public PageEntity<List<BbsListVo>> listV2(PageParams<ListPostsParams> data) {
        List<ForumBbsExtension> forumPosts = forumBbsBusiness.queryPosts(data.getParams().getPostsType(),
                data.getParams().getWords(),
                data.getPageSize(),
                data.getCurrentPage());
        if (CollectionUtils.isEmpty(forumPosts)) {
            return new PageEntity(0L, Lists.newArrayList());
        }
        Map<Integer, ForumBbsComment> bbsCommentMap = getCommentIdAndBbsId(forumPosts);
        Map<Integer, Integer> maps = getCommentReadStatus(bbsCommentMap);

        List<BbsListVo> listVo = forumPosts.stream()
                .map(e -> toListVo(e, bbsCommentMap, maps))
                .collect(Collectors.toList());

        Long count = forumBbsBusiness.getPostsCount(data.getParams().getPostsType(),
                data.getParams().getWords());
        Collections.sort(listVo, (a, b) -> b.getNewCount().compareTo(a.getNewCount()));
        PageEntity entity = new PageEntity(count, listVo);
        return entity;
    }

    private BbsListVo toListVo(ForumBbsExtension bbs,
                               Map<Integer, ForumBbsComment> bbsCommentMap,
                               Map<Integer, Integer> maps) {
        ForumBbsComment bbsComment = bbsCommentMap.get(bbs.getId());

        BbsListVo vo = new BbsListVo();
        vo.setId(bbs.getId());
        vo.setAuthor(employeeUtils.mapEmployee(bbs.getAuthorNo()));
        vo.setCreateTime(DateTimeUtils.format(bbs.getCreateTime(), "yyyy-MM-dd HH:mm"));
        if (StringUtils.isNotEmpty(bbs.getProject())) {
            vo.setTitle(String.format("【%s】%s", bbs.getProject(), bbs.getTitle()));
        } else {
            vo.setTitle(bbs.getTitle());
        }
        vo.setContent(Optional.ofNullable(bbsComment).map(ForumBbsComment::getContent).orElse(""));
        vo.setRead(maps.getOrDefault(Optional.ofNullable(bbsComment).map(ForumBbsComment::getId).orElse(0), 0).equals(1));
        vo.setNewCount(Optional.ofNullable(bbs.getSum()).orElse(NumberConstants.ZERO));
        vo.setDiscussantVo(employeeUtils.mapEmployee(Optional.ofNullable(bbsComment).map(ForumBbsComment::getAuthorNo).orElse("")));
        return vo;
    }

    public Map<Integer, Integer> getCommentReadStatus(Map<Integer, ForumBbsComment> bbsIdCommentIDs) {
        List<Integer> commentIds = bbsIdCommentIDs.values().stream().map(ForumBbsComment::getId).toList();
        return readStatusBusiness.getReadStatusList(ModuleEnums.Forum, commentIds);
    }

    public Map<Integer, ForumBbsComment> getCommentIdAndBbsId(List<ForumBbsExtension> forumPosts) {
        List<Integer> bbsIds = forumPosts.stream().map(ForumBbs::getId).collect(Collectors.toList());
        List<ForumBbsComment> comments = forumBbsCommentBusiness.queryCommentByBbsIds(bbsIds);
        return comments.stream().collect(Collectors.toMap(ForumBbsComment::getFbId, e -> e));
    }
}
