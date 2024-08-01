package com.foxconn.sw.data.dto.entity.oa;

public class MyWorks {

    private int total;
    private int argentCount;
    private int scheduleCount;
    private int approvalCount;
    private int unReadMailCount;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getArgentCount() {
        return argentCount;
    }

    public void setArgentCount(int argentCount) {
        this.argentCount = argentCount;
    }

    public int getScheduleCount() {
        return scheduleCount;
    }

    public void setScheduleCount(int scheduleCount) {
        this.scheduleCount = scheduleCount;
    }

    public int getApprovalCount() {
        return approvalCount;
    }

    public void setApprovalCount(int approvalCount) {
        this.approvalCount = approvalCount;
    }

    public int getUnReadMailCount() {
        return unReadMailCount;
    }

    public void setUnReadMailCount(int unReadMailCount) {
        this.unReadMailCount = unReadMailCount;
    }
}
