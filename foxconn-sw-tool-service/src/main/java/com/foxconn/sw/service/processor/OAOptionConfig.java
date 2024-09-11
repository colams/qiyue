package com.foxconn.sw.service.processor;

import com.foxconn.sw.data.constants.enums.oa.TaskLevelEnums;
import com.foxconn.sw.data.dto.entity.universal.OptionsVo;

import java.util.ArrayList;
import java.util.List;

public class OAOptionConfig {

    public static List<OptionsVo> initUrgency() {
        List<OptionsVo> optionsVos = new ArrayList<>();
        for (var enums : TaskLevelEnums.values()) {
            optionsVos.add(new OptionsVo(enums.getCode().toString(), enums.getMsg()));
        }
        return optionsVos;
    }

    public static List<OptionsVo> initProject() {
        List<OptionsVo> optionsVos = new ArrayList<>();
        List<OptionsVo> optionsVos1 = new ArrayList<>();
        OptionsVo optionsVo1 = new OptionsVo("1", "FF", optionsVos1);
        optionsVos1.add(new OptionsVo("1-1", "GA"));
        optionsVos1.add(new OptionsVo("1-2", "NW"));
        optionsVos1.add(new OptionsVo("1-3", "GB"));
        optionsVos1.add(new OptionsVo("1-4", "Magen"));
        optionsVos1.add(new OptionsVo("1-5", "Reno"));
        optionsVos1.add(new OptionsVo("1-6", "JU-M"));

        List<OptionsVo> optionsVos2 = new ArrayList<>();
        OptionsVo optionsVo2 = new OptionsVo("2", "AF", optionsVos2);
        optionsVos2.add(new OptionsVo("2-1", "CHS"));
        optionsVos2.add(new OptionsVo("2-2", "CHS25"));
        optionsVos2.add(new OptionsVo("2-3", "CHS26"));

        List<OptionsVo> optionsVos3 = new ArrayList<>();
        OptionsVo optionsVo3 = new OptionsVo("3", "SS", optionsVos3);
        addOptionVo("3-1", "MW23", optionsVos3);
        addOptionVo("3-2", "MW24", optionsVos3);
        addOptionVo("3-3", "MW25", optionsVos3);
        addOptionVo("3-4", "ATW", optionsVos3);

        addOptionVo("3-5", "AKC", optionsVos3);
        addOptionVo("3-6", "BWI24", optionsVos3);
        addOptionVo("3-7", "BWI25", optionsVos3);
        addOptionVo("3-8", "BOI", optionsVos3);

        List<OptionsVo> optionsVos4 = new ArrayList<>();
        OptionsVo optionsVo4 = new OptionsVo("4", "3D", optionsVos4);
        addOptionVo("4-1", "B3J001", optionsVos4);
        addOptionVo("4-2", "F3G001", optionsVos4);
        addOptionVo("4-3", "F3J001", optionsVos4);
        addOptionVo("4-4", "F3J002", optionsVos4);
        addOptionVo("4-5", "F3L001", optionsVos4);

        addOptionVo("4-6", "GLOL01", optionsVos4);
        addOptionVo("4-7", "H5NL01", optionsVos4);
        addOptionVo("4-8", "H5NL02", optionsVos4);
        addOptionVo("4-9", "H5NL03", optionsVos4);
        addOptionVo("4-10", "H5NM208", optionsVos4);
        addOptionVo("4-11", "H5NM209", optionsVos4);
        addOptionVo("4-12", "I3K002", optionsVos4);
        addOptionVo("4-13", "I3K003", optionsVos4);
        addOptionVo("4-14", "I3L001", optionsVos4);
        addOptionVo("4-15", "M3J001", optionsVos4);

        addOptionVo("4-16", "V3J001", optionsVos4);
        addOptionVo("4-17", "VINK01", optionsVos4);

        List<OptionsVo> optionsVos5 = new ArrayList<>();
        OptionsVo optionsVo5 = new OptionsVo("5", "Micosoft", optionsVos5);
        addOptionVo("5-1", "Whidbey", optionsVos5);
        addOptionVo("5-2", "lummi", optionsVos5);
        addOptionVo("5-3", "LK1&3", optionsVos5);
        addOptionVo("5-4", "May_24M", optionsVos5);
        addOptionVo("5-5", "Sake_12M", optionsVos5);
        addOptionVo("5-6", "Unibody_12M", optionsVos5);
        addOptionVo("5-7", "ASUS_Dual13M", optionsVos5);
        addOptionVo("5-8", "ASUS_Dual50M", optionsVos5);
        addOptionVo("5-9", "CFB_FEWL001", optionsVos5);

        List<OptionsVo> optionsVos6 = new ArrayList<>();
        OptionsVo optionsVo6 = new OptionsVo("6", "AVI", optionsVos6);
        addOptionVo("6-1", "Lens", optionsVos6);
        addOptionVo("6-2", "Connector", optionsVos6);

        List<OptionsVo> optionsVos7 = new ArrayList<>();
        OptionsVo optionsVo7 = new OptionsVo("7", "工具", optionsVos7);
        addOptionVo("7-1", "辦公助手", optionsVos7);
        addOptionVo("7-2", "FA工具", optionsVos7);

        List<OptionsVo> optionsVos8 = new ArrayList<>();
        OptionsVo optionsVo8 = new OptionsVo("8", "其他", optionsVos8);
        addOptionVo("8-1", "其他", optionsVos8);

        optionsVos.add(optionsVo1);
        optionsVos.add(optionsVo2);
        optionsVos.add(optionsVo3);
        optionsVos.add(optionsVo4);
        optionsVos.add(optionsVo5);
        optionsVos.add(optionsVo6);
        optionsVos.add(optionsVo7);
        optionsVos.add(optionsVo8);
        return optionsVos;
    }

    public static List<OptionsVo> initTaskType() {
        List<OptionsVo> optionsVos = new ArrayList<>();
        List<OptionsVo> optionsVos1 = new ArrayList<>();
        OptionsVo optionsVo1 = new OptionsVo("1", "MIL", optionsVos1);
        optionsVos1.add(new OptionsVo("1-1", "設備"));
        optionsVos1.add(new OptionsVo("1-2", "生產"));
        optionsVos1.add(new OptionsVo("1-3", "軟件"));

        List<OptionsVo> optionsVos2 = new ArrayList<>();
        OptionsVo optionsVo2 = new OptionsVo("2", "專案管理", optionsVos2);
        optionsVos2.add(new OptionsVo("2-1", "计划管理"));
        optionsVos2.add(new OptionsVo("2-2", "任务管理"));
        optionsVos2.add(new OptionsVo("2-3", "文件管理"));
        optionsVos2.add(new OptionsVo("2-4", "成员管理"));
        optionsVos2.add(new OptionsVo("2-5", "報價"));

        List<OptionsVo> optionsVos3 = new ArrayList<>();
        OptionsVo optionsVo3 = new OptionsVo("3", "软件", optionsVos3);
        addOptionVo("3-1", "軟件需求", optionsVos3);
        addOptionVo("3-2", "軟件bug", optionsVos3);
        addOptionVo("3-3", "系统开发", optionsVos3);

        List<OptionsVo> optionsVos4 = new ArrayList<>();
        OptionsVo optionsVo4 = new OptionsVo("4", "報告", optionsVos4);
        addOptionVo("4-1", "MBO report", optionsVos4);
        addOptionVo("4-2", "OK2Send", optionsVos4);
        addOptionVo("4-3", "GRR", optionsVos4);
        addOptionVo("4-4", "XY Correlation", optionsVos4);
        addOptionVo("4-5", "FACA", optionsVos4);

        List<OptionsVo> optionsVos5 = new ArrayList<>();
        OptionsVo optionsVo5 = new OptionsVo("5", "測試/生產", optionsVos5);
        addOptionVo("5-1", "軟件測試", optionsVos5);
        addOptionVo("5-2", "設備故障", optionsVos5);

        List<OptionsVo> optionsVos6 = new ArrayList<>();
        OptionsVo optionsVo6 = new OptionsVo("6", "其他", optionsVos6);
        addOptionVo("6-1", "其他", optionsVos6);

        optionsVos.add(optionsVo1);
        optionsVos.add(optionsVo2);
        optionsVos.add(optionsVo3);
        optionsVos.add(optionsVo4);
        optionsVos.add(optionsVo5);
        optionsVos.add(optionsVo6);
        return optionsVos;
    }

    private static void addOptionVo(String key, String value, List<OptionsVo> optionsVoList) {
        optionsVoList.add(new OptionsVo(key, value));
    }

}
