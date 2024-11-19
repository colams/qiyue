package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.forums.ForumPostsBusiness;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.data.dto.request.forums.UpdateAttachParams;
import com.foxconn.sw.data.entity.ForumPosts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UpdatePostsProcessor {

    @Autowired
    ForumPostsBusiness forumPostsBusiness;

    public boolean updateAttach(UpdateAttachParams data) {
        ForumPosts forumPosts = forumPostsBusiness.getForumPosts(data.getId());
        if (Objects.isNull(forumPosts)) {
            return false;
        }

        List<Integer> resourceIds = JsonUtils.deserialize(forumPosts.getResourceIds(), List.class, Integer.class);

        ForumPosts updatePosts = new ForumPosts();
        updatePosts.setId(forumPosts.getId());
        updatePosts.setResourceIds(JsonUtils.serialize(resourceIds.remove(data.getResourceId())));
        return forumPostsBusiness.updatePosts(updatePosts);
    }
}
