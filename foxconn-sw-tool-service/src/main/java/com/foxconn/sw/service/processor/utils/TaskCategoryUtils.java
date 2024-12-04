package com.foxconn.sw.service.processor.utils;

import com.foxconn.sw.data.dto.entity.universal.OptionsVo;
import com.foxconn.sw.service.processor.OAOptionConfig;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class TaskCategoryUtils {

    public static String processCategory(String topCategory, String category) {
        List<OptionsVo> list = OAOptionConfig.initTaskType("");
        String result = category;
        String topResult = topCategory;

        if (category.equalsIgnoreCase("3-3")) {
            return "軟件開發";
        }

        for (OptionsVo optionVo : list) {
            var tempList = optionVo.getOptionsVos()
                    .stream()
                    .filter(optionsVo -> category.equalsIgnoreCase(optionsVo.getKey()))
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(tempList)) {
                result = tempList.get(0).getText();
                topResult = optionVo.getText();
            }
        }
        if ("MIL".equalsIgnoreCase(topResult)) {
            return String.format("%s-%s", topResult, result);
        }
        return result;
    }


    public static List<OptionsVo> processCategory(List<String> categorys) {
        List<OptionsVo> list = OAOptionConfig.initTaskType("");
        List<OptionsVo> result = new CopyOnWriteArrayList<>();

        List<String> projectlist = categorys.stream().distinct().collect(Collectors.toList());
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
