package com.foxconn.sw.business.forums;

import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.common.utils.constanst.NumberConstants;
import com.foxconn.sw.data.entity.ForumFavorite;
import com.foxconn.sw.data.entity.ForumFavoriteExample;
import com.foxconn.sw.data.mapper.extension.forums.ForumFavoriteExtMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ForumFavoriteBusiness {

    @Autowired
    ForumFavoriteExtMapper forumFavoriteExtMapper;


    public boolean collect(Integer id) {
        ForumFavoriteExample example = new ForumFavoriteExample();
        ForumFavoriteExample.Criteria criteria = example.createCriteria();
        criteria.andOperatorEqualTo(RequestContext.getEmployeeNo());
        criteria.andPostsIdEqualTo(id);
        List<ForumFavorite> favorites = forumFavoriteExtMapper.selectByExample(example);

        ForumFavorite favorite = Optional.ofNullable(favorites)
                .orElse(Lists.newArrayList())
                .stream()
                .findFirst()
                .orElse(new ForumFavorite());

        favorite.setOperator(RequestContext.getEmployeeNo());
        favorite.setPostsId(id);
        favorite.setIsValid(NumberConstants.ZERO.equals(favorite.getIsValid()) ? 0 : 1);

        if (Objects.nonNull(favorite.getId()) && favorite.getId() > 0) {
            ForumFavorite updateFavorite = new ForumFavorite();
            updateFavorite.setId(favorite.getId());
            updateFavorite.setIsValid(favorite.getIsValid());
            return forumFavoriteExtMapper.updateByPrimaryKeySelective(favorite) > 0;
        } else {
            return forumFavoriteExtMapper.insertSelective(favorite) > 0;
        }
    }

    public Integer queryCollectionStatus(Integer postsID) {
        ForumFavoriteExample example = new ForumFavoriteExample();
        ForumFavoriteExample.Criteria criteria = example.createCriteria();
        criteria.andPostsIdEqualTo(postsID);
        criteria.andOperatorEqualTo(RequestContext.getEmployeeNo());
        List<ForumFavorite> favorites = forumFavoriteExtMapper.selectByExample(example);
        return Optional.ofNullable(favorites)
                .orElse(Lists.newArrayList())
                .stream()
                .map(e -> e.getIsValid())
                .findFirst()
                .orElse(0);
    }
}
