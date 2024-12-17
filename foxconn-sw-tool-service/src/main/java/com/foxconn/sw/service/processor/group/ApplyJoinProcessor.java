package com.foxconn.sw.service.processor.group;

import com.foxconn.sw.business.group.SwCustomGroupFavoriteBusiness;
import com.foxconn.sw.business.group.SwCustomGroupMemberBusiness;
import com.foxconn.sw.business.group.SwCustomGroupOperateBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.data.dto.entity.group.CustomOperateVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.enums.AgreeStatusEnums;
import com.foxconn.sw.data.dto.request.enums.GroupMemberTypeEnums;
import com.foxconn.sw.data.dto.request.group.ApplyJoinGroupParams;
import com.foxconn.sw.data.dto.request.group.ProcessApplyParams;
import com.foxconn.sw.data.entity.SwCustomGroupFavorite;
import com.foxconn.sw.data.entity.SwCustomGroupMember;
import com.foxconn.sw.data.entity.SwCustomGroupOperate;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApplyJoinProcessor {


    @Autowired
    SwCustomGroupOperateBusiness customGroupOperateBusiness;
    @Autowired
    SwCustomGroupFavoriteBusiness customGroupFavoriteBusiness;
    @Autowired
    EmployeeUtils employeeUtils;
    @Autowired
    SwCustomGroupMemberBusiness groupMemberBusiness;

    public boolean applyJoin(ApplyJoinGroupParams data) {
        return customGroupOperateBusiness.applyJoin(data.getGroupId(), data.getRemark());
    }

    public boolean process(ProcessApplyParams data) {
        if (data.getAgree().equals(AgreeStatusEnums.Agree)) {
            SwCustomGroupOperate operate = customGroupOperateBusiness.selectById(data.getApplyID());
            SwCustomGroupMember member = new SwCustomGroupMember();
            member.setCustomGroupId(operate.getCustomGroupId());
            member.setMember(operate.getOperator());
            member.setMemberType(GroupMemberTypeEnums.Member.getCode());
            groupMemberBusiness.insert(member);
        }
        return customGroupOperateBusiness.processApply(data.getApplyID(), data.getAgree());
    }

    public boolean collect(IntegerParams data) {
        SwCustomGroupFavorite favorite = customGroupFavoriteBusiness.getFavoriteGroup(data.getParams());
        favorite.setIsDelete(NumberConstants.ZERO.equals(favorite.getIsDelete()) ? 1 : 0);
        return customGroupFavoriteBusiness.insertOrUpdate(favorite);
    }

    public List<CustomOperateVo> operateList() {
        List<SwCustomGroupOperate> list = customGroupOperateBusiness.getOperateList();

        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.stream().map(e ->
                toCustomOperateVo(e)
        ).collect(Collectors.toList());
    }

    private CustomOperateVo toCustomOperateVo(SwCustomGroupOperate operate) {
        CustomOperateVo vo = new CustomOperateVo();
        vo.setId(operate.getId());
        vo.setEmployeeVo(employeeUtils.mapEmployee(operate.getOperator()));
        vo.setRemark(operate.getRemark());
        vo.setOperateType(operate.getOperateType());
        return vo;
    }
}


