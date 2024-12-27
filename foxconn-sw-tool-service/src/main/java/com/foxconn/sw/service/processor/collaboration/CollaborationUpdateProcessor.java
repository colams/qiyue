package com.foxconn.sw.service.processor.collaboration;

import com.foxconn.sw.business.collaboration.CollaborationDetailBusiness;
import com.foxconn.sw.business.collaboration.CollaborationDetailLogBusiness;
import com.foxconn.sw.business.collaboration.CollaborationUserBusiness;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
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

import java.util.ArrayList;
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
    @Autowired
    SwTaskProgressBusiness taskProgressBusiness;
    @Autowired
    CollaborationDetailLogBusiness collaborationDetailLogBusiness;


    public Boolean update(CollaborationUpdateParams data) {
        List<SwCollaborationUser> collaborationUsers = collaborationUserBusiness.queryCollaborationUser(data.getTaskID());
        if (Objects.isNull(data.getHeader())) {
            return updateRow(collaborationUsers, data.getTaskID(), data.getId(), data.getContent());
        } else {
            return updateCol(collaborationUsers, data.getHeader(), data.getColPair());
        }
    }

    public Boolean updateRow(List<SwCollaborationUser> collaborationUsers, Integer taskID, Long id, Map<String, String> content) {
        String rowIndexStr = content.get("rowIndex");
        Integer rowIndex = Objects.isNull(rowIndexStr) ? null : Integer.valueOf(rowIndexStr);

        if (Objects.nonNull(rowIndex) && rowIndex > 0 && (Objects.isNull(id) || id <= 0)) {
            List<SwCollaborationDetail> detailList = collaborationDetail.queryCollaborationDetail(collaborationUsers.get(0).getId(), rowIndex);
            List<SwCollaborationDetail> updateDetails = new ArrayList<>();
            detailList.forEach(e -> {
                SwCollaborationDetail detail = new SwCollaborationDetail();
                detail.setId(e.getId());
                detail.setItemValue(content.getOrDefault(e.getItem(), ""));
                detail.setSpareValue(content.getOrDefault(e.getItem(), ""));
                updateDetails.add(detail);
            });
            collaborationDetail.batchUpdate(updateDetails);
            collaborationDetailLogBusiness.insertCollaborationDetailLog(updateDetails);
        } else {
            if (Objects.isNull(id)) {
                SwCollaborationUser collaborationUser = new SwCollaborationUser();
                collaborationUser.setTaskId(taskID);
                collaborationUser.setEmployeeNo(RequestContext.getEmployeeNo());
                Long scuID = collaborationUserBusiness.insertCollaborationUser(collaborationUser);

                for (Map.Entry<String, String> entry : content.entrySet()) {
                    SwCollaborationDetail detail = new SwCollaborationDetail();
                    detail.setScuId(scuID);
                    detail.setItem(entry.getKey());
                    detail.setItemValue(entry.getValue());
                    detail.setSpareValue(entry.getValue());
                    Long detailID = collaborationDetail.updateOrInsert(detail);
                    collaborationDetailLogBusiness.insertCollaborationDetailLog(detailID,
                            detail.getRowIndex(),
                            detail.getColIndex(),
                            entry.getValue());
                }

                return true;
            }

            List<SwCollaborationDetail> detailList = collaborationDetail.queryCollaborationDetail(id);
            for (Map.Entry<String, String> entry : content.entrySet()) {
                SwCollaborationDetail detail = detailList.stream()
                        .filter(e -> e.getItem().equalsIgnoreCase(entry.getKey()) && e.getRowIndex().equals(rowIndex))
                        .findFirst()
                        .orElse(new SwCollaborationDetail());
                detail.setItem(entry.getKey());
                detail.setScuId(id);
                detail.setItemValue(entry.getValue());
                detail.setSpareValue(entry.getValue());
                Long detailID = collaborationDetail.updateOrInsert(detail);
                collaborationDetailLogBusiness.insertCollaborationDetailLog(detailID,
                        detail.getRowIndex(),
                        detail.getColIndex(),
                        entry.getValue());
            }
        }
        return true;
    }


    public Boolean updateCol(List<SwCollaborationUser> collaborationUsers, String header, Map<Integer, String> colPair) {
        for (Map.Entry<Integer, String> entry : colPair.entrySet()) {
            collaborationDetail.updateItemValue(collaborationUsers.get(0).getId(), entry.getKey(), header, entry.getValue());
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
