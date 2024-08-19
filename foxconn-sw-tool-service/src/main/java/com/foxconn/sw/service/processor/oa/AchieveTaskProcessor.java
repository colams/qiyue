package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.constants.enums.retcode.RetCode;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.oa.TaskProgressBriefParams;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

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
     * @param briefParamsList
     * @param head
     * @return
     */
    public boolean achieve(List<TaskProgressBriefParams> briefParamsList, Header head) {
        if (CollectionUtils.isEmpty(briefParamsList)) {
            throw new BizException(RetCode.VALIDATE_FAILED);
        }
        UserInfo userInfo = commonUserUtils.queryUserInfo(head.getToken());
        String employeeName = commonUserUtils.getEmployeeName(userInfo);

        briefParamsList.forEach(m -> {
            boolean result = saveProcess(m, userInfo.getEmployeeNo());
            if (result && Optional.ofNullable(m.getProgress()).orElse(0) > 0) {
                taskBusiness.achieveTask(m.getTaskId(), m.getProgress());
                taskLogBusiness.addTaskLog(m.getTaskId(), employeeName, String.format("%s 更新任务进度为 %s%", employeeName, m.getProgress()));
            }
        });

        saveAchieveLog(briefParamsList.get(0).getTaskId(), employeeName);
        return true;
    }

    private boolean saveAchieveLog(Integer taskId, String employeeName) {
        String content = String.format("%s 完成任务", employeeName);
        return taskLogBusiness.addTaskLog(taskId, employeeName, content);
    }

    private boolean saveProcess(TaskProgressBriefParams briefParams, String employeeID) {
        SwTaskProgress taskProgress = new SwTaskProgress();
        taskProgress.setTaskId(briefParams.getTaskId());
        taskProgress.setOperateEid(employeeID);
        taskProgress.setResourceIds(ConvertUtils.listIntegerToString(briefParams.getResourceIds()));
        taskProgress.setProgress(briefParams.getProgress());
        taskProgress.setContent(briefParams.getContent());
        return taskProgressBusiness.addProcessInfo(taskProgress);
    }
}
