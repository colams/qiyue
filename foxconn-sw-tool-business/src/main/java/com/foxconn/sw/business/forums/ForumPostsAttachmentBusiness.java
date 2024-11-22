package com.foxconn.sw.business.forums;

import com.foxconn.sw.data.entity.ForumPostsAttachment;
import com.foxconn.sw.data.entity.ForumPostsAttachmentExample;
import com.foxconn.sw.data.mapper.extension.forums.ForumPostsAttachmentExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ForumPostsAttachmentBusiness {

    @Autowired
    ForumPostsAttachmentExtMapper postsAttachmentExtMapper;

    public boolean insertPostsAttachment(Integer postsID, List<Integer> resourceIDs) {
        resourceIDs.forEach(e -> {
            ForumPostsAttachment postsAttachment = new ForumPostsAttachment();
            postsAttachment.setPostsId(postsID);
            postsAttachment.setResourceId(e);
            postsAttachmentExtMapper.insertSelective(postsAttachment);
        });
        return true;
    }

    public List<ForumPostsAttachment> selectPostsAttachment(Integer postsID) {
        ForumPostsAttachmentExample example = new ForumPostsAttachmentExample();
        ForumPostsAttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andPostsIdEqualTo(postsID);
        criteria.andIsDeleteEqualTo(0);
        return postsAttachmentExtMapper.selectByExample(example);
    }

    public boolean deletePostsAttachment(Integer id) {
        ForumPostsAttachment postsAttachment = new ForumPostsAttachment();
        postsAttachment.setIsDelete(1);

        ForumPostsAttachmentExample example = new ForumPostsAttachmentExample();
        ForumPostsAttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return postsAttachmentExtMapper.updateByExampleSelective(postsAttachment, example) > 0;
    }

}
