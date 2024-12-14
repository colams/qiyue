package com.foxconn.sw.business.forums;

import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.request.forums.PostsParams;
import com.foxconn.sw.data.entity.ForumBbs;
import com.foxconn.sw.data.entity.ForumBbsExample;
import com.foxconn.sw.data.entity.ForumPosts;
import com.foxconn.sw.data.entity.ForumPostsExample;
import com.foxconn.sw.data.mapper.extension.forums.ForumBbsExtMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ForumBbsBusiness {

    @Autowired
    ForumBbsExtMapper forumBbsExtMapper;


    public int createPosts(PostsParams data) {
        ForumBbs forumBbs = new ForumBbs();
        forumBbs.setProject(data.getProject().get(1));
        forumBbs.setTitle(data.getTitle());
        forumBbs.setAuthorNo(RequestContext.getEmployeeNo());
        forumBbsExtMapper.insertSelective(forumBbs);
        return forumBbs.getId();
    }


    public List<ForumBbs> queryPosts(Integer postsType, String words) {
        if (NumberConstants.TWO.equals(postsType)) {
            return forumBbsExtMapper.favoriteBbs(words, RequestContext.getEmployeeNo());
        }
        ForumBbsExample example = new ForumBbsExample();
        ForumBbsExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(0);

        if (NumberConstants.ONE.equals(postsType)) {
            criteria.andAuthorNoEqualTo(RequestContext.getEmployeeNo());
        }

        if (StringUtils.isNotEmpty(words)) {
            criteria.andTitleLike("%" + words + "%");
        }

        example.setOrderByClause(" create_time desc ");

        return forumBbsExtMapper.selectByExample(example);
    }

    public ForumBbs getForumPosts(Integer params) {
        return forumBbsExtMapper.selectByPrimaryKey(params);
    }

    public boolean updatePosts(ForumBbs forumBbs) {
        return forumBbsExtMapper.updateByPrimaryKeySelective(forumBbs) > 0;
    }

    public boolean delete(Integer id) {
        ForumBbs forumBbs = new ForumBbs();
        forumBbs.setIsDelete(1);

        ForumBbsExample example = new ForumBbsExample();
        ForumBbsExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        return forumBbsExtMapper.updateByExampleSelective(forumBbs, example) > 0;
    }
}
