package com.foxconn.sw.data.constants;

public enum DepartLevel {

    Directorate(0, "总办"),
    Ministerial(1, "處級"),
    SectionChief(2, "部級"),
    ;

    DepartLevel(Integer level, String name) {
        this.level = level;
        this.name = name;
    }

    private Integer level;
    private String name;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
