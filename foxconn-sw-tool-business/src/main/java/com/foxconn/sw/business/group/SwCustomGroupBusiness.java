package com.foxconn.sw.business.group;

import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.entity.group.GroupBriefVo;
import com.foxconn.sw.data.entity.SwCustomGroup;
import com.foxconn.sw.data.entity.SwCustomGroupExample;
import com.foxconn.sw.data.mapper.extension.group.SwCustomGroupExtMapper;
import com.foxconn.sw.data.mapper.extension.group.SwCustomGroupMemberExtMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SwCustomGroupBusiness {

    @Autowired
    SwCustomGroupExtMapper customGroupExtMapper;
    @Autowired
    SwCustomGroupMemberExtMapper customGroupMemberExtMapper;

    public SwCustomGroup createGroup(String groupName, String owner, Integer isPrivate, String description) {
        SwCustomGroup group = new SwCustomGroup();
        group.setName(groupName);
        group.setOwner(owner);
        group.setIsPrivate(isPrivate);
        group.setDescription(description);
        group.setCreateTime(LocalDateTime.now());
        customGroupExtMapper.insertSelective(group);
        return group.getId() > 0 ? group : null;
    }


    /**
     * 查詢我的群組
     *
     * @param keywords
     * @return
     */
    public List<SwCustomGroup> listOwnerGroup(String keywords) {
        SwCustomGroupExample example = new SwCustomGroupExample();
        SwCustomGroupExample.Criteria criteria = example.createCriteria();
        criteria.andOwnerEqualTo(RequestContext.getEmployeeNo());
        criteria.andIsDeleteEqualTo(NumberConstants.ZERO);
        if (StringUtils.isNotEmpty(keywords)) {
            criteria.andNameLike(String.format("%%%s%%", keywords));
        }
        return customGroupExtMapper.selectByExample(example);
    }

    /**
     * 查詢收藏群組
     *
     * @param keywords
     * @return
     */
    public List<GroupBriefVo> listCollectGroup(String keywords) {
        return Lists.newArrayList();
    }

    /**
     * 查詢公開群組
     *
     * @param keywords
     * @return
     */
    public List<GroupBriefVo> listPublicGroup(String keywords) {
        return Lists.newArrayList();
    }
}
