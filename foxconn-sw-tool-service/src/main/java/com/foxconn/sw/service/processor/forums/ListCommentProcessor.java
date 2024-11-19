package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.forums.ForumCommentBusiness;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.forums.CommentsVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.entity.ForumComment;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListCommentProcessor {

    @Autowired
    ForumCommentBusiness forumCommentBusiness;
    @Autowired
    EmployeeUtils employeeUtils;


    public List<CommentsVo> list(PageParams<IntegerParams> data) {
        List<ForumComment> comments = forumCommentBusiness.queryCommentByPostsID(data.getParams().getParams());
        if (CollectionUtils.isEmpty(comments)) {
            return Lists.newArrayList();
        }
        return mapComments(comments);
    }

    private List<CommentsVo> mapComments(List<ForumComment> comments) {
        List<CommentsVo> vos = new ArrayList<>();
        comments.forEach(e -> {
            CommentsVo vo = new CommentsVo();
            vo.setPostsId(e.getPostsId());
            vo.setId(e.getId());
            vo.setTargetId(e.getTargetId());
            vo.setContent(e.getContent());
            vo.setEmployee(employeeUtils.mapEmployee(e.getAuthorNo()));
            vo.setReplies(Lists.newArrayList());
            vos.add(vo);
        });
        return vos;
    }
}
