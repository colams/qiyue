package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class TaskNoSeed {
    private Integer id;

    private Long seed;

    private Integer status;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public TaskNoSeed withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getSeed() {
        return seed;
    }

    public TaskNoSeed withSeed(Long seed) {
        this.setSeed(seed);
        return this;
    }

    public void setSeed(Long seed) {
        this.seed = seed;
    }

    public Integer getStatus() {
        return status;
    }

    public TaskNoSeed withStatus(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public TaskNoSeed withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}