package com.foxconn.sw.service.processor.oa;

import com.foxconn.sw.business.SwAppendResourceBusiness;
import com.foxconn.sw.business.mapper.AppendResourceMapper;
import com.foxconn.sw.business.mapper.SwTaskLogMapper;
import com.foxconn.sw.business.mapper.TaskMapper;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.data.constants.enums.retcode.OAExceptionCode;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.oa.TaskDetailVo;
import com.foxconn.sw.data.dto.entity.oa.TaskEntityVo;
import com.foxconn.sw.data.dto.entity.oa.TaskLogVo;
import com.foxconn.sw.data.dto.entity.oa.TaskProgressVo;
import com.foxconn.sw.data.dto.entity.universal.IntegerParams;
import com.foxconn.sw.data.entity.SwAppendResource;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskLog;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.service.processor.oa.utils.TaskCategoryUtils;
import com.foxconn.sw.service.processor.oa.utils.TaskLevelUtils;
import com.foxconn.sw.service.processor.oa.utils.TaskProjectUtils;
import com.foxconn.sw.service.processor.oa.utils.TaskStatusUtils;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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

    public TaskEntityVo detail(IntegerParams data, Header head) {
        String employeeID = commonUserUtils.getEmployeeNo(head.getToken());

        TaskDetailVo detailVo = getDetailVo(data.getParams(), employeeID);

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

    private TaskDetailVo getDetailVo(Integer taskId, String employeeID) {
        SwTask task = taskBusiness.getTaskById(taskId);

        boolean isPermission = employeeID.equalsIgnoreCase(task.getHandleEid())
                || employeeID.equalsIgnoreCase(task.getManagerEid())
                || employeeID.equalsIgnoreCase(task.getProposerEid());
        if (!isPermission) {
            throw new BizException(OAExceptionCode.NO_PERMISSION_EXCEPTION);
        }

        TaskDetailVo taskDetailVo = TaskMapper.INSTANCE.toSwTaskDetailVo(task);
        taskDetailVo.setLevelInfoVo(TaskLevelUtils.processLevel(taskDetailVo.getLevel()));
        taskDetailVo.setStatusInfoVo(TaskStatusUtils.processStatus(employeeID, taskDetailVo.getStatus(), taskDetailVo.getManagerEid(), taskDetailVo.getHandleEid()));
        taskDetailVo.setProject(TaskProjectUtils.processProject(taskDetailVo.getProject()));
        taskDetailVo.setCategory(TaskCategoryUtils.processCategory(taskDetailVo.getTopCategory(), taskDetailVo.getCategory()));
        processEmployee(taskDetailVo);
        return taskDetailVo;
    }

    private void processEmployee(TaskDetailVo taskDetailVo) {
        List<String> employeeNos = Lists.newArrayList(taskDetailVo.getProposerEid(), taskDetailVo.getManagerEid(), taskDetailVo.getHandleEid());
        List<SwEmployee> employees = employeeBusiness.selectEmployeeByENos(employeeNos);
        if (!CollectionUtils.isEmpty(employees)) {
            taskDetailVo.setProposerEid(employees.stream().filter(e -> e.getEmployeeNo().equalsIgnoreCase(taskDetailVo.getProposerEid())).findFirst().get().getName());
            taskDetailVo.setManagerEid(employees.stream().filter(e -> e.getEmployeeNo().equalsIgnoreCase(taskDetailVo.getManagerEid())).findFirst().map(e -> e.getName()).orElse(""));
            taskDetailVo.setHandleEid(employees.stream().filter(e -> e.getEmployeeNo().equalsIgnoreCase(taskDetailVo.getHandleEid())).findFirst().map(e -> e.getName()).orElse(""));
        }
    }


}
