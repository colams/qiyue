package com.foxconn.sw.business.group;

import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.dto.request.group.UpdateGroupParams;
import com.foxconn.sw.data.entity.SwCustomGroup;
import com.foxconn.sw.data.entity.SwCustomGroupExample;
import com.foxconn.sw.data.mapper.extension.group.SwCustomGroupExtMapper;
import com.foxconn.sw.data.mapper.extension.group.SwCustomGroupMemberExtMapper;
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
        return customGroupExtMapper.selectManagerGroups(RequestContext.getEmployeeNo(), keywords);
    }

    /**
     * 查詢收藏群組
     *
     * @param keywords
     * @return
     */
    public List<SwCustomGroup> listCollectGroup(String keywords) {
        return customGroupExtMapper.listCollectGroup(keywords);
    }

    /**
     * 查詢公開群組
     *
     * @param keywords
     * @return
     */
    public List<SwCustomGroup> listPublicGroup(String keywords) {
        SwCustomGroupExample example = new SwCustomGroupExample();
        SwCustomGroupExample.Criteria criteria = example.createCriteria();
        criteria.andIsPrivateEqualTo(NumberConstants.ZERO);
        criteria.andIsDeleteEqualTo(NumberConstants.ZERO);
        if (StringUtils.isNotEmpty(keywords)) {
            criteria.andNameLike(String.format("%%%s%%", keywords));
        }
        return customGroupExtMapper.selectByExample(example);
    }

    public boolean disband(SwCustomGroup group) {
        SwCustomGroup updateGroup = new SwCustomGroup();
        updateGroup.setIsDelete(NumberConstants.ONE);
        updateGroup.setId(group.getId());
        return customGroupExtMapper.updateByPrimaryKeySelective(updateGroup) > 0;
    }

    public SwCustomGroup getCustomGroup(Integer groupID) {
        return customGroupExtMapper.selectByPrimaryKey(groupID);
    }

    public boolean updateGroup(UpdateGroupParams data) {
        SwCustomGroup updateGroup = new SwCustomGroup();
        updateGroup.setName(data.getName());
        updateGroup.setIsPrivate(data.getAccessLevel().getCode());
        updateGroup.setDescription(data.getDescription());
        updateGroup.setId(data.getGroupID());
        return customGroupExtMapper.updateByPrimaryKeySelective(updateGroup) > 0;
    }
}
