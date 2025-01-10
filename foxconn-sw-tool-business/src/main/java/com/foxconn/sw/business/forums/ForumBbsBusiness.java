package com.foxconn.sw.business.forums;

import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.enums.PostsCategoryEnums;
import com.foxconn.sw.data.dto.request.forums.PostsParams;
import com.foxconn.sw.data.entity.ForumBbs;
import com.foxconn.sw.data.entity.ForumBbsExample;
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

    public int createPosts(ForumBbs forumBbs) {
        forumBbsExtMapper.insertSelective(forumBbs);
        return forumBbs.getId();
    }

    public List<ForumBbs> queryPosts(PostsCategoryEnums postsType, String words, Integer pageSize, Integer currentPage) {

        Integer isAdmin = RequestContext.getEmployeeNo().equalsIgnoreCase("G1658973") ? 1 : 0;
        int start = (currentPage - 1) * pageSize;
        if (PostsCategoryEnums.CollectPosts.equals(postsType)) {
            return forumBbsExtMapper.favoriteBbs(isAdmin, words, RequestContext.getEmployeeNo(), start, pageSize);
        }

        String owner = PostsCategoryEnums.MyPosts.equals(postsType) ? RequestContext.getEmployeeNo() : null;
        Integer hiddenStatus = PostsCategoryEnums.Hidden.equals(postsType) ? 1 : 0;
        String title = StringUtils.isNotEmpty(words) ? "%" + words + "%" : null;

        return forumBbsExtMapper.selectByKeyWords(isAdmin, RequestContext.getEmployeeNo(), owner, title, hiddenStatus, start, pageSize);
    }

    public Long getPostsCount(PostsCategoryEnums postsType, String words) {
        if (PostsCategoryEnums.CollectPosts.equals(postsType)) {
            return forumBbsExtMapper.getFavoriteBbsCount(words, RequestContext.getEmployeeNo());
        }
        String employee = "";
        if (PostsCategoryEnums.MyPosts.equals(postsType)) {
            employee = RequestContext.getEmployeeNo();
        }
        return forumBbsExtMapper.selectBbsCount(words, employee);
    }

    public ForumBbs getForumBbs(Integer params) {
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
