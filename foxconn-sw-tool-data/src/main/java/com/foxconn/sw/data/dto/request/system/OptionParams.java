package com.foxconn.sw.data.dto.request.system;

import com.foxconn.sw.data.dto.enums.system.OptionsEnums;

import java.util.List;

public class OptionParams {

    private List<OptionsEnums> optionsEnums;
    private String secondaryValue;

    public List<OptionsEnums> getOptionsEnums() {
        return optionsEnums;
    }

    public void setOptionsEnums(List<OptionsEnums> optionsEnums) {
        this.optionsEnums = optionsEnums;
    }

    public String getSecondaryValue() {
        return secondaryValue;
    }

    public void setSecondaryValue(String secondaryValue) {
        this.secondaryValue = secondaryValue;
    }
}
