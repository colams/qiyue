package com.foxconn.sw.service.processor.oa.utils;

import com.foxconn.sw.data.constants.enums.OperateTypeEnum;
import com.foxconn.sw.data.constants.enums.oa.RejectStatusEnum;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.entity.oa.TaskDetailVo;
import com.foxconn.sw.data.dto.entity.universal.OperateEntity;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums.*;
import static com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums.*;

public class TaskOperateUtils {

    public static OperateEntity processOperate(String employeeNo, SwTask task, OperateTypeEnum op) {
        TaskStatusEnums taskStatusEnums = TaskStatusEnums.getStatusByCode(task.getStatus());
        OperateEntity operate = null;
        Integer subType = null;
        boolean enable = false;
        switch (op) {
            case VIEW:
                operate = initVo(op.getMsg(), op.name(), !enable, subType);
                break;
            case UPDATE:
                if ((taskStatusEnums == DRAFT
                        || taskStatusEnums == REVOKE
                        || (taskStatusEnums == PENDING
                        && task.getRejectStatus() == RejectStatusEnum.RELEASE_REJECT.getCode()))
                        && employeeNo.equalsIgnoreCase(task.getProposerEid())) {
                    enable = true;
                    subType = 0;
                } else if (taskStatusEnums != REVOKE
                        && taskStatusEnums != CLOSED
                        && taskStatusEnums != COMPLETED
                        && StringUtils.isEmpty(task.getHandleEid())
                        && employeeNo.equalsIgnoreCase(task.getProposerEid())) {
                    enable = true;
                    subType = 1;
                } else if (taskStatusEnums == ACCEPTING
                        && employeeNo.equalsIgnoreCase(task.getProposerEid())) {
                    enable = true;
                    subType = 1;
                } else if ((taskStatusEnums == PENDING
                        || taskStatusEnums == PROCESSING)
                        && employeeNo.equalsIgnoreCase(task.getHandleEid())) {
                    enable = true;
                    subType = 1;
                }
                operate = initVo(op.getMsg(), op.name(), enable, subType);
                break;
            case REMINDER:
                if ((taskStatusEnums == PROCESSING
                        || (taskStatusEnums == PENDING
                        && task.getRejectStatus() != RejectStatusEnum.RELEASE_REJECT.getCode()))
                        && employeeNo.equalsIgnoreCase(task.getProposerEid())) {
                    enable = true;
                }
                operate = initVo(op.getMsg(), op.name(), enable);
                break;
            case REVOKE:
                if (taskStatusEnums == PENDING
                        && employeeNo.equalsIgnoreCase(task.getProposerEid())) {
                    enable = true;
                }
                operate = initVo(op.getMsg(), op.name(), enable);
                break;
        }
        return operate;
    }

    public static OperateEntity processDetailOperate(String employeeNo, TaskDetailVo taskDetailVo,
                                                     OperateTypeEnum op, List<SwTaskEmployeeRelation> relations) {
        SwTaskEmployeeRelation relation = relations.stream()
                .filter(e -> e.getEmployeeNo().equalsIgnoreCase(employeeNo))
                .findFirst()
                .orElse(null);

        boolean hasHandler = relations.stream().anyMatch(e -> Handler_Flag.test(e.getRoleFlag()));

        boolean isProposer = Proposer_Flag.test(relation.getRoleFlag());
        boolean isManger = Manager_Flag.test(relation.getRoleFlag());
        boolean isHandler = Handler_Flag.test(relation.getRoleFlag());


        TaskStatusEnums taskStatusEnums = TaskStatusEnums.getStatusByCode(taskDetailVo.getStatus());
        boolean enable = false;
        switch (op) {
            case REJECT:
                boolean pendingAndUnReject = PENDING.equals(taskStatusEnums)
                        && RejectStatusEnum.UN_REJECT.test(taskDetailVo.getRejectStatus());
                enable = (pendingAndUnReject && (isManger || isHandler)) || (ACCEPTING.equals(taskStatusEnums) && isProposer);
                break;
            case ASSIGN:
                enable = PENDING.equals(taskStatusEnums) && (isManger || isHandler);
                break;
            case ACCEPT:
                enable = PENDING.equals(taskStatusEnums) && isManger;
                break;
            case SUBMIT:
                enable = PROCESSING.equals(taskStatusEnums) && isHandler;
                break;
            case CHECK:
                enable = ACCEPTING.equals(taskStatusEnums) && isProposer;
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
