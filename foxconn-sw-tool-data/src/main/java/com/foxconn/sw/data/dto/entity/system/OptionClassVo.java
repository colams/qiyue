package com.foxconn.sw.data.dto.entity.system;

import com.foxconn.sw.data.dto.entity.universal.OptionsVo;

import java.util.List;

public class OptionClassVo {

    private String optionName;
    private List<OptionClassVo> optionClassVos;

    private List<OptionsVo> optionsVoList;

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public List<OptionClassVo> getOptionClassVos() {
        return optionClassVos;
    }

    public void setOptionClassVos(List<OptionClassVo> optionClassVos) {
        this.optionClassVos = optionClassVos;
    }

    public List<OptionsVo> getOptionsVoList() {
        return optionsVoList;
    }

    public void setOptionsVoList(List<OptionsVo> optionsVoList) {
        this.optionsVoList = optionsVoList;
    }
}
