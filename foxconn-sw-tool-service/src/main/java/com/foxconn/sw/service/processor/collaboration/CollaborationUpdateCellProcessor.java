package com.foxconn.sw.service.processor.collaboration;

import com.foxconn.sw.business.collaboration.CollaborationDetailBusiness;
import com.foxconn.sw.business.collaboration.CollaborationDetailLogBusiness;
import com.foxconn.sw.business.collaboration.CollaborationUserBusiness;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationSaveUpdateParams;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationUpdateCellParams;
import com.foxconn.sw.data.entity.SwCollaborationDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

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

    public Boolean updateCell(CollaborationUpdateCellParams data) {
        SwCollaborationDetail collaborationDetail = collaborationDetailBusiness.selectCollaborationDetail(data.getDetailID(),
                data.getRowIndex(),
                data.getColIndex(),
                data.getItem());

        if (Objects.nonNull(collaborationDetail)) {
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
            SwCollaborationDetail updateDetail = new SwCollaborationDetail();
            updateDetail.setId(e.getId());
            updateDetail.setSpareValue("");
            updateDetail.setItemValue(e.getSpareValue());
            collaborationDetailBusiness.updateOrInsert(updateDetail);
        });
        return true;
    }
}
