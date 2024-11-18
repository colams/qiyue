package com.foxconn.sw.business.collaboration;

import com.foxconn.sw.data.entity.SwCollaborationDetail;
import com.foxconn.sw.data.entity.SwCollaborationDetailExample;
import com.foxconn.sw.data.mapper.extension.oa.SwCollaborationDetailExtensionMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Component
public class CollaborationDetailBusiness {
    @Autowired
    SwCollaborationDetailExtensionMapper collaborationDetailMapper;

    public List<SwCollaborationDetail> queryCollaborationDetail(List<Long> scuIDs) {
        if (CollectionUtils.isEmpty(scuIDs)) {
            return Lists.newArrayList();
        }
        SwCollaborationDetailExample example = new SwCollaborationDetailExample();
        SwCollaborationDetailExample.Criteria criteria = example.createCriteria();
        criteria.andScuIdIn(scuIDs);
        return collaborationDetailMapper.selectByExample(example);
    }

    public List<SwCollaborationDetail> queryCollaborationDetail(Long scuID) {
        SwCollaborationDetailExample example = new SwCollaborationDetailExample();
        SwCollaborationDetailExample.Criteria criteria = example.createCriteria();
        criteria.andScuIdEqualTo(scuID);
        return collaborationDetailMapper.selectByExample(example);
    }

    public boolean updateOrInsert(SwCollaborationDetail detail) {
        int i = 0;
        if (Objects.isNull(detail.getId())) {
            i = collaborationDetailMapper.insertSelective(detail);
        } else {
            i = collaborationDetailMapper.updateByPrimaryKeySelective(detail);
        }
        return i > 0;
    }

    public boolean updateItemValue(Integer key, String item, String value) {

        SwCollaborationDetailExample exampleSearch = new SwCollaborationDetailExample();
        SwCollaborationDetailExample.Criteria criteriaSearch = exampleSearch.createCriteria();
        criteriaSearch.andScuIdEqualTo(key.longValue());
        criteriaSearch.andItemEqualTo(item);
        List<SwCollaborationDetail> detailList = collaborationDetailMapper.selectByExample(exampleSearch);
        if (CollectionUtils.isEmpty(detailList)) {
            SwCollaborationDetail detail = new SwCollaborationDetail();
            detail.setScuId(key.longValue());
            detail.setItem(item);
            detail.setItemValue(value);
            collaborationDetailMapper.insertSelective(detail);
            return true;
        }

        SwCollaborationDetail detail = new SwCollaborationDetail();
        detail.setScuId(key.longValue());
        detail.setItem(item);
        detail.setItemValue(value);

        SwCollaborationDetailExample example = new SwCollaborationDetailExample();
        SwCollaborationDetailExample.Criteria criteria = example.createCriteria();
        criteria.andScuIdEqualTo(key.longValue());
        criteria.andItemEqualTo(item);
        return collaborationDetailMapper.updateByExampleSelective(detail, example) > 0;
    }
}
