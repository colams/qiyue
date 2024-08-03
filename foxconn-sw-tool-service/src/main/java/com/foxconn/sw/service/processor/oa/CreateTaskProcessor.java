package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.mapper.TaskMapper;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefDetailVo;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwUser;
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
    SwTaskLogBusiness taskLogBusiness;

    public boolean createTask(TaskBriefDetailVo data, Header head) {

        SwTask task = TaskMapper.INSTANCE.brief2SwTask(data);
        SwUser user = userUtils.queryUserInfo(head.getToken());
        task.setProposerEid(user.getUserName());

        boolean result = swTaskBusiness.createTask(task);
        if (result) {
            String operator = String.format("%s(%s)", user.getName(), user.getUserName());
            String content = String.format("%s 创建了任务", operator);
            taskLogBusiness.addTaskLog(task.getId(), operator, content);
        }
        return result;
    }
}
