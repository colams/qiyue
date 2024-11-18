package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.forums.ForumPostsBusiness;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.forums.PostsBriefVo;
import com.foxconn.sw.data.dto.request.forums.ListPostsParams;
import com.foxconn.sw.data.entity.ForumPosts;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListPostsProcessor {
    @Autowired
    ForumPostsBusiness forumPostsBusiness;


    public List<PostsBriefVo> list(PageParams<ListPostsParams> data) {

        List<ForumPosts> forumPosts = forumPostsBusiness.queryPosts(data.getParams().getPostsType(), data.getParams().getWords());
        if (CollectionUtils.isEmpty(forumPosts)) {
            return Lists.newArrayList();
        }

        List<PostsBriefVo> vos = map(forumPosts);
        return vos;
    }

    private List<PostsBriefVo> map(List<ForumPosts> forumPosts) {
        List<PostsBriefVo> vos = new ArrayList<>();
        forumPosts.forEach(e -> {
            PostsBriefVo vo = new PostsBriefVo();
            vo.setId(e.getId());
            vo.setAuthor(mapEmployee(e.getAuthorNo()));
            vo.setCreateTime(DateTimeUtils.format(e.getCreateTime()));
            vo.setTitle(e.getTitle());
            vo.setContent(e.getDescription());
            vo.setViewCount(0);
            vo.setCommentCount(0);
            vos.add(vo);
        });
        return vos;
    }

    private EmployeeVo mapEmployee(String authorNo) {
        EmployeeVo employeeVo = new EmployeeVo();
        return employeeVo;
    }
}
