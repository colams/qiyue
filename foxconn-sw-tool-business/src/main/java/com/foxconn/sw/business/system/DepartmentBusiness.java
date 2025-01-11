package com.foxconn.sw.business.system;

import com.foxconn.sw.common.context.RequestContext;
import com.foxconn.sw.common.utils.IntegerExtUtils;
import com.foxconn.sw.data.dto.entity.system.DepartmentVo;
import com.foxconn.sw.data.entity.SwDepartment;
import com.foxconn.sw.data.entity.SwDepartmentExample;
import com.foxconn.sw.data.exception.BizException;
import com.foxconn.sw.data.mapper.extension.system.DepartmentExtensionMapper;
import com.github.houbb.opencc4j.util.ZhConverterUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DepartmentBusiness {

    @Autowired
    private DepartmentExtensionMapper departmentExtensionMapper;

    public Map<Integer, DepartmentVo> getDepartMap() {
        List<DepartmentVo> departmentVos = departmentExtensionMapper.getDepartList();
        return Optional.ofNullable(departmentVos)
                .orElse(Lists.newArrayList())
                .stream()
                .collect(Collectors.toMap(DepartmentVo::getId, e -> e));
    }

    public List<DepartmentVo> getDepartList() {
        List<DepartmentVo> departmentVos = departmentExtensionMapper.getDepartList();
        departmentVos.forEach(e -> {
            e.setName(ZhConverterUtil.convertToTraditional(e.getName()));
        });
        return departmentVos;
    }

    public List<DepartmentVo> getTreeDepartmentVos() {
        List<DepartmentVo> vos = getDepartList();
        List<DepartmentVo> treeDepartmentVos = buildDepartmentVoTree(vos);
        return treeDepartmentVos;
    }


    private List<DepartmentVo> buildDepartmentVoTree(List<DepartmentVo> vos) {
        Map<Integer, DepartmentVo> departmentVoMap = vos.stream()
                .collect(Collectors.toMap(DepartmentVo::getId, e -> e));
        List<DepartmentVo> rootMenus = buildTree(departmentVoMap);
        return rootMenus;
    }

    private List<DepartmentVo> buildTree(Map<Integer, DepartmentVo> departmentVoMap) {
        List<DepartmentVo> rootDeparts = new ArrayList<>();

        for (DepartmentVo departmentVo : departmentVoMap.values()) {
            Integer parentId = departmentVo.getParentId();
            if (!IntegerExtUtils.isPk(parentId)) {
                rootDeparts.add(departmentVo);
                buildSubTree(departmentVo, departmentVoMap);
            }
        }

        return rootDeparts;
    }

    private void buildSubTree(DepartmentVo parentMenu, Map<Integer, DepartmentVo> menuMap) {
        if (parentMenu.getChildren() == null) {
            parentMenu.setChildren(new ArrayList<>());
        }

        for (DepartmentVo menu : menuMap.values()) {
            Integer parentId = menu.getParentId();
            if (parentId != null && parentId.equals(parentMenu.getId())) {
                parentMenu.getChildren().add(menu);
                buildSubTree(menu, menuMap);
            }
        }
    }

    public List<Integer> getAllSubDepartID(String searchKey) {
        List<DepartmentVo> departmentVos = getDepartList();
        return getDepartmentIds(departmentVos, NumberUtils.toInt(searchKey), 0);
    }

    public List<Integer> getSubDepartID(Integer searchDeptId) {
        if (Objects.isNull(searchDeptId)) {
            return Lists.newArrayList();
        }
        List<DepartmentVo> departmentVos = getDepartList();
        return getDepartmentIds(departmentVos, searchDeptId, 0);
    }


    private List<Integer> getDepartmentIds(List<DepartmentVo> vos, Integer currId, int type) {
        List<Integer> result = new ArrayList<>();

        List<Integer> subVoIds = vos
                .stream()
                .filter(e -> type == 0 ? e.getId().equals(currId) : e.getParentId().equals(currId))
                .map(DepartmentVo::getId)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(subVoIds)) {
            return result;
        }

        subVoIds.forEach(e -> {
            List<Integer> ids = getDepartmentIds(vos, e, 1);
            result.addAll(ids);
        });
        result.addAll(subVoIds);
        return result;
    }

    public SwDepartment getDepartment(Integer departmentId) {
        List<SwDepartment> departments = getDepartment();
        SwDepartment department = departments.stream().filter(e -> e.getId().equals(departmentId)).findFirst().orElse(null);
        if (Objects.isNull(department)) {
            throw new BizException(4, "查找部門失敗");
        }
        return department;
    }

    public List<SwDepartment> getDepartment(String employeeNo) {
        List<SwDepartment> departments = getDepartment();
        List<SwDepartment> department = departments.stream()
                .filter(e -> employeeNo.equalsIgnoreCase(e.getManagerNo()))
                .collect(Collectors.toList());
        return Optional.ofNullable(department).orElse(Lists.newArrayList());
    }

    public List<SwDepartment> getDepartment() {
        SwDepartmentExample example = new SwDepartmentExample();
        SwDepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        List<SwDepartment> departmentList = departmentExtensionMapper.selectByExample(example);
        return departmentList;
    }

    public List<Integer> getMangeDepart(String employeeNo) {
        List<SwDepartment> departments = getDepartment();
        List<SwDepartment> directDepartments = getDepartment(employeeNo);
        if (CollectionUtils.isEmpty(directDepartments)) {
            return Lists.newArrayList();
        }

        List<Integer> departIds = getAllMangeDepart(departments, directDepartments)
                .stream()
                .map(SwDepartment::getId)
                .collect(Collectors.toList());
        return departIds;
    }


    public List<DepartmentVo> getMangeDepartVo(String employeeNo) {
        List<DepartmentVo> vo = getTreeDepartmentVos();
        return searchTree(vo, employeeNo);
    }

    public List<DepartmentVo> searchTree(List<DepartmentVo> treeVos, String employeeNo) {
        List<DepartmentVo> result = treeVos.stream()
                .filter(e -> employeeNo.equalsIgnoreCase(e.getManagerNo()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(result)) {
            for (DepartmentVo vo : treeVos) {
                if (CollectionUtils.isEmpty(vo.getChildren())) {
                    continue;
                }
                result = searchTree(vo.getChildren(), employeeNo);
                if (!CollectionUtils.isEmpty(result)) {
                    break;
                }
            }
        }
        return result;
    }

    private List<SwDepartment> getAllMangeDepart(List<SwDepartment> departments, List<SwDepartment> directDepartments) {
        List<SwDepartment> departmentList = new ArrayList<>();
        departmentList.addAll(directDepartments);
        List<SwDepartment> temps = departments.stream()
                .filter(e -> directDepartments.stream()
                        .anyMatch(ed -> e.getParentId().equals(ed.getId())))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(temps)) {
            return departmentList;
        }
        List<SwDepartment> nextDeparts = getAllMangeDepart(departments, temps);
        if (!CollectionUtils.isEmpty(nextDeparts)) {
            departmentList.addAll(nextDeparts);
        }
        departmentList.addAll(temps);
        return departmentList;
    }

    public String getFullDepartName(int departID) {
        Map<Integer, DepartmentVo> voMap = getDepartMap();
        List<DepartmentVo> departmentVos = getDepartList(voMap, departID);
        return departmentVos.stream().map(e -> e.getName()).collect(Collectors.joining(" - "));
    }

    public String getShortDepartName(int departID) {
        Map<Integer, DepartmentVo> voMap = getDepartMap();
        List<DepartmentVo> departmentVos = getDepartList(voMap, departID);
        if (departmentVos.size() > 2) {
            return departmentVos.subList(departmentVos.size() - 2, departmentVos.size())
                    .stream()
                    .map(e -> e.getName())
                    .collect(Collectors.joining(" "));
        }
        return departmentVos.stream()
                .map(e -> e.getName())
                .collect(Collectors.joining(" "));
    }


    public List<DepartmentVo> getDepartList(Map<Integer, DepartmentVo> voMap, int departID) {
        List<DepartmentVo> vos = new ArrayList<>();

        DepartmentVo departmentVo = voMap.get(departID);
        if (Objects.isNull(departmentVo.getParentId()) || departmentVo.getParentId() == 0) {
            return vos;
        }
        List<DepartmentVo> temps = getDepartList(voMap, departmentVo.getParentId());
        temps.addAll(Lists.newArrayList(departmentVo));
        vos.addAll(temps);
        return vos;
    }

    /**
     * 修改或新增
     *
     * @param department
     * @return
     */
    public Integer updateOrInsert(SwDepartment department) {
        int effectCount;
        if (Objects.nonNull(department.getId()) && department.getId() > 0) {
            effectCount = departmentExtensionMapper.updateByPrimaryKeySelective(department);
        } else {
            departmentExtensionMapper.insertSelective(department);
            effectCount = department.getId();
        }
        return effectCount;
    }

    public boolean isHead() {
        SwDepartmentExample example = new SwDepartmentExample();
        SwDepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andManagerNoEqualTo(RequestContext.getEmployeeNo());
        List<SwDepartment> departments = departmentExtensionMapper.selectByExample(example);
        return CollectionUtils.isEmpty(departments);
    }
}
