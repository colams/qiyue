package com.foxconn.sw.business.group;

import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.entity.SwCustomGroupFavorite;
import com.foxconn.sw.data.entity.SwCustomGroupFavoriteExample;
import com.foxconn.sw.data.mapper.extension.group.SwCustomGroupFavoriteExtMapper;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class SwCustomGroupFavoriteBusiness {

    @Autowired
    SwCustomGroupFavoriteExtMapper customGroupFavoriteExtMapper;
    @Autowired
    SqlSessionFactory sqlSessionFactory;


    /**
     * 查詢公開群組
     *
     * @return
     */
    public List<SwCustomGroupFavorite> listFavoriteGroup() {
        SwCustomGroupFavoriteExample example = new SwCustomGroupFavoriteExample();
        SwCustomGroupFavoriteExample.Criteria criteria = example.createCriteria();
        criteria.andOperatorEqualTo(RequestContext.getEmployeeNo());
        criteria.andIsDeleteEqualTo(NumberConstants.ZERO);
        return customGroupFavoriteExtMapper.selectByExample(example);
    }

    public SwCustomGroupFavorite getFavoriteGroup(Integer params) {
        SwCustomGroupFavoriteExample example = new SwCustomGroupFavoriteExample();
        SwCustomGroupFavoriteExample.Criteria criteria = example.createCriteria();
        criteria.andOperatorEqualTo(RequestContext.getEmployeeNo());
        criteria.andCustomGroupIdEqualTo(params);
        List<SwCustomGroupFavorite> list = customGroupFavoriteExtMapper.selectByExample(example);
        SwCustomGroupFavorite favorite = Optional.ofNullable(list).orElse(Lists.newArrayList())
                .stream().findAny()
                .orElse(getDefault(params));
        return favorite;
    }

    private SwCustomGroupFavorite getDefault(Integer params) {
        SwCustomGroupFavorite favorite = new SwCustomGroupFavorite();
        favorite.setCustomGroupId(params);
        favorite.setIsDelete(1);
        favorite.setOperator(RequestContext.getEmployeeNo());
        return favorite;
    }

    public boolean insertOrUpdate(SwCustomGroupFavorite favorite) {
        int effectCount;
        if (Objects.nonNull(favorite.getId()) && favorite.getId() > 0) {
            effectCount = customGroupFavoriteExtMapper.updateByPrimaryKeySelective(favorite);
        } else {
            effectCount = customGroupFavoriteExtMapper.insertSelective(favorite);
        }
        return effectCount > 0;
    }
}
