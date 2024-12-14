package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.forums.ForumCommentBusiness;
import com.foxconn.sw.business.forums.ForumParticipantBusiness;
import com.foxconn.sw.business.forums.ForumPostsBusiness;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.forums.PostsBriefVo;
import com.foxconn.sw.data.dto.request.forums.ListPostsParams;
import com.foxconn.sw.data.entity.ForumPosts;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListPostsProcessor {
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
                data.getParams().getWords());
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

}
