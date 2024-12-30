package com.foxconn.sw.data.constants.enums;

public enum MeetingRoleFlagEnums {

    Chairman_Flag(1 << 0),  // 0001 会议主持人  1
    Maintainer_Flag(1 << 1), // 0010 会议维护人 2
    Member_Flag(1 << 2),  // 0100 会议参与人    4
    Creator_Flag(1 << 3), // 1000 会议创建人    8
    Recorder(1 << 4), // 10000 会议记录人    16
    ;


    private int flag;

    MeetingRoleFlagEnums(Integer flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public static Integer initFlag(MeetingRoleFlagEnums roleFlag) {
        return 0 | roleFlag.getFlag();
    }


    public static Integer setFlag(Integer flags, MeetingRoleFlagEnums roleFlag) {
        return flags | roleFlag.getFlag();
    }

    public static boolean checkFlag(Integer flags, MeetingRoleFlagEnums roleFlag) {
        return (flags & roleFlag.getFlag()) != 0;
    }

    public boolean test(Integer flags) {
        return (flags & getFlag()) != 0;
    }

    public Integer setFlag(Integer flags) {
        return flags | getFlag();
    }

    public Integer initFlag() {
        return 0 | getFlag();
    }

    public Integer removeFlag(Integer flags) {
        return flags & (~getFlag());
    }

    public static Integer removeFlag(Integer flags, MeetingRoleFlagEnums roleFlag) {
        return flags & (~roleFlag.getFlag());
    }
}
