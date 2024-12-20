package com.foxconn.sw.service.processor.utils;

import com.foxconn.sw.data.constants.enums.OperateTypeEnum;
import com.foxconn.sw.data.constants.enums.oa.RejectStatusEnum;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.entity.task.TaskDetailVo;
import com.foxconn.sw.data.dto.entity.universal.OperateEntity;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums.*;
import static com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums.*;

public class TaskOperateUtils {

    public static OperateEntity processOperate(SwTask task,
                                               OperateTypeEnum op,
                                               Optional<SwTaskEmployeeRelation> optional) {
        TaskStatusEnums taskStatusEnums = TaskStatusEnums.getStatusByCode(task.getStatus());

        boolean isProposer = false;
        boolean isManger = false;
        boolean isHandler = false;
        boolean isInspector = false;

        if (optional.isPresent()) {
            SwTaskEmployeeRelation relation = optional.get();
            isProposer = Proposer_Flag.test(relation.getRoleFlag());
            isManger = Manager_Flag.test(relation.getRoleFlag());
            isHandler = Handler_Flag.test(relation.getRoleFlag());
            isInspector = relation.getIsInspector() == 1;
        }

        OperateEntity operate = null;
        Integer subType = null;
        boolean enable = false;
        switch (op) {
            case VIEW:
                operate = initVo(op.getMsg(), op.name(), !enable, subType);
                break;
            case UPDATE:
                if (isProposer) {
                    subType = taskStatusEnums == ACCEPTING || isInspector ? 1 : 0;
                    enable = taskStatusEnums == DRAFT   // 草稿
                            || taskStatusEnums == REVOKE    // 已撤销
                            || (taskStatusEnums == PENDING
                            && task.getRejectStatus().equals(RejectStatusEnum.RELEASE_REJECT.getCode()))// 驳回
                            || (taskStatusEnums == ACCEPTING || isInspector);    // 待验收
                }

                if (!enable && isHandler) {
                    subType = 1;
                    enable = (taskStatusEnums == PENDING && task.getRejectStatus()
                            != RejectStatusEnum.RELEASE_REJECT.getCode())
                            || taskStatusEnums == PROCESSING;
                }

                if (!enable && isManger) {
                    subType = 1;
                    enable = (taskStatusEnums == PENDING || taskStatusEnums == PROCESSING)
                            && task.getRejectStatus() != RejectStatusEnum.RELEASE_REJECT.getCode();
                }

                if (isInspector) {
                    subType = 1;
                    enable = true;
                }
                operate = initVo(op.getMsg(), op.name(), enable, subType);
                break;
            case REMINDER:
                if ((taskStatusEnums == PROCESSING
                        || (taskStatusEnums == PENDING && task.getRejectStatus()
                        != RejectStatusEnum.RELEASE_REJECT.getCode()))
                        && isProposer) {
                    enable = true;
                }
                operate = initVo(op.getMsg(), op.name(), enable);
                break;
            case REVOKE:
                if (taskStatusEnums == DRAFT || taskStatusEnums == REVOKE) {
                    enable = true;
                    op = OperateTypeEnum.DELETE;
                } else {
                    if ((taskStatusEnums == PENDING && isProposer) || isInspector) {
                        enable = true;
                    }
                }

                operate = initVo(op.getMsg(), op.name(), enable);
                break;
            case DELETE:
                if ((taskStatusEnums == PENDING && isProposer) || isInspector) {
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
                .orElse(new SwTaskEmployeeRelation());
        int roleFlag = Optional.ofNullable(relation).map(e -> e.getRoleFlag()).orElse(0);
        boolean isProposer = Proposer_Flag.test(roleFlag);
        boolean isManger = Manager_Flag.test(roleFlag);
        boolean isHandler = Handler_Flag.test(roleFlag);
        boolean isInspector = Objects.nonNull(relation.getIsInspector()) ? relation.getIsInspector() == 1 : false;


        TaskStatusEnums taskStatusEnums = TaskStatusEnums.getStatusByCode(taskDetailVo.getStatus());
        boolean enable = false;
        switch (op) {
            case REJECT:
                enable = ACCEPTING.equals(taskStatusEnums) && (isProposer || isInspector);
                break;
            case ASSIGN:
            case SUBMIT:
                enable = (PENDING.equals(taskStatusEnums) || PROCESSING.equals(taskStatusEnums)) && (isManger || isHandler);
                break;
//            case ACCEPT:
//                enable = PENDING.equals(taskStatusEnums) && isManger;
//                break;
//            case SUBMIT:
//                enable = PROCESSING.equals(taskStatusEnums) && (isHandler || isManger);
//                break;
            case CHECK:
                enable = ACCEPTING.equals(taskStatusEnums) && (isProposer || isInspector);
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
