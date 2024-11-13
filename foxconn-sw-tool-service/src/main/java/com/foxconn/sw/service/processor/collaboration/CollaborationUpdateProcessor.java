package com.foxconn.sw.service.processor.collaboration;

import com.foxconn.sw.business.collaboration.CollaborationDetailBusiness;
import com.foxconn.sw.business.collaboration.CollaborationUserBusiness;
import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.data.constants.enums.retcode.OAExceptionCode;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationDetailParams;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationEvaluationParams;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationUpdateParams;
import com.foxconn.sw.data.entity.SwCollaborationDetail;
import com.foxconn.sw.data.entity.SwCollaborationUser;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class CollaborationUpdateProcessor {

    @Autowired
    CollaborationDetailBusiness collaborationDetail;
    @Autowired
    CollaborationUserBusiness collaborationUserBusiness;
    @Autowired
    SwTaskBusiness taskBusiness;


    public Boolean update(CollaborationUpdateParams data) {
        if (Objects.isNull(data.getId())) {
            SwCollaborationUser collaborationUser = new SwCollaborationUser();
            collaborationUser.setTaskId(data.getTaskID());
            collaborationUser.setEmployeeNo(RequestContext.getEmployeeNo());
            Long scuID = collaborationUserBusiness.insertCollaborationUser(collaborationUser);

            for (Map.Entry<String, String> entry : data.getContent().entrySet()) {
                SwCollaborationDetail detail = new SwCollaborationDetail();
                detail.setScuId(scuID);
                detail.setItem(entry.getKey());
                detail.setItemValue(entry.getValue());
                collaborationDetail.updateOrInsert(detail);
            }

            return true;
        }

        List<SwCollaborationDetail> detailList = collaborationDetail.queryCollaborationDetail(data.getId());
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
        List<SwCollaborationUser> collaborationUsers = collaborationUserBusiness
                .queryCollaborationUser(data.getTaskID(), data.getIdList());

        SwTask task = taskBusiness.getTaskById(collaborationUsers.get(0).getTaskId());
        if (!RequestContext.getEmployeeNo().equalsIgnoreCase(task.getProposerEid())) {
            throw new BizException(OAExceptionCode.NO_PERMISSION_EXCEPTION);
        }

        for (SwCollaborationUser user : collaborationUsers) {
            collaborationUserBusiness.updateEvaluation(user, data.getEvaluationType());
        }
        return true;
    }

    public Boolean submit(CollaborationDetailParams data) {
        SwCollaborationUser collaboration = collaborationUserBusiness.queryCollaborationUserByID(data.getId());
        if (Objects.isNull(collaboration)) {
            return false;
        }
        collaboration.setStatus(1);
        return collaborationUserBusiness.updateUser(collaboration);
    }
}
