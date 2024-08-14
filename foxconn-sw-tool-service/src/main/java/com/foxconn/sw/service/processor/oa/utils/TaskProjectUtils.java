package com.foxconn.sw.service.processor.oa.utils;

import com.foxconn.sw.data.dto.entity.universal.OptionsVo;
import com.foxconn.sw.service.processor.OAOptionConfig;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class TaskProjectUtils {

    public static String processProject(String project) {
        List<OptionsVo> list = OAOptionConfig.initTaskType();
        String result = project;
        for (OptionsVo optionVo : list) {
            var tempList = optionVo.getOptionsVos()
                    .stream()
                    .filter(optionsVo -> project.equalsIgnoreCase(optionsVo.getKey()))
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(tempList)) {
                result = tempList.get(0).getText();
                break;
            }
        }
        return result;
    }

}
