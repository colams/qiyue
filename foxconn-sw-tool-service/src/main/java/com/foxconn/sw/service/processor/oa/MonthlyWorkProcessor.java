package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.data.dto.entity.oa.MonthlyWorkParams;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MonthlyWorkProcessor {

    public Map<String, Integer> monthlyWork(MonthlyWorkParams data) {
        Map<String, Integer> mapUtils = new HashMap<>();
        mapUtils.put("1月", 41);
        mapUtils.put("2月", 45);
        mapUtils.put("3月", 36);
        mapUtils.put("4月", 52);
        mapUtils.put("5月", 46);
        mapUtils.put("6月", 38);
        mapUtils.put("7月", 57);
        return mapUtils;
    }
}
