package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.data.constants.enums.OperateTypeEnum;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.oa.InfoColorVo;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefListVo;
import com.foxconn.sw.data.dto.entity.oa.TaskParams;
import com.foxconn.sw.data.dto.entity.universal.OperateEntity;
import com.foxconn.sw.service.processor.oa.utils.TaskCategoryUtils;
import com.foxconn.sw.service.processor.oa.utils.TaskLevelUtils;
import com.foxconn.sw.service.processor.oa.utils.TaskProjectUtils;
import com.foxconn.sw.service.processor.oa.utils.TaskStatusUtils;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskListProcessor {
    @Autowired
    CommonUserUtils userUtils;
    @Autowired
    SwTaskBusiness taskBusiness;

    public PageEntity<TaskBriefListVo> list(PageParams<TaskParams> taskParams, Header head) {
        String employeeId = userUtils.getEmployeeNo(head.getToken());
        List<TaskBriefListVo> briefVos = taskBusiness.listBriefVos(taskParams, employeeId);
        processAfter(briefVos, employeeId);
        int totalCount = taskBusiness.getTotalCountByParams(taskParams.getParams(), employeeId);
        PageEntity<TaskBriefListVo> voPageEntity = new PageEntity<>(totalCount, briefVos);
        return voPageEntity;
    }

    private void processAfter(List<TaskBriefListVo> briefVos, String employeeNo) {
        briefVos.forEach(e -> {
            InfoColorVo statusInfoVo = TaskStatusUtils.processStatus(employeeNo, e.getStatus(), e.getManagerEid(), e.getHandleEid());
            e.setStatusInfoVo(statusInfoVo);
            e.setOperateList(processOperate(e, employeeNo));
            e.setLevelInfo(TaskLevelUtils.processLevel(e.getLevel()));
            e.setProject(TaskProjectUtils.processProject(e.getProject()));
            e.setCategory(TaskCategoryUtils.processCategory(e.getTopCategory(), e.getCategory()));
        });
    }

    private List<OperateEntity> processOperate(TaskBriefListVo e, String employeeNo) {
        List<OperateEntity> entityList = new ArrayList<>();
        for (OperateTypeEnum op : OperateTypeEnum.values()) {
            OperateEntity operate = new OperateEntity();
            operate.setOperateName(op.getMsg());
            OperateTypeEnum.EnableInfo enableInfo = op.getEnable(e, employeeNo);
            operate.setEnable(enableInfo.isEnable());
            operate.setMsg(enableInfo.getMsg());
            operate.setOperateType(op.name());
            entityList.add(operate);
        }
        return entityList;
    }
}
