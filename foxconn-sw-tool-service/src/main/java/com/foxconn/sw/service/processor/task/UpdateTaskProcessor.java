package com.foxconn.sw.service.processor.task;

import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.business.oa.SwTaskBusiness;
import com.foxconn.sw.business.oa.SwTaskEmployeeRelationBusiness;
import com.foxconn.sw.business.oa.SwTaskLogBusiness;
import com.foxconn.sw.business.oa.SwTaskProgressBusiness;
import com.foxconn.sw.common.utils.ObjectCompare;
import com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums;
import com.foxconn.sw.data.constants.enums.oa.TaskStatusEnums;
import com.foxconn.sw.data.dto.request.task.UpdateTaskParams;
import com.foxconn.sw.data.entity.SwTask;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import com.foxconn.sw.data.entity.SwTaskProgress;
import com.foxconn.sw.service.processor.user.CommonUserUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums.Watcher_Flag;

@Component
public class UpdateTaskProcessor {

    @Autowired
    CommonUserUtils userUtils;
    @Autowired
    SwTaskBusiness swTaskBusiness;
    @Autowired
    SwTaskLogBusiness taskLogBusiness;
    @Autowired
    SwTaskProgressBusiness progressBusiness;
    @Autowired
    SwTaskEmployeeRelationBusiness employeeRelationBusiness;

    private static final Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("deadLine", "任務截止時間");
        hashMap.put("description", "需求描述");
        hashMap.put("watcher", "關注人");
    }

    public boolean updateTask(UpdateTaskParams taskParams) {
        boolean result;
        SwTask old = swTaskBusiness.getTaskById(taskParams.getBriefTaskVo().getId());

        if (taskParams.getFieldInfo().equalsIgnoreCase("watcher")) {
            result = updateEmployeeRelation(taskParams.getBriefTaskVo().getId(), taskParams.getBriefTaskVo().getWatchers());
        } else {
            result = swTaskBusiness.updateTask(taskParams.getBriefTaskVo());
        }

        if (result) {
            Pair pair = ObjectCompare.compare(taskParams.getBriefTaskVo(), old, SwTask.class, taskParams.getFieldInfo());

            String content = "修改了" + hashMap.getOrDefault(taskParams.getFieldInfo(), taskParams.getFieldInfo());
            if (Objects.nonNull(pair) && !"description".equalsIgnoreCase(taskParams.getFieldInfo())) {
                content += String.format(" : %s > %s", pair.getRight(), pair.getLeft());
            }
            taskLogBusiness.addTaskLog(old.getId(), RequestContext.getEmployeeNo(), content);

            SwTaskProgress progress = new SwTaskProgress();
            progress.setTaskId(old.getId());
            progress.setOperateEid(RequestContext.getEmployeeNo());
            progress.setProgress(0);
            progress.setContent(content);
            progressBusiness.addProcessInfo(progress);
        }

        return result;
    }

    private boolean updateEmployeeRelation(Integer taskID, List<String> watchers) {
        List<SwTaskEmployeeRelation> relations = employeeRelationBusiness.getRelationsByTaskId(taskID);

        SwTaskEmployeeRelation primary = relations
                .stream()
                .filter(e -> TaskRoleFlagEnums.Proposer_Flag.test(e.getRoleFlag()))
                .findAny()
                .get();

        List<SwTaskEmployeeRelation> updateRelations = updateRelation(relations, watchers, taskID, primary.getId());
        List<SwTaskEmployeeRelation> deleteRelations = deleteRelation(relations, watchers);

        List<SwTaskEmployeeRelation> relationList = Lists.newArrayList(updateRelations);
        relationList.addAll(deleteRelations);
        return employeeRelationBusiness.insertOrUpdate(relationList);
    }

    private List<SwTaskEmployeeRelation> deleteRelation(List<SwTaskEmployeeRelation> allRelations, List<String> watchers) {
        List<SwTaskEmployeeRelation> relations = new ArrayList<>();

        List<SwTaskEmployeeRelation> watcherEmployees = allRelations
                .stream()
                .filter(e -> !watchers.contains(e.getEmployeeNo()))
                .filter(e -> Watcher_Flag.test(e.getRoleFlag()))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(watcherEmployees)) {
            return Lists.newArrayList();
        }

        for (SwTaskEmployeeRelation relation : watcherEmployees) {
            if (!relation.getRoleFlag().equals(Watcher_Flag.initFlag())) {
                relation.setRoleFlag(Watcher_Flag.removeFlag(relation.getRoleFlag()));
            } else {
                relation.setIsDelete(1);
            }
            relations.add(relation);
        }
        return relations;
    }

    private List<SwTaskEmployeeRelation> updateRelation(List<SwTaskEmployeeRelation> allRelations, List<String> watchers, Integer taskID, Integer primaryId) {
        List<SwTaskEmployeeRelation> relations = new ArrayList<>();
        for (String watcher : watchers) {
            SwTaskEmployeeRelation relation = query(allRelations, watcher, taskID, primaryId);

            if (Objects.nonNull(relation.getId()) && relation.getId() > 0) {
                if (!Watcher_Flag.test(relation.getRoleFlag())) {
                    relation.setRoleFlag(Watcher_Flag.setFlag(relation.getRoleFlag()));
                    relations.add(relation);
                }
            } else {
                relations.add(relation);
            }
        }
        return relations;

    }

    private SwTaskEmployeeRelation query(List<SwTaskEmployeeRelation> relations, String employeeNo, Integer taskID, Integer primaryId) {
        return relations
                .stream()
                .filter(e -> e.getEmployeeNo().equalsIgnoreCase(employeeNo))
                .findFirst()
                .orElse(initRelation(taskID, employeeNo, primaryId));
    }

    private SwTaskEmployeeRelation initRelation(Integer taskID, String watcher, Integer primaryId) {
        SwTaskEmployeeRelation relation = new SwTaskEmployeeRelation();
        relation.setTaskId(taskID);
        relation.setEmployeeNo(watcher);
        relation.setPrevId(primaryId);
        relation.setRoleFlag(Watcher_Flag.initFlag());
        return relation;
    }

    public boolean delete(Integer taskID) {
        boolean result = swTaskBusiness.updateTaskStatus(taskID, TaskStatusEnums.DELETE);
        if (result) {
            taskLogBusiness.addTaskLog(taskID, RequestContext.getEmployeeNo(), "刪除任務");
            SwTaskProgress progress = new SwTaskProgress();
            progress.setTaskId(taskID);
            progress.setOperateEid(RequestContext.getEmployeeNo());
            progress.setContent("刪除任務");
            progressBusiness.addProcessInfo(progress);
        }
        return result;
    }
}
