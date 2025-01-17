package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwNotification {
    private Long id;

    private String msgType;

    private String content;

    private String receiver;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Long getId() {
        return id;
    }

    public SwNotification withId(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsgType() {
        return msgType;
    }

    public SwNotification withMsgType(String msgType) {
        this.setMsgType(msgType);
        return this;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public String getContent() {
        return content;
    }

    public SwNotification withContent(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public SwNotification withReceiver(String receiver) {
        this.setReceiver(receiver);
        return this;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwNotification withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwNotification withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}