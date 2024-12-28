package com.foxconn.sw.service.processor.utils;

import com.foxconn.sw.data.constants.enums.oa.TaskLevelEnums;
import com.foxconn.sw.data.dto.entity.oa.InfoColorVo;

public class TaskLevelUtils {


    public static InfoColorVo processLevel(Integer level) {
        TaskLevelEnums levelEnums = TaskLevelEnums.getLevel(level);
        InfoColorVo colorVo = new InfoColorVo();
        colorVo.setCode(levelEnums.getCode());
        colorVo.setColor(levelEnums.getColor());
        colorVo.setDescription(levelEnums.getMsg());
        return colorVo;
    }

}
