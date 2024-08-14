package com.foxconn.sw.service.processor.oa.utils;

import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.entity.oa.InfoColorVo;
import com.foxconn.sw.data.exception.BizException;

import static com.foxconn.sw.data.constants.enums.retcode.RetCode.ENUM_CONVERT_ERROR;

public class TaskStatusUtils {


    public static InfoColorVo processStatus(String employeeNo, Integer status, String managerEid, String handleEid) {
        InfoColorVo statusInfoVo;
        TaskStatusEnums taskStatusEnums = TaskStatusEnums.getStatusByCode(status);
        switch (taskStatusEnums) {
            case DRAFT:
                statusInfoVo = initVo(status, "gray", "草稿");
                break;
            case PENDING:
                if (employeeNo.equalsIgnoreCase(managerEid)) {
                    statusInfoVo = initVo(status, "gray", "新需求");
                } else {
                    statusInfoVo = initVo(status, "gold", "待确认");
                }
                break;
            case CONFIRMING:
                if (employeeNo.equalsIgnoreCase(handleEid)) {
                    statusInfoVo = initVo(status, "gray", "新需求");
                } else {
                    statusInfoVo = initVo(status, "brown", "确认中");
                }
                break;
            case PROCESSING:
                statusInfoVo = initVo(status, "blue", "处理中");
                break;
            case ACCEPTING:
                statusInfoVo = initVo(status, "palevioletred", "待验收");
                break;
            case COMPLETED:
                statusInfoVo = initVo(status, "lime", "已完成");
                break;
            case CLOSED:
                statusInfoVo = initVo(status, "gray", "已关闭");
                break;
            default:
                throw new BizException(ENUM_CONVERT_ERROR);
        }
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
