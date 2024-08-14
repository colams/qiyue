package com.foxconn.sw.data.constants.enums;

import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefListVo;
import org.apache.commons.lang3.StringUtils;

public enum OperateTypeEnum {

    VIEW(0, "查看"),
    UPDATE(1, "更新"),
    ASSIGN(2, "分派"),
    CHECK(3, "验收"),
    REVOKE(4, "撤销"),
    ;

    OperateTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public EnableInfo getEnable(TaskBriefListVo e, String employeeNo) {
        EnableInfo enableInfo = new EnableInfo(false, "当前状态不能操作");
        switch (this) {
            case VIEW:
                enableInfo = new EnableInfo(true);
                break;
            case UPDATE:
                if ((TaskStatusEnums.CONFIRMING.getCode().equals(e.getStatus())
                        || TaskStatusEnums.PROCESSING.getCode().equals(e.getStatus()))
                        && employeeNo.equalsIgnoreCase(e.getHandleEid())) {
                    enableInfo = new EnableInfo(true);
                } else {
                    enableInfo = new EnableInfo(false, "只有处理人可以更新");
                }
                break;
            case ASSIGN:
                if (TaskStatusEnums.PENDING.getCode().equals(e.getStatus())
                        && employeeNo.equalsIgnoreCase(e.getManagerEid())
                        && StringUtils.isEmpty(e.getHandleEid())) {
                    enableInfo = new EnableInfo(true);
                } else {
                    enableInfo = new EnableInfo(false, "当前任务已分派");
                }
                break;
            case CHECK:
                if (TaskStatusEnums.ACCEPTING.getCode().equals(e.getStatus())
                        && employeeNo.equalsIgnoreCase(e.getProposerEid())) {
                    enableInfo = new EnableInfo(true);
                } else {
                    enableInfo = new EnableInfo(false, "任务提交后，任务提出人才能处理");
                }
                break;
            case REVOKE:
                if (employeeNo.equalsIgnoreCase(e.getProposerEid())
                        && StringUtils.isEmpty(e.getHandleEid())
                        && e.getStatus() == TaskStatusEnums.PENDING.getCode()) {
                    enableInfo = new EnableInfo(true);
                } else {
                    enableInfo = new EnableInfo(false, "任务未分派时，提出者才可以操作");
                }
                break;
        }
        return enableInfo;
    }

    public class EnableInfo {
        private boolean enable;
        private String msg;

        public EnableInfo() {
        }

        public EnableInfo(boolean enable) {
            this.enable = enable;
        }

        public EnableInfo(boolean enable, String msg) {
            this.enable = enable;
            this.msg = msg;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
