package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.oa.TaskProgressBriefParams;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AchieveTaskProcessor {

    @Autowired
    CommonUserUtils commonUserUtils;
    @Autowired
    SwTaskBusiness taskBusiness;
    @Autowired
    SwTaskLogBusiness taskLogBusiness;
    @Autowired
    SwTaskProgressBusiness taskProgressBusiness;

    /**
     * 完成任务
     *
     * @param briefParams
     * @param head
     * @return
     */
    public boolean achieve(TaskProgressBriefParams briefParams, Header head) {
        UserInfo userInfo = commonUserUtils.queryUserInfo(head.getToken());
        String employeeName = commonUserUtils.getEmployeeName(userInfo);

        boolean result = saveProcess(briefParams, userInfo.getEmployeeNo());
        if (result) {
            taskBusiness.achieveTask(briefParams.getTaskId(), briefParams.getProgress(),briefParams.getContent());
            taskLogBusiness.addTaskLog(briefParams.getTaskId(), employeeName, String.format("%s 完成任务", employeeName));
        }
        return true;
    }

    private boolean saveProcess(TaskProgressBriefParams briefParams, String employeeID) {
        SwTaskProgress taskProgress = new SwTaskProgress();
        taskProgress.setTaskId(briefParams.getTaskId());
        taskProgress.setOperateEid(employeeID);
        taskProgress.setResourceIds(ConvertUtils.listIntegerToString(briefParams.getResourceIds()));
        taskProgress.setProgress(briefParams.getProgress());
        if (Objects.isNull(briefParams.getProgress())) {
            taskProgress.setContent("完成操作：" + briefParams.getContent());
        } else {
            taskProgress.setContent(briefParams.getContent());
        }
        return taskProgressBusiness.addProcessInfo(taskProgress);
    }
}
