package com.foxconn.sw.data.entity;

import java.time.LocalDateTime;

public class SwCustomGroupMember {
    private Integer id;

    private Integer customGroupId;

    private String member;

    private Integer memberType;

    private Integer isDelete;

    private LocalDateTime createTime;

    private LocalDateTime datetimeLastchange;

    public Integer getId() {
        return id;
    }

    public SwCustomGroupMember withId(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomGroupId() {
        return customGroupId;
    }

    public SwCustomGroupMember withCustomGroupId(Integer customGroupId) {
        this.setCustomGroupId(customGroupId);
        return this;
    }

    public void setCustomGroupId(Integer customGroupId) {
        this.customGroupId = customGroupId;
    }

    public String getMember() {
        return member;
    }

    public SwCustomGroupMember withMember(String member) {
        this.setMember(member);
        return this;
    }

    public void setMember(String member) {
        this.member = member == null ? null : member.trim();
    }

    public Integer getMemberType() {
        return memberType;
    }

    public SwCustomGroupMember withMemberType(Integer memberType) {
        this.setMemberType(memberType);
        return this;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public SwCustomGroupMember withIsDelete(Integer isDelete) {
        this.setIsDelete(isDelete);
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public SwCustomGroupMember withCreateTime(LocalDateTime createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDatetimeLastchange() {
        return datetimeLastchange;
    }

    public SwCustomGroupMember withDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.setDatetimeLastchange(datetimeLastchange);
        return this;
    }

    public void setDatetimeLastchange(LocalDateTime datetimeLastchange) {
        this.datetimeLastchange = datetimeLastchange;
    }
}