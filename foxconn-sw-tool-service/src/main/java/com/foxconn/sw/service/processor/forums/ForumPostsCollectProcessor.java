package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.forums.ForumFavoriteBusiness;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ForumPostsCollectProcessor {

    @Autowired
    ForumFavoriteBusiness forumFavoriteBusiness;


    public boolean collect(IntegerParams data) {
        return forumFavoriteBusiness.collect(data.getParams());
    }
}
