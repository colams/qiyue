package com.foxconn.sw.business.forums;

import com.foxconn.sw.data.entity.ForumComment;
import com.foxconn.sw.data.entity.ForumCommentExample;
import com.foxconn.sw.data.mapper.extension.forums.ForumCommentExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public boolean addComment(ForumComment comment) {
        return forumCommentExtMapper.insertSelective(comment) > 0;
    }
}
