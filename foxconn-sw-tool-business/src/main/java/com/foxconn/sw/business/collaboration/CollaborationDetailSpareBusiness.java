package com.foxconn.sw.business.collaboration;

import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.entity.SwCollaborationDetailSpare;
import com.foxconn.sw.data.entity.SwCollaborationDetailSpareExample;
import com.foxconn.sw.data.mapper.extension.collaboration.SwCollaborationDetailSpareExtensionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Component
public class CollaborationDetailSpareBusiness {
    private static final Logger logger = LoggerFactory.getLogger(CollaborationDetailSpareBusiness.class);

    @Autowired
    SwCollaborationDetailSpareExtensionMapper detailSpareExtensionMapper;

    public Long updateOrInsert(SwCollaborationDetailSpare detailSpare) {
        if (Objects.isNull(detailSpare.getId())) {
            detailSpareExtensionMapper.insertSelective(detailSpare);
            return detailSpare.getId();
        } else {
            int effectCount = detailSpareExtensionMapper.updateByPrimaryKeySelective(detailSpare);
            return Long.valueOf(effectCount);
        }
    }

    public SwCollaborationDetailSpare getCollaborationDetail(Long detailID) {
        SwCollaborationDetailSpareExample example = new SwCollaborationDetailSpareExample();
        SwCollaborationDetailSpareExample.Criteria criteria = example.createCriteria();
        criteria.andDetailIdEqualTo(detailID);
        criteria.andOperatorEqualTo(RequestContext.getEmployeeNo());
        criteria.andIsDeleteEqualTo(NumberConstants.ZERO);
        List<SwCollaborationDetailSpare> spareList = detailSpareExtensionMapper.selectByExample(example);
        return CollectionUtils.isEmpty(spareList) ? null : spareList.get(0);
    }

    public List<SwCollaborationDetailSpare> getCollaborationDetails(Integer taskID) {
        SwCollaborationDetailSpareExample example = new SwCollaborationDetailSpareExample();
        SwCollaborationDetailSpareExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskID);
        criteria.andOperatorEqualTo(RequestContext.getEmployeeNo());
        criteria.andIsDeleteEqualTo(NumberConstants.ZERO);
        List<SwCollaborationDetailSpare> spareList = detailSpareExtensionMapper.selectByExample(example);
        return spareList;
    }
}
