package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.forums.ForumPostsAttachmentBusiness;
import com.foxconn.sw.business.forums.ForumPostsBusiness;
import com.foxconn.sw.data.dto.request.forums.UpdateAttachParams;
import com.foxconn.sw.data.entity.ForumPostsAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Component
public class UpdatePostsProcessor {

    @Autowired
    ForumPostsBusiness forumPostsBusiness;
    @Autowired
    ForumPostsAttachmentBusiness postsAttachmentBusiness;

    public boolean updateAttach(UpdateAttachParams data) {
        if (Objects.isNull(data.getId()) || CollectionUtils.isEmpty(data.getResourceIds())) {
            return false;
        }

        List<ForumPostsAttachment> attachments = postsAttachmentBusiness.selectPostsAttachment(data.getId());

        if (CollectionUtils.isEmpty(attachments)) {
            return false;
        }

        attachments.forEach(e -> {
            boolean hasAttach = data.getResourceIds().stream().anyMatch(f -> f.equals(e.getResourceId()));
            if (hasAttach) {
                postsAttachmentBusiness.deletePostsAttachment(e.getId());
            }
        });
        return true;
    }
}
