package com.foxconn.sw.business.forums;

import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.request.enums.PostsCategoryEnums;
import com.foxconn.sw.data.dto.request.forums.PostsParams;
import com.foxconn.sw.data.entity.ForumPosts;
import com.foxconn.sw.data.entity.ForumPostsExample;
import com.foxconn.sw.data.mapper.extension.forums.ForumPostsExtMapper;
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
        forumPosts.setProject(data.getProject().get(1));
        forumPosts.setTitle(data.getTitle());
        forumPosts.setAuthorNo(RequestContext.getEmployeeNo());
        forumPosts.setPurview(data.getPurview());
        forumPosts.setDescription(data.getContent());
        forumPostsExtMapper.insertSelective(forumPosts);
        return forumPosts.getId();
    }


    public List<ForumPosts> queryPosts(PostsCategoryEnums postsType, String words, Integer pageSize, Integer currentPage) {
        int start = (currentPage - 1) * pageSize;
        if (NumberConstants.TWO.equals(postsType.getCode())) {
            return forumPostsExtMapper.favoritePosts(words, RequestContext.getEmployeeNo(), start, pageSize);
        }
        ForumPostsExample example = new ForumPostsExample();
        ForumPostsExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0);

        if (NumberConstants.ONE.equals(postsType.getCode())) {
            criteria.andAuthorNoEqualTo(RequestContext.getEmployeeNo());
        }

        if (StringUtils.isNotEmpty(words)) {
            criteria.andTitleLike("%" + words + "%");
        }

        example.setOrderByClause(" create_time desc ");

        return forumPostsExtMapper.selectByExampleWithBLOBs(example);
    }

    public ForumPosts getForumPosts(Integer params) {
        return forumPostsExtMapper.selectByPrimaryKey(params);
    }

    public boolean updatePosts(ForumPosts updatePosts) {
        return forumPostsExtMapper.updateByPrimaryKeySelective(updatePosts) > 0;
    }

    public boolean delete(Integer id) {
        ForumPosts forumPosts = new ForumPosts();
        forumPosts.setIsDelete(1);

        ForumPostsExample example = new ForumPostsExample();
        ForumPostsExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return forumPostsExtMapper.updateByExampleSelective(forumPosts, example) > 0;
    }
}
