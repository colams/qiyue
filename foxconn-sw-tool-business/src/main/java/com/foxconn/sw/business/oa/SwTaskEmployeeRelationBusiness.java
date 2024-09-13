package com.foxconn.sw.business.oa;

import com.foxconn.sw.business.context.RequestContext;
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

import static com.foxconn.sw.data.constants.enums.TaskRoleFlagEnums.*;

@Component
public class SwTaskEmployeeRelationBusiness {

    @Autowired
    SwTaskEmployeeRelationExtensionMapper employeeRelationExtensionMapper;

    public boolean addRelationAtCreate(Integer taskID, String managerNo, List<String> watchers) {

        Map<String, SimpleRelation> roleMap = processEmployeeNo(RequestContext.getEmployeeNo(), managerNo, watchers);

        List<SwTaskEmployeeRelation> relations = employeeRelationExtensionMapper.selectByTaskID(taskID);
        relations = Optional.ofNullable(relations).orElse(Lists.newArrayList());

        int prevID = 0;
        List<SimpleRelation> values = roleMap.values().stream().sorted(Comparator.comparing(SimpleRelation::getOrderBy)).toList();
        for (int i = 0; i < values.size(); i++) {
            SimpleRelation simpleRelation = values.get(i);
            int relationID = insertRelation(taskID, simpleRelation, relations, prevID);
            if (i == 0) {
                prevID = relationID;
            }
        }

        List<Integer> deleteIds = new ArrayList<>();
        relations.forEach(e -> {
            if (!roleMap.containsKey(e.getEmployeeNo())) {
                deleteIds.add(e.getId());
            }
        });
        if (!CollectionUtils.isEmpty(deleteIds)) {
            employeeRelationExtensionMapper.deleteWatchRelation(deleteIds);
        }
        return true;
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

    private Map<String, SimpleRelation> processEmployeeNo(String employeeNo, String managerNo, List<String> watchers) {
        Map<String, SimpleRelation> map = new HashMap<>();
        boolean isSame = employeeNo.equalsIgnoreCase(managerNo);
        addRole(map, Lists.newArrayList(employeeNo), Proposer_Flag, isSame ? 1 : 0, 0);
        addRole(map, Lists.newArrayList(managerNo), Manager_Flag, 1, isSame ? 0 : 1);
        addRole(map, watchers, Watcher_Flag, 0, 2);
        return map;
    }

    private void addRole(Map<String, SimpleRelation> map,
                         List<String> employeeNos,
                         TaskRoleFlagEnums roleFlagEnums,
                         int active,
                         int orderBy) {
        if (CollectionUtils.isEmpty(employeeNos)) {
            return;
        }

        for (String employeeNo : employeeNos) {

            if (StringUtils.isEmpty(employeeNo)) {
                continue;
            }

            boolean hasKey = map.containsKey(employeeNo);
            SimpleRelation relation = map.getOrDefault(employeeNo, new SimpleRelation());
            relation.setActive(active);
            relation.setRoleFlag(roleFlagEnums.setFlag(relation.getRoleFlag()));
            relation.setEmployeeNo(employeeNo);
            if (!hasKey) {
                relation.setOrderBy(orderBy);
            }
            map.put(employeeNo, relation);
        }
    }

    public Integer assignTaskEmployee(String employeeNo, int taskID, TaskRoleFlagEnums roleFlagEnum) {

        SwTaskEmployeeRelation data = employeeRelationExtensionMapper.queryRelation(taskID, employeeNo);
        if (Objects.nonNull(data)) {
            SwTaskEmployeeRelation employeeRelation = new SwTaskEmployeeRelation();
            employeeRelation.setId(data.getId());
            employeeRelation.setRoleFlag(TaskRoleFlagEnums.setFlag(data.getRoleFlag(), roleFlagEnum));
            employeeRelationExtensionMapper.updateByPrimaryKeySelective(employeeRelation);
            return data.getId();
        }

        SwTaskEmployeeRelation current = employeeRelationExtensionMapper.queryRelation(taskID, RequestContext.getEmployeeNo());
        SwTaskEmployeeRelation employeeRelation = new SwTaskEmployeeRelation();
        employeeRelation.setRoleFlag(TaskRoleFlagEnums.initFlag(roleFlagEnum));
        employeeRelation.setEmployeeNo(employeeNo);
        employeeRelation.setPrevId(current.getId());
        employeeRelation.setTaskId(taskID);
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
