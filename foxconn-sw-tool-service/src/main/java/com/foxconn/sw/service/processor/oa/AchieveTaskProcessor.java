package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.common.utils.ConvertUtils;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.TaskProgressBriefParams;
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
            throw new BizException("请求信息不存在！");
        }
        String employeeId = commonUserUtils.getEmployeeNo(head.getToken());

        briefParamsList.forEach(m -> {
            boolean result = saveProcess(m, employeeId);
            if (result && Optional.of(m.getProgress()).orElse(0) > 0) {
                taskBusiness.updateProgress(m.getTaskId(), m.getProgress());
                taskLogBusiness.addTaskLog(m.getTaskId(), employeeId, String.format("%s 更新任务进度为 %s%", employeeId, m.getProgress()));
            }
        });

        saveAchieveLog(briefParamsList.get(0).getTaskId(), employeeId);
        return true;
    }

    private boolean saveAchieveLog(Integer taskId, String employeeID) {
        String content = String.format("%s 完成任务", employeeID);
        return taskLogBusiness.addTaskLog(taskId, employeeID, content);
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
