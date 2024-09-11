package com.foxconn.sw.data.constants.enums;

public enum TaskRoleFlagEnums {

    Proposer_Flag(1 << 0),  // 0001
    Manager_Flag(1 << 1),  // 0010
    Handler_Flag(1 << 2),  // 0100
    ;


    private int bigFlag;

    TaskRoleFlagEnums(Integer bigFlag) {
        this.bigFlag = bigFlag;
    }

    public int getBigFlag() {
        return bigFlag;
    }

    public static Integer initFlag(TaskRoleFlagEnums taskRoleFlagEnum) {
        return 0 | taskRoleFlagEnum.getBigFlag();
    }


    public static Integer setFlag(Integer flags, TaskRoleFlagEnums taskRoleFlagEnum) {
        return flags | taskRoleFlagEnum.getBigFlag();
    }

    public static boolean checkFlag(Integer flags, TaskRoleFlagEnums taskRoleFlagEnum) {
        return (flags & taskRoleFlagEnum.getBigFlag()) != 0;
    }

    public boolean test(Integer flags) {
        return (flags & getBigFlag()) != 0;
    }


    public static Integer clearFlag(Integer flags, TaskRoleFlagEnums taskRoleFlagEnum) {
        return flags & (~taskRoleFlagEnum.getBigFlag());
    }
}
