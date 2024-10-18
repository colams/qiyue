package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.oa.TaskAssignParams;
import com.foxconn.sw.data.entity.SwEmployee;
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
    @Autowired
    private EmployeeBusiness employeeBusiness;
    @Autowired
    private SwTaskEmployeeRelationBusiness employeeRelationBusiness;

    /**
     * 分派 工作任务
     *
     * @param data
     * @param head
     * @return
     */
    public boolean assignTask(TaskAssignParams data, Header head) {
        UserInfo user = commonUserUtils.queryUserInfo(head.getToken());

        SwEmployee employee = employeeBusiness.selectEmployeeByENo(data.getAssignEid());

        boolean result = taskBusiness.assignTask(data.getTaskId(), data.getAssignEid());
        if (result) {
            String content = String.format("%s(%s) 任务分派给了 %s; %s",
                    user.getEmployeeName(),
                    user.getEmployeeNo(),
                    String.format("%s(%s)", employee.getName(), employee.getEmployeeNo()),
                    Optional.ofNullable(data.getContent()).orElse(""));
            String operator = String.format("%s(%s)", user.getEmployeeName(), user.getEmployeeNo());
            addProcessInfo(data, user, content);
            addTaskLog(data, operator, content);
            employeeRelationBusiness.assignTaskEmployee(data.getAssignEid(), data.getTaskId(), TaskRoleFlagEnums.Manager_Flag);
        }
        return result;
    }

    private boolean addTaskLog(TaskAssignParams data, String operator, String content) {
        return taskLogBusiness.addTaskLog(data.getTaskId(), operator, content);
    }

    private boolean addProcessInfo(TaskAssignParams data, UserInfo user, String content) {
        SwTaskProgress progress = new SwTaskProgress();
        progress.setTaskId(data.getTaskId());
        progress.setOperateEid(user.getEmployeeNo());
        progress.setContent(content);
        return taskProgressBusiness.addProcessInfo(progress);
    }
}
