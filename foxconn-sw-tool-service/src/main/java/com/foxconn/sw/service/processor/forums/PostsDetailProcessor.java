package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.forums.ForumBbsBusiness;
import com.foxconn.sw.business.forums.ForumFavoriteBusiness;
import com.foxconn.sw.business.forums.ForumParticipantBusiness;
import com.foxconn.sw.business.forums.ForumPostsAttachmentBusiness;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.DomainRetrieval;
import com.foxconn.sw.common.utils.FilePathUtils;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.entity.forums.BbsDetailVo;
import com.foxconn.sw.data.dto.entity.forums.ForumsParticipantVo;
import com.foxconn.sw.data.dto.entity.forums.PostsResourceVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.entity.ForumAttachment;
import com.foxconn.sw.data.entity.ForumBbs;
import com.foxconn.sw.data.entity.ForumParticipant;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    FilePathUtils filePathUtils;

    public BbsDetailVo detailV2(IntegerParams data) {
        ForumBbs forumBbs = forumBbsBusiness.getForumBbs(data.getParams());
        return map2Bbs(forumBbs);
    }

    private BbsDetailVo map2Bbs(ForumBbs forumBbs) {
        ForumParticipant participant = forumParticipantBusiness.queryParticipants(forumBbs.getId(), RequestContext.getEmployeeNo());

        BbsDetailVo bbsDetailVo = new BbsDetailVo();
        bbsDetailVo.setId(forumBbs.getId());

        if (StringUtils.isNotEmpty(forumBbs.getProject())) {
            bbsDetailVo.setTitle(String.format("【%s】%s", forumBbs.getProject(), forumBbs.getTitle()));
        } else {
            bbsDetailVo.setTitle(forumBbs.getTitle());
        }
        bbsDetailVo.setCollectionStatus(favoriteBusiness.queryCollectionStatus(forumBbs.getId()));
        bbsDetailVo.setCanDel(RequestContext.getEmployeeNo().equalsIgnoreCase(forumBbs.getAuthorNo()));
        bbsDetailVo.setStatus(forumBbs.getStatus());
        bbsDetailVo.setIsHidden(participant.getHidden());
        return bbsDetailVo;
    }

    public List<PostsResourceVo> attachments(IntegerParams data) {
        if (Objects.isNull(data.getParams())) {
            return Lists.newArrayList();
        }

        ForumBbs forumBbs = forumBbsBusiness.getForumBbs(data.getParams());
        List<ForumAttachment> attachments = postsAttachmentBusiness.selectPostsAttachment(data.getParams());

        if (CollectionUtils.isEmpty(attachments)) {
            return Lists.newArrayList();
        }
        Map<Integer, ForumAttachment> attachmentMap = attachments.stream()
                .collect(Collectors.toMap(ForumAttachment::getResourceId, e -> e));

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
            resourceVo.setCreateTime(DateTimeUtils.format(e.getCreateTime()));
            resourceVo.setViewUrl(String.format("%s/upload/%s/%s", DomainRetrieval.getDomain(), e.getUploadType(), e.getFilePath()));
            resourceVo.setCanDelete(getCanDeleteStatus(forumBbs.getAuthorNo(), e.getOperator()));
            resourceVos.add(resourceVo);
        });
        return resourceVos;

    }

    private boolean getCanDeleteStatus(String authorNo, String operator) {
        return RequestContext.getEmployeeNo().equalsIgnoreCase(authorNo) || RequestContext.getEmployeeNo().equalsIgnoreCase(operator);
    }

    public List<ForumsParticipantVo> participants(IntegerParams data) {
        if (Objects.isNull(data.getParams())) {
            return Lists.newArrayList();
        }

        return forumParticipantBusiness.queryParticipants(data.getParams());
    }
}
