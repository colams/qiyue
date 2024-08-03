package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.data.constants.enums.OperateTypeEnum;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.common.OperateEntity;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefListVo;
import com.foxconn.sw.data.dto.entity.oa.TaskParams;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskListProcessor {
    @Autowired
    CommonUserUtils userUtils;
    @Autowired
    SwTaskBusiness taskBusiness;

    public PageEntity<TaskBriefListVo> list(PageParams<TaskParams> taskParams, Header head) {
        String employeeId = userUtils.getEmployeeId(head.getToken());
        List<TaskBriefListVo> briefVos = taskBusiness.listBriefVos(taskParams, employeeId);
        processOperate(briefVos);

        int totalCount = taskBusiness.getTotalCountByParams(taskParams.getParams(), employeeId);
        PageEntity<TaskBriefListVo> voPageEntity = new PageEntity<>(totalCount, briefVos);
        return voPageEntity;
    }

    private void processOperate(List<TaskBriefListVo> briefVos) {
        briefVos.forEach(e -> {
            e.setOperateList(Lists.newArrayList());

            for (OperateTypeEnum op : OperateTypeEnum.values()) {
                OperateEntity operate = new OperateEntity();
                operate.setOperateName(op.getMsg());
                operate.setEnable(true);
                operate.setOperateType(op.name());
                e.getOperateList().add(operate);
            }
        });
    }
}
