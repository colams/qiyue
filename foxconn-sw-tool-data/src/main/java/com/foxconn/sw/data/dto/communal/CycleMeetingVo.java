package com.foxconn.sw.data.dto.communal;

import java.util.List;

public class CycleMeetingVo {

    private List<Integer> cycle;

    private String cycleStart;
    private String cycleExpire;

    public List<Integer> getCycle() {
        return cycle;
    }

    public void setCycle(List<Integer> cycle) {
        this.cycle = cycle;
    }

    public String getCycleStart() {
        return cycleStart;
    }

    public void setCycleStart(String cycleStart) {
        this.cycleStart = cycleStart;
    }

    public String getCycleExpire() {
        return cycleExpire;
    }

    public void setCycleExpire(String cycleExpire) {
        this.cycleExpire = cycleExpire;
    }
}
