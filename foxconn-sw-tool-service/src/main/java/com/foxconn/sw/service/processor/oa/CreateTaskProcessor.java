package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.mapper.TaskMapper;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefDetailVo;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTaskProcessor {

    @Autowired
    CommonUserUtils userUtils;
    @Autowired
    SwTaskBusiness swTaskBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    SwTaskLogBusiness taskLogBusiness;
    @Autowired
    SwTaskProgressBusiness progressBusiness;

    public boolean createTask(TaskBriefDetailVo data, Header head) {

        SwTask task = TaskMapper.INSTANCE.brief2SwTask(data);
        UserInfo user = userUtils.queryUserInfo(head.getToken());
        task.setProposerEid(user.getEmployeeNo());

        boolean result = swTaskBusiness.createTask(task);
        if (result) {
            addTaskLog(user, task);
            if (TaskStatusEnums.PENDING.getCode().equals(task.getStatus())) {
                addProcessInfo(user, task);
            }
        }
        return result;
    }

    private void addTaskLog(UserInfo user, SwTask task) {
        String operator = String.format("%s(%s)", user.getEmployeeName(), user.getEmployeeNo());
        String content = String.format("由 %s 创建了任务", operator);
        taskLogBusiness.addTaskLog(task.getId(), operator, content);
    }

    private void addProcessInfo(UserInfo user, SwTask task) {
        SwEmployee ee = employeeBusiness.selectEmployeeByENo(task.getManagerEid());
        String content = String.format("發起並派送給：%s", ee.getName());
        SwTaskProgress progress = new SwTaskProgress();
        progress.setTaskId(task.getId());
        progress.setOperateEid(user.getEmployeeNo());
        progress.setProgress(0);
        progress.setContent(content);
        progressBusiness.addProcessInfo(progress);
    }
}
