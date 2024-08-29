package com.foxconn.sw.data.dto.entity.system;

public class PostVo {


    private Integer id;
    private String name;

    public PostVo() {
    }

    public PostVo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
