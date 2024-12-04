package com.foxconn.sw.service.processor.utils;

import com.foxconn.sw.data.dto.entity.universal.OptionsVo;
import com.foxconn.sw.service.processor.OAOptionConfig;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class TaskProjectUtils {

    public static String processProject(String project) {
        List<OptionsVo> list = OAOptionConfig.initProject2();
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

    public static List<OptionsVo> processProject(List<String> projects) {
        List<OptionsVo> list = OAOptionConfig.initProject2();
        List<OptionsVo> result = new CopyOnWriteArrayList<>();

        List<String> projectlist = projects.stream().distinct().collect(Collectors.toList());
        list.stream().forEach(e -> {
            e.getOptionsVos().stream().forEach(v -> {
                if (projectlist.contains(v.getText()) || projectlist.contains(v.getKey())) {
                    result.add(v);
                }
            });
        });
        return result;
    }

}
