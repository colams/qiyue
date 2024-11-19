package com.foxconn.sw.business.forums;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.common.utils.JsonUtils;
import com.foxconn.sw.common.utils.constanst.NumberConstants;
import com.foxconn.sw.data.dto.request.forums.PostsParams;
import com.foxconn.sw.data.entity.ForumPosts;
import com.foxconn.sw.data.entity.ForumPostsExample;
import com.foxconn.sw.data.mapper.extension.forums.ForumPostsExtMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ForumPostsBusiness {

    @Autowired
    ForumPostsExtMapper forumPostsExtMapper;


    public int createPosts(PostsParams data) {
        ForumPosts forumPosts = new ForumPosts();
        forumPosts.setTitle(data.getTitle());
        forumPosts.setAuthorNo(RequestContext.getEmployeeNo());
        forumPosts.setPurview(data.getPurview());
        forumPosts.setDescription(data.getContent());
        forumPosts.setResourceIds(JsonUtils.serialize(data.getResources()));
        forumPostsExtMapper.insertSelective(forumPosts);
        return forumPosts.getId();
    }


    public List<ForumPosts> queryPosts(Integer postsType, String words) {
        if (NumberConstants.TWO.equals(postsType)) {
            // todo
            return Lists.newArrayList();
        }
        ForumPostsExample example = new ForumPostsExample();
        ForumPostsExample.Criteria criteria = example.createCriteria();

        if (NumberConstants.ONE.equals(postsType)) {
            criteria.andAuthorNoEqualTo(RequestContext.getEmployeeNo());
        }

        if (StringUtils.isNotEmpty(words)) {
            criteria.andTitleLike("%" + words + "%");
        }

        return forumPostsExtMapper.selectByExampleWithBLOBs(example);
    }

    public ForumPosts getForumPosts(Integer params) {
        return forumPostsExtMapper.selectByPrimaryKey(params);
    }

    public boolean updatePosts(ForumPosts updatePosts) {
        return forumPostsExtMapper.updateByPrimaryKeySelective(updatePosts) > 0;
    }
}
