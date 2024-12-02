package com.foxconn.sw.data.dto.entity.task;

import java.util.List;

public class TaskEntityVo {

    private TaskDetailVo taskDetailVo;

    private List<TaskProgressVo> taskProgressVos;

    private List<TaskLogVo> taskLogVos;

    public TaskDetailVo getTaskDetailVo() {
        return taskDetailVo;
    }

    public void setTaskDetailVo(TaskDetailVo taskDetailVo) {
        this.taskDetailVo = taskDetailVo;
    }

    public List<TaskProgressVo> getTaskProgressVos() {
        return taskProgressVos;
    }

    public void setTaskProgressVos(List<TaskProgressVo> taskProgressVos) {
        this.taskProgressVos = taskProgressVos;
    }

    public List<TaskLogVo> getTaskLogVos() {
        return taskLogVos;
    }

    public void setTaskLogVos(List<TaskLogVo> taskLogVos) {
        this.taskLogVos = taskLogVos;
    }
}
