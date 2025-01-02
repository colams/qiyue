package com.foxconn.sw.service.processor.collaboration;

import com.foxconn.sw.business.SwCapexSetBusiness;
import com.foxconn.sw.business.collaboration.CollaborationDetailBusiness;
import com.foxconn.sw.business.collaboration.CollaborationDetailLogBusiness;
import com.foxconn.sw.business.collaboration.CollaborationUserBusiness;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.entity.task.BriefTaskVo;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationSaveUpdateParams;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationUpdateCellParams;
import com.foxconn.sw.data.entity.SwCapexSet;
import com.foxconn.sw.data.entity.SwCollaborationDetail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.foxconn.sw.common.constanst.CapexSetConstants.Column;
import static com.foxconn.sw.common.constanst.CapexSetConstants.ROW;

@Component
public class CollaborationUpdateCellProcessor {

    @Autowired
    CollaborationDetailBusiness collaborationDetailBusiness;
    @Autowired
    CollaborationUserBusiness collaborationUserBusiness;
    @Autowired
    SwTaskBusiness taskBusiness;
    @Autowired
    SwTaskProgressBusiness taskProgressBusiness;
    @Autowired
    CollaborationDetailLogBusiness collaborationDetailLogBusiness;
    @Autowired
    SwCapexSetBusiness capexSetBusiness;

    public Boolean updateCell(CollaborationUpdateCellParams data) {
        SwCollaborationDetail collaborationDetail = collaborationDetailBusiness.selectCollaborationDetail(data.getDetailID(),
                data.getRowIndex(),
                data.getColIndex(),
                data.getItem());

        if (Objects.isNull(collaborationDetail)) {
            return false;
        }

        SwCollaborationDetail updateDetail = new SwCollaborationDetail();
        updateDetail.setId(collaborationDetail.getId());
        updateDetail.setSpareValue(data.getValue());
        return collaborationDetailBusiness.updateOrInsert(updateDetail) > 0;
    }

    public Boolean saveUpdate(CollaborationSaveUpdateParams data) {
        List<SwCollaborationDetail> collaborationDetails = collaborationDetailBusiness.selectCollaborationDetail(data.getTaskID());
        collaborationDetails.forEach(e -> {
            if (StringUtils.isNotEmpty(e.getSpareValue()) && checkPermission(data.getTaskID(), e.getRowIndex(), e.getColIndex())) {
                SwCollaborationDetail updateDetail = new SwCollaborationDetail();
                updateDetail.setId(e.getId());
                updateDetail.setSpareValue("");
                updateDetail.setRowIndex(e.getRowIndex());
                updateDetail.setColIndex(e.getRowIndex());
                updateDetail.setItemValue(e.getSpareValue());
                collaborationDetailBusiness.updateOrInsert(updateDetail);
                collaborationDetailLogBusiness.insertCollaborationDetailLog(updateDetail);
            }
        });
        return true;
    }

    public boolean checkPermission(Long taskID, Integer rowIndex, Integer colIndex) {
        List<SwCapexSet> swCapexSets = capexSetBusiness.queryCapexSet(taskID.intValue());
        String employeeNo = RequestContext.getEmployeeNo();
        BriefTaskVo task = taskBusiness.getTaskById(taskID.intValue());
        if (employeeNo.equalsIgnoreCase(task.getProposerEid())) {
            return true;
        }

        Map<String, List<SwCapexSet>> mapSet = swCapexSets.stream()
                .filter(e -> employeeNo.equalsIgnoreCase(e.getSetValue()) || StringUtils.isEmpty(e.getSetValue()))
                .collect(Collectors.groupingBy(SwCapexSet::getType));

        if (mapSet.containsKey(ROW)) {
            return mapSet.get(ROW).stream().anyMatch(e -> e.getNumber().equals(rowIndex));
        }

        if (mapSet.containsKey(Column)) {
            return mapSet.get(Column).stream().anyMatch(e -> e.getNumber().equals(colIndex));
        }
        return false;
    }
}
