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
import com.foxconn.sw.data.dto.request.collaboration.CollaborationSaveUpdateParams;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationUpdateCellParams;
import com.foxconn.sw.data.entity.SwCollaborationDetail;
import com.foxconn.sw.data.entity.SwCollaborationDetailSpare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        SwCollaborationDetailSpare updateDetailSpare;
        if (Objects.nonNull(data.getDetailID())) {
            updateDetailSpare = getUpdateDetailSpareEntity(data);
        } else {
            updateDetailSpare = initNewDetailLine(data);
        }
        return collaborationDetailSpareBusiness.updateOrInsert(updateDetailSpare) > 0;
    }

    private SwCollaborationDetailSpare initNewDetailLine(CollaborationUpdateCellParams data) {
        SwCollaborationDetail collaborationDetail = collaborationDetailBusiness.createCollaborationDetail(data);
        SwCollaborationDetailSpare updateDetailSpare = new SwCollaborationDetailSpare();
        updateDetailSpare.setTaskId(data.getTaskID());
        updateDetailSpare.setDetailId(collaborationDetail.getId());
        updateDetailSpare.setOperator(RequestContext.getEmployeeNo());
        updateDetailSpare.setValue(data.getValue());
        return updateDetailSpare;
    }

    private SwCollaborationDetailSpare getUpdateDetailSpareEntity(CollaborationUpdateCellParams data) {
        SwCollaborationDetailSpare detailSpare = collaborationDetailSpareBusiness.getCollaborationDetail(data.getDetailID());
        SwCollaborationDetailSpare updateDetailSpare = new SwCollaborationDetailSpare();
        updateDetailSpare.setDetailId(data.getDetailID());
        updateDetailSpare.setId(Optional.ofNullable(detailSpare).map(SwCollaborationDetailSpare::getId).orElse(0L));
        updateDetailSpare.setValue(data.getValue());
        updateDetailSpare.setOperator(RequestContext.getEmployeeNo());
        updateDetailSpare.setTaskId(data.getTaskID());
        return updateDetailSpare;
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

    public Boolean cancelUpdate(CollaborationSaveUpdateParams data) {
        List<SwCollaborationDetailSpare> spares = collaborationDetailSpareBusiness.getCollaborationDetails(data.getTaskID());
        spares.forEach(spare -> {
            SwCollaborationDetailSpare detailSpare = new SwCollaborationDetailSpare();
            detailSpare.setId(spare.getId());
            detailSpare.setIsDelete(NumberConstants.ONE);
            collaborationDetailSpareBusiness.updateOrInsert(detailSpare);
        });
        return true;
    }
}
