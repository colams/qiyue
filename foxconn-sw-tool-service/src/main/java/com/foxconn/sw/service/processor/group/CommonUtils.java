package com.foxconn.sw.service.processor.group;

import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.group.ApplyJoinVo;
import com.foxconn.sw.data.dto.entity.group.GroupBriefVo;
import com.foxconn.sw.data.dto.entity.oa.InfoColorVo;
import com.foxconn.sw.data.entity.SwCustomGroup;
import com.foxconn.sw.data.entity.SwCustomGroupFavorite;
import com.foxconn.sw.data.entity.SwCustomGroupMember;

import java.util.List;
import java.util.Objects;

public class CommonUtils {

    public static InfoColorVo initGroupType(Integer isPrivate) {
        InfoColorVo infoColorVo = new InfoColorVo();
        infoColorVo.setCode(isPrivate);
        infoColorVo.setDescription(isPrivate == 1 ? "私有" : "公開");
        return infoColorVo;
    }

    public static GroupBriefVo map(SwCustomGroup group, EmployeeVo owner) {
        GroupBriefVo vo = new GroupBriefVo();
        vo.setId(group.getId());
        vo.setName(group.getName());
        vo.setOwner(owner);
        vo.setGroupType(CommonUtils.initGroupType(group.getIsPrivate()));
        vo.setCreateTime(DateTimeUtils.formatYMD(group.getCreateTime()));
        vo.setDescription(group.getDescription());
        return vo;
    }

    public static GroupBriefVo map2(SwCustomGroup group,
                                    List<SwCustomGroupMember> joinGroups,
                                    List<SwCustomGroupFavorite> favoriteGroups,
                                    EmployeeVo owner) {
        GroupBriefVo vo = new GroupBriefVo();
        vo.setId(group.getId());
        vo.setName(group.getName());
        vo.setOwner(owner);
        vo.setGroupType(CommonUtils.initGroupType(group.getIsPrivate()));
        vo.setCreateTime(DateTimeUtils.formatYMD(group.getCreateTime()));
        vo.setDescription(group.getDescription());
        vo.setJoinVo(initJoinVo(group, joinGroups));
        if (Objects.nonNull(favoriteGroups)) {
            vo.setCollectStatus(favoriteGroups.stream().anyMatch(e -> e.getCustomGroupId().equals(group.getId())));
        } else {
            vo.setCollectStatus(true);
        }
        return vo;
    }

    private static ApplyJoinVo initJoinVo(SwCustomGroup group, List<SwCustomGroupMember> joinGroups) {
        boolean isJoin = joinGroups.stream().anyMatch(e -> e.getCustomGroupId().equals(group.getId()));
        boolean isOwner = group.getOwner().equalsIgnoreCase(RequestContext.getEmployeeNo());

        ApplyJoinVo vo = new ApplyJoinVo();
        vo.setCanJoin(!isJoin);
        if (isOwner) {
            vo.setStatus(2);
            vo.setDescription("群主");
        } else if (isJoin) {
            vo.setStatus(1);
            vo.setDescription("已加入");
        } else {
            vo.setStatus(0);
            vo.setDescription("申請");
        }
        return vo;
    }
}
