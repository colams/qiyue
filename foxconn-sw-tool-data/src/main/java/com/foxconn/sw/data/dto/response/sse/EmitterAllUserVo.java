package com.foxconn.sw.data.dto.response.sse;

import java.util.List;

public class EmitterAllUserVo {

    private Integer count;
    private List<EmitterUserVo> list;

    public EmitterAllUserVo() {
    }

    public EmitterAllUserVo(Integer count, List<EmitterUserVo> list) {
        this.count = count;
        this.list = list;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<EmitterUserVo> getList() {
        return list;
    }

    public void setList(List<EmitterUserVo> list) {
        this.list = list;
    }
}
