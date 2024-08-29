package com.foxconn.sw.service.processor.oa.utils;

import com.foxconn.sw.data.constants.enums.OperateTypeEnum;
import com.foxconn.sw.data.constants.enums.oa.RejectStatusEnum;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefListVo;
import com.foxconn.sw.data.dto.entity.oa.TaskDetailVo;
import com.foxconn.sw.data.dto.entity.universal.OperateEntity;
import org.apache.commons.lang3.StringUtils;

import static com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums.*;

public class TaskOperateUtils {

    public static OperateEntity processOperate(String userNo, TaskBriefListVo briefListVo, OperateTypeEnum op) {
        TaskStatusEnums taskStatusEnums = TaskStatusEnums.getStatusByCode(briefListVo.getStatus());
        OperateEntity operate = null;
        Integer subType = null;
        boolean enable = false;
        switch (op) {
            case VIEW:
                operate = initVo(op.getMsg(), op.name(), !enable, subType);
                break;
            case UPDATE:
                if ((taskStatusEnums == DRAFT
                        || (taskStatusEnums == PENDING
                        && briefListVo.getRejectStatus() == RejectStatusEnum.RELEASE_REJECT.getCode()))
                        && userNo.equalsIgnoreCase(briefListVo.getProposerEid())) {
                    enable = true;
                    subType = 0;
                } else if (taskStatusEnums != CLOSED
                        && taskStatusEnums != COMPLETED
                        && StringUtils.isEmpty(briefListVo.getHandleEid())
                        && userNo.equalsIgnoreCase(briefListVo.getManagerEid())) {
                    enable = true;
                    subType = 1;
                } else if (taskStatusEnums == ACCEPTING
                        && userNo.equalsIgnoreCase(briefListVo.getProposerEid())) {
                    enable = true;
                    subType = 1;
                } else if ((taskStatusEnums == PENDING || taskStatusEnums == PROCESSING) &&
                        userNo.equalsIgnoreCase(briefListVo.getHandleEid())) {
                    enable = true;
                    subType = 1;
                }
                operate = initVo(op.getMsg(), op.name(), enable, subType);
                break;
            case REMINDER:
                if ((taskStatusEnums == PROCESSING
                        || (taskStatusEnums == PENDING
                        && briefListVo.getRejectStatus() != RejectStatusEnum.RELEASE_REJECT.getCode()))
                        && userNo.equalsIgnoreCase(briefListVo.getProposerEid())) {
                    enable = true;
                }
                operate = initVo(op.getMsg(), op.name(), enable);
                break;
            case REVOKE:
                if (taskStatusEnums == PENDING
                        && userNo.equalsIgnoreCase(briefListVo.getProposerEid())) {
                    enable = true;
                }
                operate = initVo(op.getMsg(), op.name(), enable);
                break;
        }
        return operate;
    }

    public static OperateEntity processDetailOperate(String userNo, TaskDetailVo taskDetailVo, OperateTypeEnum op) {
        TaskStatusEnums taskStatusEnums = TaskStatusEnums.getStatusByCode(taskDetailVo.getStatus());
        boolean enable = false;
        switch (op) {
            case REJECT:
                if ((taskStatusEnums == PENDING
                        && userNo.equalsIgnoreCase(taskDetailVo.getManagerEid())
                        && StringUtils.isEmpty(taskDetailVo.getHandleEid())
                        && RejectStatusEnum.UN_REJECT.getCode() == taskDetailVo.getRejectStatus())
                        || (taskStatusEnums == ACCEPTING
                        && userNo.equalsIgnoreCase(taskDetailVo.getProposerEid()))) {
                    enable = true;
                }
                break;
            case ASSIGN:
                if (taskStatusEnums == PENDING
                        && (userNo.equalsIgnoreCase(taskDetailVo.getManagerEid())
                        || userNo.equalsIgnoreCase(taskDetailVo.getHandleEid()))) {
                    enable = true;
                }
                break;
            case ACCEPT:
                if (taskStatusEnums == PENDING
                        && userNo.equalsIgnoreCase(taskDetailVo.getHandleEid())) {
                    enable = true;
                }
                break;
            case SUBMIT:
                if (taskStatusEnums == PROCESSING
                        && userNo.equalsIgnoreCase(taskDetailVo.getHandleEid())) {
                    enable = true;
                }
                break;
            case ACHIEVE:
                if (taskStatusEnums == PROCESSING
                        && userNo.equalsIgnoreCase(taskDetailVo.getHandleEid())
                        && taskDetailVo.getProgressPercent() == 100) {
                    enable = true;
                }
                break;
            case CHECK:
                if (taskStatusEnums == ACCEPTING
                        && userNo.equalsIgnoreCase(taskDetailVo.getProposerEid())) {
                    enable = true;
                }
                break;
        }
        return enable ? initVo(op.getMsg(), op.name(), true) : null;
    }

    private static OperateEntity initVo(String name, String operateType, boolean enable, Integer subOperateType) {
        OperateEntity operate = new OperateEntity();
        operate.setOperateName(name);
        operate.setOperateType(operateType.toLowerCase());
        operate.setEnable(enable);
        operate.setSubOperateType(subOperateType);
        return operate;
    }

    private static OperateEntity initVo(String name, String operateType, boolean enable) {
        return initVo(name, operateType, enable, null);
    }
}
