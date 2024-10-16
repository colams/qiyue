package com.foxconn.sw.data.constants.enums;

public enum TaskRoleFlagEnums {

    Proposer_Flag(1 << 0), // 0001 任务发起人
    Manager_Flag(1 << 1),  // 0010 任务经办人
    Handler_Flag(1 << 2),  // 0100 任务DRI 处理人
    Watcher_Flag(1 << 3),  // 1000 任务关注者
    ;


    private int flag;

    TaskRoleFlagEnums(Integer bitFlag) {
        this.flag = bitFlag;
    }

    public int getFlag() {
        return flag;
    }

    public static Integer initFlag(TaskRoleFlagEnums taskRoleFlagEnum) {
        return 0 | taskRoleFlagEnum.getFlag();
    }


    public static Integer setFlag(Integer flags, TaskRoleFlagEnums taskRoleFlagEnum) {
        return flags | taskRoleFlagEnum.getFlag();
    }

    public static boolean checkFlag(Integer flags, TaskRoleFlagEnums taskRoleFlagEnum) {
        return (flags & taskRoleFlagEnum.getFlag()) != 0;
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

    public static Integer removeFlag(Integer flags, TaskRoleFlagEnums taskRoleFlagEnum) {
        return flags & (~taskRoleFlagEnum.getFlag());
    }
}
