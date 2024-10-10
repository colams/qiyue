package com.foxconn.sw.service.processor.collaboration;

import com.foxconn.sw.business.collaboration.CollaborationDetailBusiness;
import com.foxconn.sw.business.collaboration.CollaborationUserBusiness;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationDetailParams;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationEvaluationParams;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationUpdateParams;
import com.foxconn.sw.data.entity.SwCollaborationDetail;
import com.foxconn.sw.data.entity.SwCollaborationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CollaborationUpdateProcessor {

    @Autowired
    CollaborationDetailBusiness collaborationDetail;
    @Autowired
    CollaborationUserBusiness collaborationUser;


    public Boolean update(CollaborationUpdateParams data) {
        List<SwCollaborationDetail> detailList = collaborationDetail.queryCollaborationDetail(data.getId());
        data.getContent().entrySet();
        for (Map.Entry<String, String> entry : data.getContent().entrySet()) {
            SwCollaborationDetail detail = detailList.stream()
                    .filter(e -> e.getItem().equalsIgnoreCase(entry.getKey()))
                    .findFirst()
                    .orElse(new SwCollaborationDetail());
            detail.setItem(entry.getKey());
            detail.setScuId(data.getId());
            detail.setItemValue(entry.getValue());
            collaborationDetail.updateOrInsert(detail);
        }
        return true;
    }

    public Boolean evaluation(CollaborationEvaluationParams data) {
        List<SwCollaborationUser> collaborationUsers = collaborationUser.queryCollaborationUser(data.getIdList());
        for (SwCollaborationUser user : collaborationUsers) {
            collaborationUser.updateEvaluation(user, data.getEvaluationType());
        }
        return true;
    }

    public Boolean submit(CollaborationDetailParams data) {
        SwCollaborationUser collaboration = collaborationUser.queryCollaborationUserByID(data.getID());
        collaboration.setStatus(1);
        return collaborationUser.updateUser(collaboration);
    }
}
