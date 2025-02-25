package com.foxconn.sw.data.dto.response.schedule;

public class ScheduleListVo {

    private String date;
    private String destination;

    private boolean current;
    /**
     * 常驻，出差，个人休假，法定休假，工作(台湾上班/返乡)
     */
    private String type;

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
}
