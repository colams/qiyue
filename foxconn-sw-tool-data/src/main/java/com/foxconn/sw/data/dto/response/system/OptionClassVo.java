package com.foxconn.sw.data.dto.response.system;

import com.foxconn.sw.data.dto.entity.universal.OptionsVo;
import com.foxconn.sw.data.dto.request.system.OptionClassParams;

import java.util.List;

public class OptionClassVo {
    private OptionClassParams optionClassParams;
    private List<OptionsVo> optionsVoList;

    public OptionClassParams getOptionClassParams() {
        return optionClassParams;
    }

    public void setOptionClassParams(OptionClassParams optionClassParams) {
        this.optionClassParams = optionClassParams;
    }

    public List<OptionsVo> getOptionsVoList() {
        return optionsVoList;
    }

    public void setOptionsVoList(List<OptionsVo> optionsVoList) {
        this.optionsVoList = optionsVoList;
    }
}
