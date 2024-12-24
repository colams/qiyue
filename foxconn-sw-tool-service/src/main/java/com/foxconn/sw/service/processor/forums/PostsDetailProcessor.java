package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.forums.*;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.entity.forums.BbsDetailVo;
import com.foxconn.sw.data.dto.entity.forums.ForumsParticipantVo;
import com.foxconn.sw.data.dto.entity.forums.PostsDetailVo;
import com.foxconn.sw.data.dto.entity.forums.PostsResourceVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.entity.ForumBbs;
import com.foxconn.sw.data.entity.ForumPostsAttachment;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PostsDetailProcessor {
    @Autowired
    ForumBbsBusiness forumBbsBusiness;
    @Autowired
    ForumParticipantBusiness forumParticipantBusiness;
    @Autowired
    EmployeeUtils employeeUtils;
    @Autowired
    SwAppendResourceBusiness appendResourceBusiness;
    @Autowired
    ForumPostsAttachmentBusiness postsAttachmentBusiness;
    @Autowired
    ForumFavoriteBusiness favoriteBusiness;

    public BbsDetailVo detailV2(IntegerParams data) {
        ForumBbs forumBbs = forumBbsBusiness.getForumBbs(data.getParams());
        return map2Bbs(forumBbs);
    }

    private BbsDetailVo map2Bbs(ForumBbs forumBbs) {
        BbsDetailVo bbsDetailVo = new BbsDetailVo();
        bbsDetailVo.setId(forumBbs.getId());
        bbsDetailVo.setTitle(forumBbs.getTitle());
        bbsDetailVo.setCollectionStatus(favoriteBusiness.queryCollectionStatus(forumBbs.getId()));
        bbsDetailVo.setCanDel(RequestContext.getEmployeeNo().equalsIgnoreCase(forumBbs.getAuthorNo()));
        return bbsDetailVo;
    }


    private List<PostsResourceVo> mapResource(Integer postsID) {
        if (Objects.isNull(postsID)) {
            return Lists.newArrayList();
        }

        List<ForumPostsAttachment> attachments = postsAttachmentBusiness.selectPostsAttachment(postsID);

        if (CollectionUtils.isEmpty(attachments)) {
            return Lists.newArrayList();
        }
        List<Integer> resourceIDsInt = attachments.stream().map(e -> e.getResourceId()).collect(Collectors.toList());
        Map<Integer, ForumPostsAttachment> attachmentMap = attachments.stream()
                .collect(Collectors.toMap(ForumPostsAttachment::getResourceId, e -> e));

        List<SwAppendResource> resources = appendResourceBusiness.getAppendResources(attachmentMap.keySet().stream().toList());
        List<PostsResourceVo> resourceVos = new ArrayList<>();
        Optional.ofNullable(resources).orElse(Lists.newArrayList()).forEach(e -> {
            PostsResourceVo resourceVo = new PostsResourceVo();
            resourceVo.setPostsId(postsID);
            resourceVo.setCommentId(attachmentMap.get(e.getId()).getCommentId());
            resourceVo.setId(e.getId());
            resourceVo.setName(e.getOriginName());
            resourceVo.setUrl(ConvertUtils.urlPreFix(e.getId(), e.getFilePath()));
            resourceVo.setOperator(employeeUtils.mapEmployee(e.getOperator()));
            resourceVos.add(resourceVo);
        });
        return resourceVos;
    }

    public List<PostsResourceVo> attachments(IntegerParams data) {
        if (Objects.isNull(data.getParams())) {
            return Lists.newArrayList();
        }

        List<ForumPostsAttachment> attachments = postsAttachmentBusiness.selectPostsAttachment(data.getParams());

        if (CollectionUtils.isEmpty(attachments)) {
            return Lists.newArrayList();
        }
        Map<Integer, ForumPostsAttachment> attachmentMap = attachments.stream()
                .collect(Collectors.toMap(ForumPostsAttachment::getResourceId, e -> e));

        List<SwAppendResource> resources = appendResourceBusiness.getAppendResources(attachmentMap.keySet().stream().toList());
        List<PostsResourceVo> resourceVos = new ArrayList<>();
        Optional.ofNullable(resources).orElse(Lists.newArrayList()).forEach(e -> {
            PostsResourceVo resourceVo = new PostsResourceVo();
            resourceVo.setPostsId(data.getParams());
            resourceVo.setCommentId(attachmentMap.get(e.getId()).getCommentId());
            resourceVo.setId(e.getId());
            resourceVo.setName(e.getOriginName());
            resourceVo.setUrl(ConvertUtils.urlPreFix(e.getId(), e.getFilePath()));
            resourceVo.setOperator(employeeUtils.mapEmployee(e.getOperator()));
            resourceVos.add(resourceVo);
        });
        return resourceVos;

    }

    public List<ForumsParticipantVo> participants(IntegerParams data) {
        if (Objects.isNull(data.getParams())) {
            return Lists.newArrayList();
        }

        return forumParticipantBusiness.queryParticipants(data.getParams());
    }
}
