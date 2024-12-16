package com.foxconn.sw.business.forums;

import com.foxconn.sw.data.entity.ForumBbsComment;
import com.foxconn.sw.data.entity.ForumBbsCommentExample;
import com.foxconn.sw.data.mapper.extension.forums.ForumBbsCommentExtMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ForumBbsCommentBusiness {
    @Autowired
    ForumBbsCommentExtMapper bbsCommentExtMapper;

    public List<ForumBbsComment> queryCommentByPostsID(Integer postID) {
        ForumBbsCommentExample example = new ForumBbsCommentExample();
        ForumBbsCommentExample.Criteria criteria = example.createCriteria();
        criteria.andFbIdEqualTo(postID);
        criteria.andIsDeleteEqualTo(0);
        return bbsCommentExtMapper.selectByExampleWithBLOBs(example);
    }

    public Integer addComment(ForumBbsComment comment) {
        bbsCommentExtMapper.insertSelective(comment);
        return comment.getId();
    }

    public Integer queryCommentCountByPostsID(Integer id) {
        ForumBbsCommentExample example = new ForumBbsCommentExample();
        ForumBbsCommentExample.Criteria criteria = example.createCriteria();
        criteria.andFbIdEqualTo(id);
        criteria.andParentIdEqualTo(0);
        List<ForumBbsComment> comments = bbsCommentExtMapper.selectByExampleWithBLOBs(example);
        return Optional.ofNullable(comments)
                .orElse(Lists.newArrayList())
                .size();
    }

    public boolean delete(Integer id) {
        ForumBbsComment comment = new ForumBbsComment();
        comment.setIsDelete(1);

        ForumBbsCommentExample example = new ForumBbsCommentExample();
        ForumBbsCommentExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return bbsCommentExtMapper.updateByExampleSelective(comment, example) > 0;
    }

    public List<ForumBbsComment> queryCommentByBbsIds(List<Integer> bbsIds) {
        List<ForumBbsComment> comments = new ArrayList<>();
        for (Integer id : bbsIds) {
            comments.add(bbsCommentExtMapper.selectByFbId(id));
        }
        return comments;
    }

    public Long queryCountByBbsId(Integer params) {
        return bbsCommentExtMapper.queryCountByBbsId(params);
    }
}
