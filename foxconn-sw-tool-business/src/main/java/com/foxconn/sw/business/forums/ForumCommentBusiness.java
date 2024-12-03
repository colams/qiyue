package com.foxconn.sw.business.forums;

import com.foxconn.sw.data.entity.ForumComment;
import com.foxconn.sw.data.entity.ForumCommentExample;
import com.foxconn.sw.data.entity.ForumPosts;
import com.foxconn.sw.data.entity.ForumPostsExample;
import com.foxconn.sw.data.mapper.extension.forums.ForumCommentExtMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ForumCommentBusiness {
    @Autowired
    ForumCommentExtMapper forumCommentExtMapper;

    public List<ForumComment> queryCommentByPostsID(Integer postID) {
        ForumCommentExample example = new ForumCommentExample();
        ForumCommentExample.Criteria criteria = example.createCriteria();
        criteria.andPostsIdEqualTo(postID);
        return forumCommentExtMapper.selectByExample(example);
    }

    public Integer addComment(ForumComment comment) {
        forumCommentExtMapper.insertSelective(comment);
        return comment.getId();
    }

    public Integer queryCommentCountByPostsID(Integer id) {
        ForumCommentExample example = new ForumCommentExample();
        ForumCommentExample.Criteria criteria = example.createCriteria();
        criteria.andPostsIdEqualTo(id);
        criteria.andParentIdEqualTo(0);
        List<ForumComment> comments = forumCommentExtMapper.selectByExample(example);
        return Optional.ofNullable(comments)
                .orElse(Lists.newArrayList())
                .size();
    }

    public boolean delete(Integer id) {
        ForumComment comment = new ForumComment();
        comment.setIsDelete(1);

        ForumCommentExample example = new ForumCommentExample();
        ForumCommentExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return forumCommentExtMapper.updateByExampleSelective(comment, example) > 0;
    }
}
