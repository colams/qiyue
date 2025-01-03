package com.foxconn.sw.service.processor.collaboration;

import com.foxconn.sw.business.SwCapexSetBusiness;
import com.foxconn.sw.business.collaboration.CollaborationDetailBusiness;
import com.foxconn.sw.business.collaboration.CollaborationDetailLogBusiness;
import com.foxconn.sw.business.collaboration.CollaborationDetailSpareBusiness;
import com.foxconn.sw.business.collaboration.CollaborationUserBusiness;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.dto.entity.task.BriefTaskVo;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationSaveUpdateParams;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationUpdateCellParams;
import com.foxconn.sw.data.entity.SwCapexSet;
import com.foxconn.sw.data.entity.SwCollaborationDetail;
import com.foxconn.sw.data.entity.SwCollaborationDetailSpare;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    @Autowired
    CollaborationDetailSpareBusiness collaborationDetailSpareBusiness;

    public Boolean updateCell(CollaborationUpdateCellParams data) {
        SwCollaborationDetailSpare detailSpare = collaborationDetailSpareBusiness.getCollaborationDetail(data.getDetailID());
        SwCollaborationDetailSpare updateDetailSpare = new SwCollaborationDetailSpare();
        updateDetailSpare.setDetailId(data.getDetailID());
        updateDetailSpare.setId(Optional.ofNullable(detailSpare).map(SwCollaborationDetailSpare::getId).orElse(0L));
        updateDetailSpare.setValue(data.getValue());
        updateDetailSpare.setOperator(RequestContext.getEmployeeNo());
        updateDetailSpare.setTaskId(data.getTaskID());

        return collaborationDetailSpareBusiness.updateOrInsert(updateDetailSpare) > 0;
    }

    public Boolean saveUpdate(CollaborationSaveUpdateParams data) {

        List<SwCollaborationDetailSpare> spares = collaborationDetailSpareBusiness.getCollaborationDetails(data.getTaskID());
        spares.forEach(spare -> {
            SwCollaborationDetail updateDetail = new SwCollaborationDetail();
            updateDetail.setId(spare.getDetailId());
            updateDetail.setItemValue(spare.getValue());
            collaborationDetailBusiness.updateOrInsert(updateDetail);
            collaborationDetailLogBusiness.insertCollaborationDetailLog(updateDetail);

            SwCollaborationDetailSpare detailSpare = new SwCollaborationDetailSpare();
            detailSpare.setId(spare.getId());
            detailSpare.setIsDelete(NumberConstants.ONE);
            collaborationDetailSpareBusiness.updateOrInsert(detailSpare);
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
