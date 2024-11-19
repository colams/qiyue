package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.forums.ForumParticipantBusiness;
import com.foxconn.sw.business.forums.ForumPostsBusiness;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.entity.ResourceVo;
import com.foxconn.sw.data.dto.entity.forums.PostsDetailVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.entity.ForumPosts;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        detailVo.setResources(mapResource(forumPosts.getResourceIds()));
        detailVo.setViewCount(0);
        detailVo.setCommentCount(0);
        return detailVo;
    }

    private List<ResourceVo> mapResource(String resourceIDs) {
        if (StringUtils.isEmpty(resourceIDs)) {
            return Lists.newArrayList();
        }

        List<Integer> resourceIDsInt = JsonUtils.deserialize(resourceIDs, List.class, Integer.class);

        List<SwAppendResource> resources = appendResourceBusiness.getAppendResources(resourceIDsInt);
        List<ResourceVo> resourceVos = new ArrayList<>();
        Optional.ofNullable(resources).orElse(Lists.newArrayList()).forEach(e -> {
            ResourceVo resourceVo = new ResourceVo();
            resourceVo.setId(e.getId());
            resourceVo.setName(e.getOriginName());
            resourceVo.setUrl(ConvertUtils.urlPreFix(e.getId(), e.getFilePath()));
            resourceVos.add(resourceVo);
        });
        return resourceVos;
    }
}
