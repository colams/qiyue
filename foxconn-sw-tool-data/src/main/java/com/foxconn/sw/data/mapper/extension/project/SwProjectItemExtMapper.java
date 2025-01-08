package com.foxconn.sw.data.mapper.extension.project;

import com.foxconn.sw.data.entity.SwProjectItem;
import com.foxconn.sw.data.mapper.auto.SwProjectItemMapper;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public interface SwProjectItemExtMapper extends SwProjectItemMapper {

    default Boolean insertOrUpdate(SwProjectItem newProjectItem) {
        int effectCount;
        if (Objects.nonNull(newProjectItem.getId()) && newProjectItem.getId() > 0) {
            effectCount = updateByPrimaryKeySelective(newProjectItem);
        } else {
            effectCount = insertSelective(newProjectItem);
        }
        return effectCount > 0;
    }
}
