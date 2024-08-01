package com.foxconn.sw.data.dto.entity.oa;

import java.util.List;

public class TaskEntityVo {

    private TaskDetailVo taskDetailVo;

    private List<TaskProgressVo> taskLogList;

    public TaskDetailVo getTaskDetailVo() {
        return taskDetailVo;
    }

    public void setTaskDetailVo(TaskDetailVo taskDetailVo) {
        this.taskDetailVo = taskDetailVo;
    }

    public List<TaskProgressVo> getTaskLogList() {
        return taskLogList;
    }

    public void setTaskLogList(List<TaskProgressVo> taskLogList) {
        this.taskLogList = taskLogList;
    }
}
