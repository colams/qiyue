package com.foxconn.sw.service.processor.group;

import com.foxconn.sw.common.utils.DateTimeUtils;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.group.GroupBriefVo;
import com.foxconn.sw.data.dto.entity.oa.InfoColorVo;
import com.foxconn.sw.data.entity.SwCustomGroup;

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
        vo.setCollectStatus(false);
//        vo.setCanJoin(false);
        return vo;
    }

}
