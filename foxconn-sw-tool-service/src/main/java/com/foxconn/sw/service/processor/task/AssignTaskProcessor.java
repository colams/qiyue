package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.data.context.RequestContext;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.UserInfo;
import com.foxconn.sw.data.dto.entity.task.TaskAssignParams;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums.Manager_Flag;

/**
 * 转交 重新分派任务
 */
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
            String content = String.format("將任務分派给了 %s; %s",
                    String.format("%s(%s)", employee.getName(), employee.getEmployeeNo()),
                    Optional.ofNullable(data.getContent()).orElse(""));
            String operator = String.format("%s(%s)", user.getEmployeeName(), user.getEmployeeNo());
            addProcessInfo(data, user, content);
            addTaskLog(data, operator, content);
            processTaskEmployeeRelation(data.getAssignEid(), data.getTaskId());
        }
        return result;
    }

    private void processTaskEmployeeRelation(String assignNo, Integer taskID) {
        SwTaskEmployeeRelation currentRelation = employeeRelationBusiness
                .queryRelationByTaskAndEno(taskID, RequestContext.getEmployeeNo());
        SwTaskEmployeeRelation employeeRelation = employeeRelationBusiness
                .queryRelationByTaskAndEno(taskID, assignNo);

        List<SwTaskEmployeeRelation> updateRelations = new ArrayList<>();
        if (Objects.nonNull(currentRelation)) {
            SwTaskEmployeeRelation relation = new SwTaskEmployeeRelation();
            relation.setId(currentRelation.getId());
            relation.setIsActive(0);
            updateRelations.add(relation);
        }

        if (Objects.isNull(employeeRelation)) {
            employeeRelation = new SwTaskEmployeeRelation();
        }
        employeeRelation.setRoleFlag(TaskRoleFlagEnums.setFlag(employeeRelation.getRoleFlag(), Manager_Flag));
        employeeRelation.setEmployeeNo(assignNo);
        employeeRelation.setPrevId(currentRelation.getId());
        employeeRelation.setTaskId(taskID);
        employeeRelation.setIsActive(1);
        updateRelations.add(employeeRelation);

        employeeRelationBusiness.insertOrUpdate(updateRelations);
    }

    private boolean addTaskLog(TaskAssignParams data, String operator, String content) {
        return taskLogBusiness.addTaskLog(data.getTaskId(), operator, content);
    }

    private boolean addProcessInfo(TaskAssignParams data, UserInfo user, String content) {
        SwTaskProgress progress = new SwTaskProgress();
        progress.setTaskId(data.getTaskId());
        progress.setOperateEid(user.getEmployeeNo());
        progress.setContent(content);
        return taskProgressBusiness.addProcessInfo(progress)>0;
    }
}
