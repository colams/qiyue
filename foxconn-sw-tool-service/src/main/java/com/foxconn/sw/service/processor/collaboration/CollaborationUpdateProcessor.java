package com.foxconn.sw.service.processor.collaboration;

import com.foxconn.sw.business.collaboration.CollaborationDetailBusiness;
import com.foxconn.sw.business.collaboration.CollaborationDetailLogBusiness;
import com.foxconn.sw.business.collaboration.CollaborationUserBusiness;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.common.constanst.NumberConstants;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
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

import java.util.*;

@Component
public class CollaborationUpdateProcessor {

    @Autowired
    CollaborationDetailBusiness collaborationDetail;
    @Autowired
    CollaborationUserBusiness collaborationUserBusiness;
    @Autowired
    SwTaskBusiness taskBusiness;
    @Autowired
    SwTaskProgressBusiness taskProgressBusiness;
    @Autowired
    CollaborationDetailLogBusiness collaborationDetailLogBusiness;

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

        if (data.getEvaluationType().equals(2)) {
            taskBusiness.updateTaskStatus(data.getTaskID(), TaskStatusEnums.COMPLETED);
            taskProgressBusiness.addProcessInfo(data.getTaskID(), "完成了協作任務");
        }

        if (data.getEvaluationType().equals(3)) {
            taskBusiness.updateTaskStatus(data.getTaskID(), TaskStatusEnums.PROCESSING);
            taskProgressBusiness.addProcessInfo(data.getTaskID(), "重新開啟了協作任務");
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
