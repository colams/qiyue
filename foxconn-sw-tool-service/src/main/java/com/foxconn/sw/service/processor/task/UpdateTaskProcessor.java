package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.common.utils.ObjectCompare;
import com.foxconn.sw.data.dto.request.task.UpdateTaskParams;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UpdateTaskProcessor {

    @Autowired
    CommonUserUtils userUtils;
    @Autowired
    SwTaskBusiness swTaskBusiness;
    @Autowired
    SwTaskLogBusiness taskLogBusiness;
    @Autowired
    SwTaskProgressBusiness progressBusiness;

    public boolean updateTask(UpdateTaskParams taskParams) {

        SwTask old = swTaskBusiness.getTaskById(taskParams.getBriefTaskVo().getId());
        boolean result = swTaskBusiness.updateTask(taskParams.getBriefTaskVo());
        if (result) {
            Pair pair = ObjectCompare.compare(taskParams.getBriefTaskVo(), old, SwTask.class, taskParams.getFieldInfo());

            String content = "修改了任务:" + taskParams.getFieldInfo();
            if (Objects.nonNull(pair) && !"description".equalsIgnoreCase(taskParams.getFieldInfo())) {
                content += String.format(" : %s > %s", pair.getRight(), pair.getLeft());
            }
            taskLogBusiness.addTaskLog(old.getId(), RequestContext.getEmployeeNo(), content);

            SwTaskProgress progress = new SwTaskProgress();
            progress.setTaskId(old.getId());
            progress.setOperateEid(RequestContext.getEmployeeNo());
            progress.setProgress(0);
            progress.setContent(content);
            progressBusiness.addProcessInfo(progress);

        }
        return result;
    }
}
