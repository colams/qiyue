package com.foxconn.sw.service.processor.oa.utils;

import com.foxconn.sw.data.constants.enums.oa.RejectStatusEnum;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.entity.oa.InfoColorVo;
import org.apache.commons.lang3.StringUtils;

public class TaskStatusUtils {


    public static InfoColorVo processStatus(Integer status, Integer rejectStatus, String handleEid) {
        TaskStatusEnums taskStatusEnums = TaskStatusEnums.getStatusByCode(status);
        String desc = taskStatusEnums.getMsg();
        if (taskStatusEnums.getCode() == TaskStatusEnums.PENDING.getCode()
                && rejectStatus == RejectStatusEnum.RELEASE_REJECT.getCode()
                && StringUtils.isEmpty(handleEid)) {
            desc = "发布驳回";
        } else if (taskStatusEnums.getCode() == TaskStatusEnums.PROCESSING.getCode()
                && rejectStatus == RejectStatusEnum.ACCEPTING_REJECT.getCode()) {
            desc = "验收驳回";
        }

        InfoColorVo statusInfoVo = initVo(status, taskStatusEnums.getColor(), desc);
        return statusInfoVo;
    }

    private static InfoColorVo initVo(Integer code, String color, String desc) {
        InfoColorVo colorVo = new InfoColorVo();
        colorVo.setCode(code);
        colorVo.setColor(color);
        colorVo.setDescription(desc);
        return colorVo;
    }
}
