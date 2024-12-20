package com.foxconn.sw.business.forums;

import com.foxconn.sw.data.entity.ForumBbsComment;
import com.foxconn.sw.data.entity.ForumBbsCommentExample;
import com.foxconn.sw.data.mapper.extension.forums.ForumBbsCommentExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            ForumBbsComment comment = bbsCommentExtMapper.selectByFbId(id);
            if (Objects.nonNull(comment)) {
                comments.add(comment);
            }
        }
        return comments;
    }

    public Long queryCountByBbsId(Integer params) {
        return bbsCommentExtMapper.queryCountByBbsId(params);
    }
}
