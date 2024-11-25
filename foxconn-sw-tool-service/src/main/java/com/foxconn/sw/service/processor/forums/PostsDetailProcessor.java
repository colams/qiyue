package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.forums.ForumFavoriteBusiness;
import com.foxconn.sw.business.forums.ForumParticipantBusiness;
import com.foxconn.sw.business.forums.ForumPostsAttachmentBusiness;
import com.foxconn.sw.business.forums.ForumPostsBusiness;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.dto.entity.forums.PostsDetailVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.entity.ForumPosts;
import com.foxconn.sw.data.entity.ForumPostsAttachment;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PostsDetailProcessor {
    @Autowired
    ForumPostsBusiness forumPostsBusiness;
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


    public PostsDetailVo detail(IntegerParams data) {
        ForumPosts forumPosts = forumPostsBusiness.getForumPosts(data.getParams());
        return map(forumPosts);
    }

    private PostsDetailVo map(ForumPosts forumPosts) {
        PostsDetailVo detailVo = new PostsDetailVo();
        detailVo.setId(forumPosts.getId());
        detailVo.setAuthor(employeeUtils.mapEmployee(forumPosts.getAuthorNo()));
        detailVo.setCreateTime(DateTimeUtils.format(forumPosts.getCreateTime()));
        detailVo.setTitle(forumPosts.getTitle());
        detailVo.setContent(forumPosts.getDescription());
        detailVo.setParticipants(forumParticipantBusiness.queryParticipants(forumPosts.getId()));
        detailVo.setResources(mapResource(forumPosts.getId()));
        detailVo.setMemberCount(detailVo.getParticipants().size());
        detailVo.setCommentCount(0);
        detailVo.setCollectionStatus(favoriteBusiness.queryCollectionStatus(forumPosts.getId()));
        return detailVo;
    }

    private List<ResourceVo> mapResource(Integer postsID) {
        if (Objects.isNull(postsID)) {
            return Lists.newArrayList();
        }

        List<ForumPostsAttachment> attachments = postsAttachmentBusiness.selectPostsAttachment(postsID);

        if (CollectionUtils.isEmpty(attachments)) {
            return Lists.newArrayList();
        }
        List<Integer> resourceIDsInt = attachments.stream().map(e -> e.getResourceId()).collect(Collectors.toList());

        List<SwAppendResource> resources = appendResourceBusiness.getAppendResources(resourceIDsInt);
        List<ResourceVo> resourceVos = new ArrayList<>();
        Optional.ofNullable(resources).orElse(Lists.newArrayList()).forEach(e -> {
            ResourceVo resourceVo = new ResourceVo();
            resourceVo.setId(e.getId());
            resourceVo.setName(e.getOriginName());
            resourceVo.setUrl(ConvertUtils.urlPreFix(e.getId(), e.getFilePath()));
            resourceVo.setOperator(employeeUtils.mapEmployee(e.getOperator()));
            resourceVos.add(resourceVo);
        });
        return resourceVos;
    }
}
