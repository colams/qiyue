
package com.foxconn.sw.business.collaboration;

import com.foxconn.sw.data.entity.SwCollaborationDetailLog;
import com.foxconn.sw.data.entity.SwCollaborationDetailLogExample;
import com.foxconn.sw.data.mapper.extension.oa.SwCollaborationDetailLogExtensionMapper;
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

    public List<SwCollaborationDetailLog> getCollaborationDetailLog(Long detailLogId) {
        SwCollaborationDetailLogExample example = new SwCollaborationDetailLogExample();
        SwCollaborationDetailLogExample.Criteria criteria = example.createCriteria();
        criteria.andDetailIdEqualTo(detailLogId);
        return collaborationDetailLogExtensionMapper.selectByExample(example);
    }

    public List<SwCollaborationDetailLog> getCollaborationDetailLog(Integer rowIndex, Integer colIndex) {
        SwCollaborationDetailLogExample example = new SwCollaborationDetailLogExample();
        SwCollaborationDetailLogExample.Criteria criteria = example.createCriteria();
        criteria.andRowIndexEqualTo(rowIndex);
        criteria.andColIndexEqualTo(colIndex);
        return collaborationDetailLogExtensionMapper.selectByExample(example);
    }


}
