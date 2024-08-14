package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.oa.TaskAssignParams;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
        UserInfo user = commonUserUtils.queryUserInfo(head.getToken());
        boolean result = taskBusiness.assignTask(data.getTaskId(), data.getAssignEid());
        if (result) {
            addProcessInfo(data, user);
            addTaskLog(data, user);
        }
        return result;
    }

    private boolean addTaskLog(TaskAssignParams data, UserInfo user) {
        return taskLogBusiness.addTaskLog(data.getTaskId(), user.getEmployeeNo(), data.getContent());
    }

    private boolean addProcessInfo(TaskAssignParams data, UserInfo user) {
        SwTaskProgress progress = new SwTaskProgress();
        progress.setTaskId(data.getTaskId());
        progress.setOperateEid(user.getEmployeeNo());
        progress.setContent(String.format("%s 任务分派给了 %s; %s",
                user.getEmployeeName(),
                data.getAssignEid(),
                Optional.ofNullable(data.getContent()).orElse("")));
        return taskProgressBusiness.addProcessInfo(progress);
    }
}
