package com.foxconn.sw.service.processor.collaboration;

import com.foxconn.sw.data.dto.entity.collaboration.CollaborationVo;
import com.foxconn.sw.data.dto.request.collaboration.CollaborationDetailParams;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CollaborationDetailProcessor {

    public CollaborationVo detail(CollaborationDetailParams params) {
        CollaborationVo vo = new CollaborationVo();
        vo.setHeaders(testHeaders());
        vo.setContent(initMapList());
        return vo;
    }


    private List<String> testHeaders() {
        return Lists.newArrayList("id", "header1", "header2", "header3");
    }

    private List<Map<String, Object>> initMapList() {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(initMap());
        return list;
    }

    private Map<String, Object> initMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("header1", "value1");
        map.put("header2", "value2");
        map.put("header3", "value3");
        map.put("status", 0);
        map.put("edit", false);
        map.put("op", "op");
        return map;
    }
}
