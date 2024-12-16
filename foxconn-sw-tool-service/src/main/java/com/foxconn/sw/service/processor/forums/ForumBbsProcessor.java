package com.foxconn.sw.service.processor.forums;

import com.foxconn.sw.business.forums.ForumBbsBusiness;
import com.foxconn.sw.business.forums.ForumBbsCommentBusiness;
import com.foxconn.sw.business.forums.ForumCommentBusiness;
import com.foxconn.sw.business.forums.ForumPostsBusiness;
import com.foxconn.sw.data.dto.Response;
import com.foxconn.sw.data.entity.ForumPosts;
import com.foxconn.sw.service.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ForumBbsProcessor {

    @Autowired
    ForumPostsBusiness postsBusiness;
    @Autowired
    ForumCommentBusiness forumCommentBusiness;
    @Autowired
    ForumBbsBusiness bbsBusiness;
    @Autowired
    ForumBbsCommentBusiness bbsCommentBusiness;

    public Response refresh() {
        List<ForumPosts> postsList = postsBusiness.selectAll();
        return ResponseUtils.success("123456");
    }
}
