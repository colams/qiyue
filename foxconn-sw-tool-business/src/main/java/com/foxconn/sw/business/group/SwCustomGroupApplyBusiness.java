package com.foxconn.sw.business.group;

import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.request.enums.AgreeStatusEnums;
import com.foxconn.sw.data.entity.SwCustomGroupApply;
import com.foxconn.sw.data.entity.SwCustomGroupApplyExample;
import com.foxconn.sw.data.mapper.extension.group.SwCustomGroupApplyExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SwCustomGroupApplyBusiness {

    @Autowired
    SwCustomGroupApplyExtMapper customGroupApplyExtMapper;

    public boolean applyJoin(Integer groupId, String remark) {
        SwCustomGroupApply group = new SwCustomGroupApply();
        group.setCustomGroupId(groupId);
        group.setApplyEmployeeNo(RequestContext.getEmployeeNo());
        group.setRemark(remark);
        customGroupApplyExtMapper.insertSelective(group);
        return group.getId() > 0;
    }


    public boolean processApply(Integer applyID, AgreeStatusEnums agree) {
        SwCustomGroupApply apply = new SwCustomGroupApply();
        apply.setStatus(agree.getCode());
        SwCustomGroupApplyExample example = new SwCustomGroupApplyExample();
        SwCustomGroupApplyExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(applyID);
        return customGroupApplyExtMapper.updateByExampleSelective(apply, example) > 0;

    }
}
