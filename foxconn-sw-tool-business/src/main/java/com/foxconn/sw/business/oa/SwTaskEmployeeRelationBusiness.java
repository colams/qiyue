package com.foxconn.sw.business.oa;

import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelation;
import com.foxconn.sw.data.entity.SwTaskEmployeeRelationExample;
import com.foxconn.sw.data.mapper.extension.oa.SwTaskEmployeeRelationExtensionMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums.*;

@Component
public class SwTaskEmployeeRelationBusiness {

    @Autowired
    SwTaskEmployeeRelationExtensionMapper employeeRelationExtensionMapper;

    public boolean addRelationAtCreate(Integer taskID, List<String> managerNos, List<String> watchers) {
        List<SimpleRelation> simpleRelations = processTaskRole(RequestContext.getEmployeeNo(), managerNos, watchers);
        List<SwTaskEmployeeRelation> relations = employeeRelationExtensionMapper.selectByTaskID(taskID);
        int prevID = 0;
        for (int i = 0; i < simpleRelations.size(); i++) {
            SimpleRelation simpleRelation = simpleRelations.get(i);
            int relationID = insertRelation(taskID, simpleRelation, relations, prevID);
            if (i == 0) {
                prevID = relationID;
            }
        }

        List<Integer> deleteIds = new ArrayList<>();
        relations.forEach(e -> {
            if (!simpleRelations.stream().anyMatch(f -> f.getEmployeeNo().equalsIgnoreCase(e.getEmployeeNo()))) {
                deleteIds.add(e.getId());
            }
        });
        if (!CollectionUtils.isEmpty(deleteIds)) {
            employeeRelationExtensionMapper.deleteWatchRelation(deleteIds);
        }
        return true;
    }

    public List<SimpleRelation> processTaskRole(String employeeNo,
                                                List<String> managerNos,
                                                List<String> watchers) {
        Map<String, SimpleRelation> taskRoleMap = new HashMap<>();
        Set<String> eNoSet = new HashSet<>();

        Lists.newArrayList(employeeNo).forEach(e -> {
            if (StringUtils.isEmpty(e)) {
                return;
            }

            if (eNoSet.contains(e)) {
                return;
            }
            processMap(taskRoleMap, e, Proposer_Flag, 0, 1);
        });

        Optional.ofNullable(managerNos).orElse(Lists.newArrayList()).forEach(e -> {
            if (StringUtils.isEmpty(e)) {
                return;
            }

            if (eNoSet.contains(e)) {
                return;
            }
            processMap(taskRoleMap, e, Manager_Flag, 1, 2);
        });

        Optional.ofNullable(watchers).orElse(Lists.newArrayList()).forEach(e -> {
            if (StringUtils.isEmpty(e)) {
                return;
            }

            if (eNoSet.contains(e)) {
                return;
            }
            processMap(taskRoleMap, e, Watcher_Flag, 0, 3);
        });

        return taskRoleMap.values().stream().sorted(Comparator.comparing(SimpleRelation::getOrderBy)).toList();
    }

    private void processMap(Map<String, SimpleRelation> map, String eno, TaskRoleFlagEnums taskRoleFlag, int active, int orderBy) {
        SimpleRelation relation = map.getOrDefault(eno, new SimpleRelation());
        relation.setActive(active);
        relation.setRoleFlag(taskRoleFlag.setFlag(relation.getRoleFlag()));
        relation.setEmployeeNo(eno);
        if (relation.getOrderBy() == 0) {
            relation.setOrderBy(orderBy);
        }
        map.put(eno, relation);
    }

    public boolean deleteTaskRelation(int id) {
        SwTaskEmployeeRelation relation = new SwTaskEmployeeRelation();
        relation.setId(id);
        relation.setIsDelete(1);
        return employeeRelationExtensionMapper.updateByPrimaryKeySelective(relation) > 0;
    }


    public SwTaskEmployeeRelation selectByPrimaryKey(int id) {
        return employeeRelationExtensionMapper.selectByPrimaryKey(id);
    }


    private Integer insertRelation(Integer taskID,
                                   SimpleRelation simpleRelation,
                                   List<SwTaskEmployeeRelation> relations,
                                   int prevID) {
        Integer relationID;

        Optional<SwTaskEmployeeRelation> optional = relations.stream()
                .filter(e -> e.getEmployeeNo().equalsIgnoreCase(simpleRelation.getEmployeeNo()))
                .findFirst();

        if (optional.isPresent()) {
            SwTaskEmployeeRelation relation = optional.get();
            if (relation.getRoleFlag() != simpleRelation.getRoleFlag()
                    || relation.getPrevId() != prevID
                    || relation.getIsActive() != simpleRelation.getActive()) {
                SwTaskEmployeeRelation updateRelation = new SwTaskEmployeeRelation();
                updateRelation.setPrevId(prevID);
                updateRelation.setRoleFlag(simpleRelation.getRoleFlag());
                updateRelation.setIsActive(simpleRelation.getActive());
                updateRelation.setId(relation.getId());
                employeeRelationExtensionMapper.updateByPrimaryKeySelective(updateRelation);
            }
            relationID = relation.getId();
        } else {
            SwTaskEmployeeRelation insertRelation = new SwTaskEmployeeRelation();
            insertRelation.setRoleFlag(simpleRelation.getRoleFlag());
            insertRelation.setTaskId(taskID);
            insertRelation.setEmployeeNo(simpleRelation.getEmployeeNo());
            insertRelation.setIsActive(simpleRelation.getActive());
            insertRelation.setPrevId(prevID);
            employeeRelationExtensionMapper.insertSelective(insertRelation);
            relationID = insertRelation.getId();
        }
        return relationID;
    }

    public SwTaskEmployeeRelation queryRelationByTaskAndEno(Integer taskID, String employeeNo) {
        return employeeRelationExtensionMapper.queryRelation(taskID, employeeNo);
    }

    public Integer assignTaskEmployee(String employeeNo, int taskID, TaskRoleFlagEnums roleFlagEnum) {

        SwTaskEmployeeRelation data = employeeRelationExtensionMapper.queryRelation(taskID, employeeNo);
        if (Objects.nonNull(data)) {
            SwTaskEmployeeRelation employeeRelation = new SwTaskEmployeeRelation();
            employeeRelation.setId(data.getId());
            employeeRelation.setRoleFlag(TaskRoleFlagEnums.setFlag(data.getRoleFlag(), roleFlagEnum));
            employeeRelation.setIsActive(1);
            employeeRelationExtensionMapper.updateByPrimaryKeySelective(employeeRelation);
            return data.getId();
        }

        SwTaskEmployeeRelation current = employeeRelationExtensionMapper.queryRelation(taskID, RequestContext.getEmployeeNo());
        SwTaskEmployeeRelation employeeRelation = new SwTaskEmployeeRelation();
        employeeRelation.setRoleFlag(TaskRoleFlagEnums.initFlag(roleFlagEnum));
        employeeRelation.setEmployeeNo(employeeNo);
        employeeRelation.setPrevId(current.getId());
        employeeRelation.setTaskId(taskID);
        employeeRelation.setIsActive(1);
        employeeRelationExtensionMapper.insertSelective(employeeRelation);
        return employeeRelation.getId();
    }

    public Integer acceptTaskEmployee(int taskID) {
        String employeeNo = RequestContext.getEmployeeNo();
        return assignTaskEmployee(employeeNo, taskID, TaskRoleFlagEnums.Handler_Flag);
    }

    public List<SwTaskEmployeeRelation> getRelationsByTaskId(Integer taskID) {
        SwTaskEmployeeRelationExample example = new SwTaskEmployeeRelationExample();
        SwTaskEmployeeRelationExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskID);
        criteria.andIsDeleteEqualTo(0);
        List<SwTaskEmployeeRelation> relations = employeeRelationExtensionMapper.selectByExample(example);
        return Optional.ofNullable(relations).orElse(Lists.newArrayList());
    }

    public List<SwTaskEmployeeRelation> getRelationsByTaskIdAndRole(Integer taskID, TaskRoleFlagEnums taskRole) {
        List<SwTaskEmployeeRelation> relations = getRelationsByTaskId(taskID);
        return relations.stream().filter(e -> taskRole.test(e.getRoleFlag())).collect(Collectors.toList());
    }

    public Map<Integer, List<SwTaskEmployeeRelation>> queryEmployeeRelation(List<Integer> taskIDs) {
        SwTaskEmployeeRelationExample example = new SwTaskEmployeeRelationExample();
        SwTaskEmployeeRelationExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdIn(taskIDs);
        criteria.andIsDeleteEqualTo(0);
        List<SwTaskEmployeeRelation> relations = employeeRelationExtensionMapper.selectByExample(example);
        relations = Optional.ofNullable(relations).orElse(Lists.newArrayList());
        return relations.stream().collect(Collectors.groupingBy(SwTaskEmployeeRelation::getTaskId));
    }

    public boolean insertOrUpdate(List<SwTaskEmployeeRelation> relationList) {
        relationList.forEach(e -> {
            SwTaskEmployeeRelation relation = new SwTaskEmployeeRelation();
            relation.setId(e.getId());
            relation.setRoleFlag(e.getRoleFlag());
            relation.setEmployeeNo(e.getEmployeeNo());
            relation.setIsDelete(e.getIsDelete());
            relation.setPrevId(e.getPrevId());
            relation.setTaskId(e.getTaskId());
            relation.setIsActive(e.getIsActive());
            relation.setIsInspector(e.getIsInspector());
            if (Objects.nonNull(e.getId()) && e.getId() > 0) {
                employeeRelationExtensionMapper.updateByPrimaryKeySelective(relation);
            } else {
                employeeRelationExtensionMapper.insertSelective(relation);
            }
        });
        return true;
    }

    public class SimpleRelation {
        private String employeeNo;
        private int roleFlag;
        private int active;
        private int orderBy;

        public String getEmployeeNo() {
            return employeeNo;
        }

        public void setEmployeeNo(String employeeNo) {
            this.employeeNo = employeeNo;
        }

        public int getRoleFlag() {
            return roleFlag;
        }

        public void setRoleFlag(int roleFlag) {
            this.roleFlag = roleFlag;
        }

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public int getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(int orderBy) {
            this.orderBy = orderBy;
        }
    }
}
