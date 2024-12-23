package com.foxconn.sw.business.group;

import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.request.enums.AgreeStatusEnums;
import com.foxconn.sw.data.entity.SwCustomGroupOperate;
import com.foxconn.sw.data.entity.SwCustomGroupOperateExample;
import com.foxconn.sw.data.mapper.extension.group.SwCustomGroupOperateExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SwCustomGroupOperateBusiness {

    @Autowired
    SwCustomGroupOperateExtMapper customGroupOperateExtMapper;

    public boolean applyJoin(Integer groupId, String remark) {
        SwCustomGroupOperate group = new SwCustomGroupOperate();
        group.setCustomGroupId(groupId);
        group.setOperator(RequestContext.getEmployeeNo());
        group.setOperateType("apply");
        group.setRemark(remark);
        customGroupOperateExtMapper.insertSelective(group);
        return group.getId() > 0;
    }


    public boolean processApply(Integer applyID, AgreeStatusEnums agree) {
        SwCustomGroupOperate apply = new SwCustomGroupOperate();
        apply.setStatus(agree.getCode());
        SwCustomGroupOperateExample example = new SwCustomGroupOperateExample();
        SwCustomGroupOperateExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(applyID);
        return customGroupOperateExtMapper.updateByExampleSelective(apply, example) > 0;
    }

    public boolean disbandGroup(IntegerParams data, String remark) {
        SwCustomGroupOperate group = new SwCustomGroupOperate();
        group.setCustomGroupId(data.getParams());
        group.setOperator(RequestContext.getEmployeeNo());
        group.setOperateType("disband");
        group.setRemark(remark);
        customGroupOperateExtMapper.insertSelective(group);
        return group.getId() > 0;
    }

    public List<SwCustomGroupOperate> getUnReadOperateList() {
        SwCustomGroupOperateExample example = new SwCustomGroupOperateExample();
        SwCustomGroupOperateExample.Criteria criteria = example.createCriteria();
        criteria.andOperatorEqualTo(RequestContext.getEmployeeNo());

        criteria.andIsReadEqualTo(NumberConstants.ZERO);
        return customGroupOperateExtMapper.selectByExample(example);
    }

    public List<SwCustomGroupOperate> getUnDealOperateList() {
        SwCustomGroupOperateExample example = new SwCustomGroupOperateExample();
        SwCustomGroupOperateExample.Criteria criteria = example.createCriteria();
        criteria.andOperatorEqualTo(RequestContext.getEmployeeNo());
        criteria.andStatusEqualTo(NumberConstants.ZERO);
        return customGroupOperateExtMapper.selectByExample(example);
    }


    public List<SwCustomGroupOperate> getOperateList() {
        SwCustomGroupOperateExample example = new SwCustomGroupOperateExample();
        SwCustomGroupOperateExample.Criteria criteria = example.createCriteria();
        criteria.andOperatorEqualTo(RequestContext.getEmployeeNo());
        criteria.andIsReadEqualTo(NumberConstants.ZERO);

        SwCustomGroupOperateExample.Criteria or = example.or();
        or.andOperatorEqualTo(RequestContext.getEmployeeNo());
        or.andStatusEqualTo(NumberConstants.ZERO);
        return customGroupOperateExtMapper.selectByExample(example);
    }

    public SwCustomGroupOperate selectById(Integer applyID) {
        return customGroupOperateExtMapper.selectByPrimaryKey(applyID);
    }

    public List<SwCustomGroupOperate> hasGroupOperateInfo(Integer id) {

        SwCustomGroupOperateExample example = new SwCustomGroupOperateExample();
        SwCustomGroupOperateExample.Criteria criteria = example.createCriteria();
        criteria.andOperatorEqualTo(RequestContext.getEmployeeNo());
        criteria.andCustomGroupIdEqualTo(id);
        List<SwCustomGroupOperate> operates = customGroupOperateExtMapper.selectByExample(example);
        return operates;

    }
}
