package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.business.mapper.AppendResourceMapper;
import com.foxconn.sw.business.mapper.SwTaskLogMapper;
import com.foxconn.sw.business.mapper.TaskMapper;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.constants.enums.OperateTypeEnum;
import com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums;
import com.foxconn.sw.data.constants.enums.retcode.OAExceptionCode;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.oa.TaskDetailVo;
import com.foxconn.sw.data.dto.entity.oa.TaskEntityVo;
import com.foxconn.sw.data.dto.entity.oa.TaskLogVo;
import com.foxconn.sw.data.dto.entity.oa.TaskProgressVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.dto.entity.universal.OperateEntity;
import com.foxconn.sw.data.entity.*;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import com.foxconn.sw.service.processor.utils.*;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TaskDetailProcessor {

    @Autowired
    SwTaskBusiness taskBusiness;
    @Autowired
    SwTaskLogBusiness taskLogBusiness;
    @Autowired
    SwTaskProgressBusiness taskProgressBusiness;
    @Autowired
    SwAppendResourceBusiness swAppendResourceBusiness;
    @Autowired
    CommonUserUtils commonUserUtils;
    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    SwTaskEmployeeRelationBusiness relationBusiness;

    public TaskEntityVo detail(IntegerParams data, Header head) {
        String employeeNo = RequestContext.getEmployeeNo();
        TaskDetailVo detailVo = getDetailVo(data.getParams(), employeeNo);

        if (Objects.isNull(detailVo)) {
            throw new BizException(OAExceptionCode.TASK_ERROR_EXCEPTION);
        }

        List<TaskProgressVo> taskProgressVos = getTaskProgressVos(data.getParams());
        List<TaskLogVo> taskLogVos = getTaskLogVos(data.getParams());


        TaskEntityVo taskEntityVo = new TaskEntityVo();
        taskEntityVo.setTaskDetailVo(detailVo);
        taskEntityVo.setTaskProgressVos(taskProgressVos);
        taskEntityVo.setTaskLogVos(taskLogVos);
        return taskEntityVo;
    }

    private List<TaskProgressVo> getTaskProgressVos(Integer taskId) {
        List<TaskProgressVo> taskProgressVos = taskProgressBusiness.selectTaskProcess(taskId);

        if (!CollectionUtils.isEmpty(taskProgressVos)) {
            List<Integer> resourceIdList = taskProgressVos
                    .stream()
                    .filter(e -> !CollectionUtils.isEmpty(e.getResourceIds()))
                    .map(e -> e.getResourceIds())
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

            if (!CollectionUtils.isEmpty(resourceIdList)) {
                var result = swAppendResourceBusiness.getAppendResources(resourceIdList);
                if (!CollectionUtils.isEmpty(result)) {
                    var map = result
                            .stream()
                            .collect(Collectors.toMap(SwAppendResource::getId,
                                    AppendResourceMapper.INSTANCE::toAppendResource));
                    taskProgressVos.forEach(e -> {
                        if (!CollectionUtils.isEmpty(e.getResourceIds())) {
                            e.getResourceIds().forEach(id -> {
                                if (Objects.isNull(e.getResources())) {
                                    e.setResources(Lists.newArrayList());
                                }
                                e.getResources().add(map.get(id));
                            });
                        }
                    });
                }
            }
        }

        return taskProgressVos;
    }

    private List<TaskLogVo> getTaskLogVos(Integer taskId) {
        List<SwTaskLog> taskLogs = taskLogBusiness.selectLogs(taskId);
        List<TaskLogVo> taskLogVos = SwTaskLogMapper.INSTANCE.toTaskLogVos(taskLogs);
        return taskLogVos;
    }

    private TaskDetailVo getDetailVo(Integer taskId, String employeeNo) {

        List<SwTaskEmployeeRelation> relations = relationBusiness.getRelationsByTaskId(taskId);

        SwTask task = taskBusiness.getTaskById(taskId);
        TaskDetailVo taskDetailVo = TaskMapper.INSTANCE.toSwTaskDetailVo(task);
        taskDetailVo.setLevelInfoVo(TaskLevelUtils.processLevel(taskDetailVo.getLevel()));
        taskDetailVo.setStatusInfoVo(TaskStatusUtils.processStatus(taskDetailVo.getStatus(), taskDetailVo.getRejectStatus()));
        taskDetailVo.setProject(TaskProjectUtils.processProject(taskDetailVo.getProject()));
        taskDetailVo.setCategory(TaskCategoryUtils.processCategory(taskDetailVo.getTopCategory(), taskDetailVo.getCategory()));
        taskDetailVo.setCategoryCode(task.getCategory());
        taskDetailVo.setOperateList(processOperate(taskDetailVo, employeeNo, relations));
        taskDetailVo.setCollaboration(task.getCategory().equalsIgnoreCase("6-2"));
        taskDetailVo.setFinishTime(task.getFinishTime());
        processEmployee(taskDetailVo, relations);
        return taskDetailVo;
    }

    private List<OperateEntity> processOperate(TaskDetailVo taskDetailVo, String employeeNo, List<SwTaskEmployeeRelation> relations) {
        List<OperateEntity> entityList = new ArrayList<>();
        for (OperateTypeEnum op : OperateTypeEnum.values()) {
            if (op.getPage().equalsIgnoreCase("detail")) {
                OperateEntity operate = TaskOperateUtils.processDetailOperate(employeeNo, taskDetailVo, op, relations);
                if (Objects.nonNull(operate)) {
                    entityList.add(operate);
                }
            }
        }
        return entityList;
    }

    private void processEmployee(TaskDetailVo taskDetailVo, List<SwTaskEmployeeRelation> relations) {
        List<String> employeeNos = relations.stream().map(e -> e.getEmployeeNo()).collect(Collectors.toList());

        String proposeNo = relations.stream()
                .filter(e -> TaskRoleFlagEnums.checkFlag(e.getRoleFlag(), TaskRoleFlagEnums.Proposer_Flag))
                .map(SwTaskEmployeeRelation::getEmployeeNo)
                .findFirst().orElse("");

        List<String> managerNos = relations.stream()
                .filter(e -> TaskRoleFlagEnums.checkFlag(e.getRoleFlag(), TaskRoleFlagEnums.Manager_Flag))
                .map(SwTaskEmployeeRelation::getEmployeeNo)
                .collect(Collectors.toList());

        String handlerNo = relations.stream()
                .filter(e -> TaskRoleFlagEnums.checkFlag(e.getRoleFlag(), TaskRoleFlagEnums.Handler_Flag))
                .map(SwTaskEmployeeRelation::getEmployeeNo)
                .findFirst().orElse("");

        List<String> watcherNos = relations.stream()
                .filter(e -> TaskRoleFlagEnums.checkFlag(e.getRoleFlag(), TaskRoleFlagEnums.Watcher_Flag))
                .map(SwTaskEmployeeRelation::getEmployeeNo)
                .collect(Collectors.toList());


        List<SwEmployee> employees = employeeBusiness.selectEmployeeByENos(employeeNos);
        if (!CollectionUtils.isEmpty(employees)) {
            taskDetailVo.setProposer(employees.stream()
                    .filter(e -> e.getEmployeeNo().equalsIgnoreCase(proposeNo))
                    .map(e -> toEmployeeVo(e))
                    .findFirst()
                    .orElse(null));
            taskDetailVo.setManager(employees.stream()
                    .filter(e -> StringUtils.isNotEmpty(e.getEmployeeNo()) && managerNos.contains(e.getEmployeeNo()))
                    .map(e -> toEmployeeVo(e))
                    .collect(Collectors.toList()));
            taskDetailVo.setHandle(employees.stream()
                    .filter(e -> e.getEmployeeNo().equalsIgnoreCase(handlerNo))
                    .findFirst()
                    .map(e -> toEmployeeVo(e))
                    .orElse(null));

            taskDetailVo.setWatchers(employees.stream()
                    .filter(e -> StringUtils.isNotEmpty(e.getEmployeeNo()) && watcherNos.contains(e.getEmployeeNo()))
                    .map(e -> toEmployeeVo(e))
                    .collect(Collectors.toList()));
        }
    }

    private EmployeeVo toEmployeeVo(SwEmployee e) {
        EmployeeVo vo = new EmployeeVo();
        vo.setEmployeeNo(e.getEmployeeNo());
        vo.setName(e.getName());
        return vo;
    }


}
