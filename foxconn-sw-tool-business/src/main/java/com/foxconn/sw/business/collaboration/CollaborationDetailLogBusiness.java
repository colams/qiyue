
package com.foxconn.sw.business.collaboration;

import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.data.entity.SwCollaborationDetail;
import com.foxconn.sw.data.entity.SwCollaborationDetailLog;
import com.foxconn.sw.data.entity.SwCollaborationDetailLogExample;
import com.foxconn.sw.data.mapper.extension.collaboration.SwCollaborationDetailLogExtensionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
                                                String newValue,
                                                String oldValue) {
        SwCollaborationDetailLog detailLog = new SwCollaborationDetailLog();
        detailLog.setDetailId(detailId);
        detailLog.setRowIndex(rowIndex);
        detailLog.setColIndex(colIndex);
        detailLog.setOperator(RequestContext.getEmployeeNo());
        detailLog.setRemark(String.format("將【%s】修改為【%s】", oldValue, newValue));
        return collaborationDetailLogExtensionMapper.insertSelective(detailLog) > 0;
    }

    public boolean insertCollaborationDetailLog(SwCollaborationDetail updateDetail, String oldValue) {
        return insertCollaborationDetailLog(updateDetail.getId(),
                updateDetail.getRowIndex(),
                updateDetail.getColIndex(),
                updateDetail.getItemValue(),
                oldValue);
    }
}
