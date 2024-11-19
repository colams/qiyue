package com.foxconn.sw.business.forums;

import com.foxconn.sw.business.context.RequestContext;
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
        criteria.andAuthorNoEqualTo(RequestContext.getEmployeeNo());
        criteria.andPostsIdEqualTo(id);
        List<ForumFavorite> favorites = forumFavoriteExtMapper.selectByExample(example);

        ForumFavorite favorite = Optional.ofNullable(favorites)
                .orElse(Lists.newArrayList())
                .stream()
                .findFirst()
                .orElse(new ForumFavorite());

        favorite.setAuthorNo(RequestContext.getEmployeeNo());
        favorite.setPostsId(id);
        favorite.setIsInvalid(NumberConstants.ONE.equals(favorite.getIsInvalid()) ? 0 : 1);

        if (Objects.nonNull(favorite.getId()) && favorite.getId() > 0) {
            ForumFavorite updateFavorite = new ForumFavorite();
            updateFavorite.setId(favorite.getId());
            updateFavorite.setIsInvalid(favorite.getIsInvalid());
            return forumFavoriteExtMapper.updateByPrimaryKeySelective(favorite) > 0;
        } else {
            return forumFavoriteExtMapper.insertSelective(favorite) > 0;
        }
    }
}
