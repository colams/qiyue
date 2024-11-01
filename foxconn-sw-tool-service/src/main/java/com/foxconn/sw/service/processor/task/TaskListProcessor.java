package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.business.context.RequestContext;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.business.oa.SwTaskFollowBusiness;
import com.foxconn.sw.business.system.EmployeeBusiness;
import com.foxconn.sw.common.utils.LocalDateExtUtils;
import com.foxconn.sw.data.constants.enums.OperateTypeEnum;
import com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums;
import com.foxconn.sw.data.dto.PageEntity;
import com.foxconn.sw.data.dto.PageParams;
import com.foxconn.sw.data.dto.entity.acount.EmployeeVo;
import com.foxconn.sw.data.dto.entity.oa.TaskBriefListVo;
import com.foxconn.sw.data.dto.entity.oa.TaskParams;
import com.foxconn.sw.data.dto.entity.universal.OperateEntity;
import com.foxconn.sw.data.entity.SwEmployee;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import com.foxconn.sw.data.entity.SwTaskFollow;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import com.foxconn.sw.service.processor.utils.*;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static com.foxconn.sw.common.utils.constanst.DateTimePattern.yyyyMMdd;

@Component
public class TaskListProcessor {
    @Autowired
    CommonUserUtils userUtils;
    @Autowired
    SwTaskBusiness taskBusiness;
    @Autowired
    SwTaskFollowBusiness followBusiness;
    @Autowired
    EmployeeBusiness employeeBusiness;
    @Autowired
    SwTaskEmployeeRelationBusiness relationBusiness;

    public PageEntity<TaskBriefListVo> list(PageParams<TaskParams> taskParams) {
        String proposer = RequestContext.getEmployeeNo();
        taskParams.getParams().setCreate_e(processDate(taskParams.getParams().getCreate_e()));
        List<String> employeeNos = getQueryEmployee(proposer, taskParams.getParams().getIsTeam());
        List<SwTask> tasks = taskBusiness.listBriefVos(taskParams, employeeNos, proposer);
        List<TaskBriefListVo> briefListVos = processAfter(tasks);
        int totalCount = taskBusiness.getTotalCountByParams(taskParams.getParams(), employeeNos, proposer);
        PageEntity<TaskBriefListVo> voPageEntity = new PageEntity<>(totalCount, briefListVos);
        return voPageEntity;
    }

    private String processDate(String create_e) {
        if (StringUtils.isEmpty(create_e)) {
            return null;
        }
        return LocalDateExtUtils.toLocalDate(create_e).plusDays(1).format(DateTimeFormatter.ofPattern(yyyyMMdd));
    }

    private List<String> getQueryEmployee(String employeeNo, Integer isTeam) {
        if (Objects.nonNull(isTeam) && isTeam == 1) {
            return employeeBusiness.queryMembers(employeeNo).stream()
                    .map(e -> e.getEmployeeNo())
                    .collect(Collectors.toList());
        } else {
            return Lists.newArrayList(employeeNo);
        }
    }

    private List<TaskBriefListVo> processAfter(List<SwTask> tasks) {

        List<Integer> taskIDs = Optional.ofNullable(tasks)
                .orElse(Lists.newArrayList())
                .stream()
                .map(SwTask::getId)
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(taskIDs)) {
            return Lists.newArrayList();
        }

        List<SwTaskFollow> follows = followBusiness.queryFollow(taskIDs);
        Map<Integer, List<SwTaskFollow>> map = follows.stream().collect(Collectors.groupingBy(SwTaskFollow::getTaskId));

        Map<Integer, List<SwTaskEmployeeRelation>> relationsMap = relationBusiness.queryEmployeeRelation(taskIDs);

        return tasks.stream()
                .map(e -> mapBrief(e, map, relationsMap.get(e.getId())))
                .collect(Collectors.toList());
    }

    private TaskBriefListVo mapBrief(SwTask e,
                                     Map<Integer, List<SwTaskFollow>> map,
                                     List<SwTaskEmployeeRelation> relations) {
        TaskBriefListVo vo = new TaskBriefListVo();
        vo.setId(e.getId());
        vo.setTaskNo(e.getTaskNo());
        vo.setCategory(TaskCategoryUtils.processCategory(e.getTopCategory(), e.getCategory()));
        vo.setTitle(e.getTitle());
        vo.setProject(TaskProjectUtils.processProject(e.getProject()));
        vo.setLevelInfo(TaskLevelUtils.processLevel(e.getLevel()));
        vo.setProgressPercent(e.getProgressPercent());
        vo.setStatusInfoVo(TaskStatusUtils.processStatus(e.getStatus(), e.getRejectStatus()));

        vo.setDeadLine(e.getDeadLine());
        vo.setCreateTime(e.getCreateTime());

        vo.setRejectStatus(e.getRejectStatus());
        vo.setFollowStatus(getFollowStatus(map, e.getId()));
        vo.setProposer(employeeBusiness.selectEmployeeByENo(e.getProposerEid()).getName());
        vo.setProposerVo(map(employeeBusiness.selectEmployeeByENo(e.getProposerEid())));
        vo.setCollaboration(e.getCategory().equalsIgnoreCase("6-2"));

        Optional<SwTaskEmployeeRelation> optional = relations.stream()
                .filter(r -> r.getTaskId().equals(e.getId()) && r.getEmployeeNo().equalsIgnoreCase(RequestContext.getEmployeeNo()))
                .findFirst();
        if (optional.isPresent()) {
            String supervisorNo = "";
            List<EmployeeVo> supervisorVos = new ArrayList<>();

            if (TaskRoleFlagEnums.Manager_Flag.test(optional.get().getRoleFlag())) {
                List<SwTaskEmployeeRelation> nexts = relations.stream()
                        .filter(r -> r.getPrevId().equals(optional.get().getId()))
                        .collect(Collectors.toList());
                if (CollectionUtils.isEmpty(nexts)) {
                    SwEmployee ee = employeeBusiness.selectEmployeeByENo(optional.get().getEmployeeNo());
                    if (Objects.nonNull(ee)) {
                        supervisorNo = ee.getName();
                        supervisorVos = Lists.newArrayList(map(ee));
                    }
                } else {
                    supervisorNo = nexts.stream().map(r -> {
                                SwEmployee ee = employeeBusiness.selectEmployeeByENo(r.getEmployeeNo());
                                return Objects.nonNull(ee) ? ee.getName() : "";
                            })
                            .collect(Collectors.joining(","));
                    supervisorVos = nexts.stream().map(r -> {
                                SwEmployee ee = employeeBusiness.selectEmployeeByENo(r.getEmployeeNo());
                                return map(ee);
                            })
                            .filter(f -> Objects.nonNull(f))
                            .collect(Collectors.toList());
                }
            } else if (TaskRoleFlagEnums.Handler_Flag.test(optional.get().getRoleFlag())) {
                SwEmployee ee = employeeBusiness.selectEmployeeByENo(optional.get().getEmployeeNo());
                if (Objects.nonNull(ee)) {
                    supervisorNo = ee.getName();
                    supervisorVos = Lists.newArrayList(map(ee));
                }
            } else if (TaskRoleFlagEnums.Proposer_Flag.test(optional.get().getRoleFlag())) {
                List<SwTaskEmployeeRelation> nexts = relations.stream()
                        .filter(r -> TaskRoleFlagEnums.Manager_Flag.test(r.getRoleFlag()))
                        .filter(r -> r.getPrevId().equals(optional.get().getId()))
                        .collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(nexts)) {
                    supervisorNo = nexts.stream().map(r -> {
                                SwEmployee ee = employeeBusiness.selectEmployeeByENo(r.getEmployeeNo());
                                return Objects.nonNull(ee) ? ee.getName() : "";
                            })
                            .collect(Collectors.joining(","));
                    supervisorVos = nexts.stream().map(r -> {
                                SwEmployee ee = employeeBusiness.selectEmployeeByENo(r.getEmployeeNo());
                                return map(ee);
                            })
                            .collect(Collectors.toList());
                }
            } else if (TaskRoleFlagEnums.Watcher_Flag.test(optional.get().getRoleFlag())) {
                supervisorNo = relations.stream()
                        .filter(r -> r.getIsActive() == 1)
                        .map(r -> {
                            SwEmployee ee = employeeBusiness.selectEmployeeByENo(r.getEmployeeNo());
                            return Objects.nonNull(ee) ? ee.getName() : "";
                        })
                        .collect(Collectors.joining(","));
                supervisorVos = relations.stream()
                        .filter(r -> r.getIsActive() == 1)
                        .map(r -> {
                            SwEmployee ee = employeeBusiness.selectEmployeeByENo(r.getEmployeeNo());
                            return map(ee);
                        })
                        .collect(Collectors.toList());
            }
            vo.setSupervisor(supervisorNo);
            vo.setSupervisorVo(supervisorVos);
        }

        vo.setOperateList(processOperate(e, optional));

        return vo;
    }

    private EmployeeVo map(SwEmployee ee) {
        if (Objects.isNull(ee)) {
            return null;
        }
        EmployeeVo vo = new EmployeeVo();
        vo.setName(ee.getName());
        vo.setEmployeeNo(ee.getEmployeeNo());
        return vo;
    }

    private Integer getFollowStatus(Map<Integer, List<SwTaskFollow>> map, Integer taskID) {
        return map.getOrDefault(taskID, Lists.newArrayList()).size();
    }

    private List<OperateEntity> processOperate(SwTask e, Optional<SwTaskEmployeeRelation> optional) {
        List<OperateEntity> entityList = new ArrayList<>();

        for (OperateTypeEnum op : OperateTypeEnum.values()) {
            if (op.getPage().equalsIgnoreCase("list")) {
                OperateEntity operate = TaskOperateUtils.processOperate(e, op, optional);
                entityList.add(operate);
            }
        }
        return entityList;
    }
}
