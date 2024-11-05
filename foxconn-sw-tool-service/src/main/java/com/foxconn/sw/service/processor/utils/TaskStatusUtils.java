package com.foxconn.sw.service.processor.utils;

import com.foxconn.sw.data.constants.enums.oa.RejectStatusEnum;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.entity.oa.InfoColorVo;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;

import java.util.Optional;

import static com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums.Proposer_Flag;

public class TaskStatusUtils {


    public static InfoColorVo processStatus(Integer status, Integer rejectStatus, Optional<SwTaskEmployeeRelation> optional) {

        boolean isInspector = false;
        boolean isProposer = false;

        if (optional.isPresent()) {
            isProposer = Proposer_Flag.test(optional.get().getRoleFlag());
            isInspector = optional.get().getIsInspector() == 1;
        }

        TaskStatusEnums taskStatusEnums = TaskStatusEnums.getStatusByCode(status);
        String desc = taskStatusEnums.getMsg();
        if (taskStatusEnums.getCode() == TaskStatusEnums.PENDING.getCode()
                && rejectStatus == RejectStatusEnum.RELEASE_REJECT.getCode()) {
            desc = "發佈回退";
        } else if (taskStatusEnums.getCode() == TaskStatusEnums.PROCESSING.getCode()
                && rejectStatus == RejectStatusEnum.ACCEPTING_REJECT.getCode()) {
            desc = "驗收駁回";
        }
        if (taskStatusEnums == TaskStatusEnums.ACCEPTING && isProposer && !isInspector) {
            taskStatusEnums = TaskStatusEnums.PROCESSING;
        }

        InfoColorVo statusInfoVo = initVo(taskStatusEnums, desc);
        return statusInfoVo;
    }

    public static InfoColorVo processStatus(Integer status, Integer rejectStatus) {
        TaskStatusEnums taskStatusEnums = TaskStatusEnums.getStatusByCode(status);
        String desc = taskStatusEnums.getMsg();
        if (taskStatusEnums.getCode() == TaskStatusEnums.PENDING.getCode()
                && rejectStatus == RejectStatusEnum.RELEASE_REJECT.getCode()) {
            desc = "发布回退";
        } else if (taskStatusEnums.getCode() == TaskStatusEnums.PROCESSING.getCode()
                && rejectStatus == RejectStatusEnum.ACCEPTING_REJECT.getCode()) {
            desc = "验收驳回";
        }

        InfoColorVo statusInfoVo = initVo(taskStatusEnums, desc);
        return statusInfoVo;
    }

    private static InfoColorVo initVo(TaskStatusEnums taskStatusEnums, String desc) {
        InfoColorVo colorVo = new InfoColorVo();
        colorVo.setCode(taskStatusEnums.getCode());
        colorVo.setColor(taskStatusEnums.getColor());
        colorVo.setDescription(desc);
        return colorVo;
    }
}
