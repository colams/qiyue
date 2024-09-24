package com.foxconn.sw.service.processor.oa.task;

import com.foxconn.sw.business.mapper.TaskMapper;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefDetailVo;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateTaskProcessor {

    @Autowired
    CommonUserUtils userUtils;
    @Autowired
    SwTaskBusiness swTaskBusiness;
    @Autowired
    SwTaskLogBusiness taskLogBusiness;

    public boolean updateTask(TaskBriefDetailVo data, Header head) {

        SwTask task = TaskMapper.INSTANCE.brief2SwTask(data);
        UserInfo user = userUtils.queryUserInfo(head.getToken());
        task.setProposerEid(user.getEmployeeNo());

        boolean result = swTaskBusiness.updateTask(task);
        if (result) {
            String operator = String.format("%s(%s)", user.getEmployeeName(), user.getEmployeeNo());
            String content = String.format("%s 更新了任务", operator);
            taskLogBusiness.addTaskLog(task.getId(), operator, content);
        }
        return result;
    }
}
