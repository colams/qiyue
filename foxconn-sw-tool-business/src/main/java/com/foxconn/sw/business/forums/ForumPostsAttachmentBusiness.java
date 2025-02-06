package com.foxconn.sw.business.forums;

import com.foxconn.sw.data.entity.ForumAttachment;
import com.foxconn.sw.data.entity.ForumAttachmentExample;
import com.foxconn.sw.data.mapper.extension.forums.ForumAttachmentExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ForumPostsAttachmentBusiness {

    @Autowired
    ForumAttachmentExtMapper forumAttachmentExtMapper;

    public boolean insertPostsAttachment(Integer postsID, List<Integer> resourceIDs) {
        resourceIDs.forEach(e -> {
            ForumAttachment postsAttachment = new ForumAttachment();
            postsAttachment.setFbId(postsID);
            postsAttachment.setResourceId(e);
            forumAttachmentExtMapper.insertSelective(postsAttachment);
        });
        return true;
    }

    public boolean insertPostsAttachment(Integer postsID, Integer commentId, List<Integer> resourceIDs) {
        resourceIDs.forEach(e -> {
            ForumAttachment postsAttachment = new ForumAttachment();
            postsAttachment.setFbId(postsID);
            postsAttachment.setCommentId(commentId);
            postsAttachment.setResourceId(e);
            forumAttachmentExtMapper.insertSelective(postsAttachment);
        });
        return true;
    }

    public List<ForumAttachment> selectPostsAttachment(Integer postsID) {
        ForumAttachmentExample example = new ForumAttachmentExample();
        ForumAttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andFbIdEqualTo(postsID);
        criteria.andIsDeleteEqualTo(0);
        return forumAttachmentExtMapper.selectByExample(example);
    }

    public boolean deletePostsAttachment(Integer id) {
        ForumAttachment postsAttachment = new ForumAttachment();
        postsAttachment.setIsDelete(1);

        ForumAttachmentExample example = new ForumAttachmentExample();
        ForumAttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return forumAttachmentExtMapper.updateByExampleSelective(postsAttachment, example) > 0;
    }

    public boolean deletePostsAttachmentByCId(Integer commentID) {
        ForumAttachment postsAttachment = new ForumAttachment();
        postsAttachment.setIsDelete(1);

        ForumAttachmentExample example = new ForumAttachmentExample();
        ForumAttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andCommentIdEqualTo(commentID);
        return forumAttachmentExtMapper.updateByExampleSelective(postsAttachment, example) > 0;
    }

    public boolean update(ForumAttachment attachment) {
        return forumAttachmentExtMapper.updateByPrimaryKeySelective(attachment) > 0;
    }
}
