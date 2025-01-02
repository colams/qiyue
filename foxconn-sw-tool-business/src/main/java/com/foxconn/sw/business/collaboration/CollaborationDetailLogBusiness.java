
package com.foxconn.sw.business.collaboration;

import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.entity.SwCollaborationDetail;
import com.foxconn.sw.data.entity.SwCollaborationDetailLog;
import com.foxconn.sw.data.entity.SwCollaborationDetailLogExample;
import com.foxconn.sw.data.mapper.extension.oa.SwCollaborationDetailLogExtensionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class CollaborationDetailLogBusiness {
    private static final Logger logger = LoggerFactory.getLogger(CollaborationDetailLogBusiness.class);
    @Autowired
    SwCollaborationDetailLogExtensionMapper collaborationDetailLogExtensionMapper;

    public List<SwCollaborationDetailLog> getCollaborationDetailLog(Long detailId) {
        SwCollaborationDetailLogExample example = new SwCollaborationDetailLogExample();
        SwCollaborationDetailLogExample.Criteria criteria = example.createCriteria();
        criteria.andDetailIdEqualTo(detailId);
        return collaborationDetailLogExtensionMapper.selectByExample(example);
    }

    public List<SwCollaborationDetailLog> getCollaborationDetailLog(Integer rowIndex, Integer colIndex) {
        SwCollaborationDetailLogExample example = new SwCollaborationDetailLogExample();
        SwCollaborationDetailLogExample.Criteria criteria = example.createCriteria();
        criteria.andRowIndexEqualTo(rowIndex);
        criteria.andColIndexEqualTo(colIndex);
        return collaborationDetailLogExtensionMapper.selectByExample(example);
    }

    public boolean insertCollaborationDetailLog(Long detailId,
                                                Integer rowIndex,
                                                Integer colIndex,
                                                String remark) {
        SwCollaborationDetailLog detailLog = new SwCollaborationDetailLog();
        detailLog.setDetailId(detailId);
        detailLog.setRowIndex(rowIndex);
        detailLog.setColIndex(colIndex);
        detailLog.setOperator(RequestContext.getEmployeeNo());
        detailLog.setRemark(remark);
        return collaborationDetailLogExtensionMapper.insertSelective(detailLog) > 0;
    }

    public boolean insertCollaborationDetailLog(List<SwCollaborationDetail> updateDetails) {
        if (CollectionUtils.isEmpty(updateDetails)) {
            return true;
        }
        try {
            updateDetails.forEach(e -> {
                insertCollaborationDetailLog(e.getId(), e.getRowIndex(), e.getColIndex(), e.getItemValue());
            });
        } catch (Exception e) {
            logger.error("insertCollaborationDetailLog", e);
        }
        return true;
    }

    public boolean insertCollaborationDetailLog(SwCollaborationDetail updateDetail) {
        return insertCollaborationDetailLog(updateDetail.getId(),
                updateDetail.getRowIndex(),
                updateDetail.getColIndex(),
                updateDetail.getItemValue());
    }
}
