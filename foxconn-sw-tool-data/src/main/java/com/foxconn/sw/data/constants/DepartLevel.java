package com.foxconn.sw.data.constants;

public enum DepartLevel {

    UnDefine(0, ""),
    Directorate(1, "总办"),
    DivisionChief(2, "處級"),
    Ministerial(3, "部級"),
    SectionChief(4, "課級"),
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
