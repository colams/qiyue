package com.foxconn.sw.data.dto.response.schedule;

public class TeamScheduleListVo {

    private String date;
    private String destination;
    private String place;

    private boolean current;
    /**
     * 常驻，出差，个人休假，法定休假，工作(台湾上班/返乡)
     */
    private String type;
    private Integer weekInfo;
    private String name;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getWeekInfo() {
        return weekInfo;
    }

    public void setWeekInfo(Integer weekInfo) {
        this.weekInfo = weekInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
