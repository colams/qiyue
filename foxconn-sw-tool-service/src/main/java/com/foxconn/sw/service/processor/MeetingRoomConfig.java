package com.foxconn.sw.service.processor;

import com.foxconn.sw.data.dto.entity.universal.OptionsVo;

import java.util.ArrayList;
import java.util.List;

public class MeetingRoomConfig {

    public static List<OptionsVo> rooms() {
        List<OptionsVo> optionsVos = new ArrayList<>();
        List<OptionsVo> optionsVos1 = new ArrayList<>();
        OptionsVo optionsVo1 = new OptionsVo("1", "F1-1.5F", optionsVos1);
        optionsVos1.add(new OptionsVo("1-1", "國際會議室"));
        optionsVos1.add(new OptionsVo("1-2", "金會議室"));
        optionsVos1.add(new OptionsVo("1-3", "木會議室"));
        optionsVos1.add(new OptionsVo("1-4", "水會議室"));
        optionsVos1.add(new OptionsVo("1-5", "火會議室"));
        optionsVos1.add(new OptionsVo("1-6", "歐洲會議室"));
        optionsVos1.add(new OptionsVo("1-7", "美洲會議室"));
        optionsVos1.add(new OptionsVo("1-8", "大洋洲會議室"));

        List<OptionsVo> optionsVos2 = new ArrayList<>();
        OptionsVo optionsVo2 = new OptionsVo("2", "F6-1.5F", optionsVos2);
        optionsVos1.add(new OptionsVo("2-1", "Los Angelse"));
        optionsVos1.add(new OptionsVo("2-2", "New York"));
        optionsVos1.add(new OptionsVo("2-3", "San Francisco"));
        optionsVos1.add(new OptionsVo("2-4", "Seattle"));
        optionsVos1.add(new OptionsVo("2-5", "Hawaii"));
        optionsVos1.add(new OptionsVo("2-6", "Chicago"));
        optionsVos1.add(new OptionsVo("2-7", "califomia"));

        List<OptionsVo> optionsVos3 = new ArrayList<>();
        OptionsVo optionsVo3 = new OptionsVo("3", "F2-1.5F", optionsVos3);
        addOptionVo("3-1", "會議室", optionsVos3);

        optionsVos.add(optionsVo1);
        optionsVos.add(optionsVo2);
        optionsVos.add(optionsVo3);
        return optionsVos;
    }

    private static void addOptionVo(String key, String value, List<OptionsVo> optionsVoList) {
        optionsVoList.add(new OptionsVo(key, value));
    }

}
