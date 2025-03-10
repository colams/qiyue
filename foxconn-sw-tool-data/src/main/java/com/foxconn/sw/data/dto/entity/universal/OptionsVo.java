package com.foxconn.sw.data.dto.entity.universal;

import java.util.List;

public class OptionsVo {

    private String key;
    private String text;
    private String extra;

    private List<OptionsVo> optionsVos;

    public OptionsVo() {
    }

    public OptionsVo(String key, String text) {
        this.text = text;
        this.key = key;
    }

    public OptionsVo(String key, String text, List<OptionsVo> optionsVos) {
        this.text = text;
        this.key = key;
        this.optionsVos = optionsVos;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public List<OptionsVo> getOptionsVos() {
        return optionsVos;
    }

    public void setOptionsVos(List<OptionsVo> optionsVos) {
        this.optionsVos = optionsVos;
    }
}
