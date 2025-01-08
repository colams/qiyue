
package com.foxconn.sw.service.processor.collaboration;

import com.foxconn.sw.business.collaboration.CollaborationDetailLogBusiness;
import com.foxconn.sw.data.dto.entity.collaboration.CollaborationDetailLogVo;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationDetailLogParams;
import com.foxconn.sw.data.entity.SwCollaborationDetailLog;
import com.foxconn.sw.service.processor.utils.EmployeeUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CollaborationUpdateHistoryProcessor {

    @Autowired
    CollaborationDetailLogBusiness collaborationDetailLogBusiness;
    @Autowired
    EmployeeUtils employeeUtils;

    public List<CollaborationDetailLogVo> log(CollaborationDetailLogParams data) {
        List<CollaborationDetailLogVo> detailLogVoList;
        if (Objects.nonNull(data.getDetailID()) && data.getDetailID() > 0) {
            detailLogVoList = logByDetailID(data.getDetailID());
        } else {
            detailLogVoList = logByRowCol(data.getRowIndex(), data.getColIndex());
        }
        return detailLogVoList;
    }

    private List<CollaborationDetailLogVo> logByRowCol(Integer rowIndex, Integer colIndex) {
        List<SwCollaborationDetailLog> logs = collaborationDetailLogBusiness.getCollaborationDetailLog(rowIndex, colIndex);
        if (CollectionUtils.isEmpty(logs)) {
            return Lists.newArrayList();
        }
        return logs.stream().map(e -> map2Vo(e)).collect(Collectors.toList());
    }

    private List<CollaborationDetailLogVo> logByDetailID(Long detailID) {
        List<SwCollaborationDetailLog> logs = collaborationDetailLogBusiness.getCollaborationDetailLog(detailID);
        if (CollectionUtils.isEmpty(logs)) {
            return Lists.newArrayList();
        }
        return logs.stream().map(e -> map2Vo(e)).collect(Collectors.toList());
    }

    private CollaborationDetailLogVo map2Vo(SwCollaborationDetailLog e) {
        CollaborationDetailLogVo vo = new CollaborationDetailLogVo();
        vo.setId(e.getId());
        vo.setEmployeeVo(employeeUtils.mapEmployee(e.getOperator()));
        vo.setRemark(e.getRemark());
        vo.setCreateTime(e.getCreateTime());
        return vo;
    }
}
