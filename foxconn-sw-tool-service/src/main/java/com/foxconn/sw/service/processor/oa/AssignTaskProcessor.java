package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.TaskAssignParams;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.data.entity.SwUser;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssignTaskProcessor {

    @Autowired
    private CommonUserUtils commonUserUtils;
    @Autowired
    private SwTaskBusiness taskBusiness;
    @Autowired
    private SwTaskProgressBusiness taskProgressBusiness;
    @Autowired
    private SwTaskLogBusiness taskLogBusiness;


    /**
     * 分派 工作任务
     *
     * @param data
     * @param head
     * @return
     */
    public boolean assignTask(TaskAssignParams data, Header head) {
        SwUser user = commonUserUtils.queryUserInfo(head.getToken());
        boolean result = taskBusiness.assignTask(data.getTaskId(), data.getAssignEid());
        if (result) {
            addProcessInfo(data, user);
            addTaskLog(data, user);
        }
        return result;
    }

    private boolean addTaskLog(TaskAssignParams data, SwUser user) {
        return taskLogBusiness.addTaskLog(data.getTaskId(), user.getUserName(), data.getContent());
    }

    private boolean addProcessInfo(TaskAssignParams data, SwUser user) {
        SwTaskProgress progress = new SwTaskProgress();
        progress.setTaskId(data.getTaskId());
        progress.setOperateEid(user.getUserName());
        progress.setContent(String.format("%s 任务分派给了 %s; %s", user.getUserName(), data.getAssignEid(), data.getContent()));
        return taskProgressBusiness.addProcessInfo(progress);
    }
}
