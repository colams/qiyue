package com.foxconn.sw.business.system;

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


    private static List<DepartmentVo> departmentVos = new ArrayList<>();

    private static List<DepartmentVo> treeDepartmentVos = new ArrayList<>();

    private static List<SwDepartment> departmentList = new ArrayList<>();


    public Map<Integer, DepartmentVo> getDepartMap() {
        if (CollectionUtils.isEmpty(departmentVos)) {
            departmentVos = departmentExtensionMapper.getDepartList();
        }
        return Optional.ofNullable(departmentVos)
                .orElse(Lists.newArrayList())
                .stream()
                .collect(Collectors.toMap(DepartmentVo::getId, e -> e));
    }

    public List<DepartmentVo> getDepartList() {
        if (CollectionUtils.isEmpty(departmentVos)) {
            departmentVos = departmentExtensionMapper.getDepartList();
        }

        departmentVos.forEach(e -> {
            e.setName(ZhConverterUtil.convertToTraditional(e.getName()));
        });
        return departmentVos;
    }

    public List<DepartmentVo> getTreeDepartmentVos() {
        if (!CollectionUtils.isEmpty(treeDepartmentVos)) {
            return treeDepartmentVos;
        }
        List<DepartmentVo> vos = getDepartList();
        treeDepartmentVos = buildDepartmentVoTree(vos);
        return treeDepartmentVos;
    }


    private static List<DepartmentVo> buildDepartmentVoTree(List<DepartmentVo> vos) {
        Map<Integer, DepartmentVo> menuMap = vos.stream()
                .collect(Collectors.toMap(DepartmentVo::getId, e -> e));
        // 构建菜单树
        List<DepartmentVo> rootMenus = buildTree(menuMap);
        return rootMenus;
    }

    private static List<DepartmentVo> buildTree(Map<Integer, DepartmentVo> menuMap) {
        List<DepartmentVo> rootMenus = new ArrayList<>();

        for (DepartmentVo menu : menuMap.values()) {
            Integer parentId = menu.getParentId();
            if (parentId == null || parentId == 0) {
                rootMenus.add(menu);
                buildSubTree(menu, menuMap);
            }
        }

        return rootMenus;
    }

    private static void buildSubTree(DepartmentVo parentMenu, Map<Integer, DepartmentVo> menuMap) {
        if (parentMenu.getChildren() == null) {
            parentMenu.setChildren(new ArrayList<>());
        }

        for (DepartmentVo menu : menuMap.values()) {
            Integer parentId = menu.getParentId();
            if (parentId != null && parentId == parentMenu.getId()) {
                parentMenu.getChildren().add(menu);
                buildSubTree(menu, menuMap);
            }
        }
    }

    public List<Integer> getAllSubDepartID(String searchKey) {
        List<DepartmentVo> departmentVos = getDepartList();
        return getDepartmentIds(departmentVos, NumberUtils.toInt(searchKey), 0);
    }

    private List<Integer> getDepartmentIds(List<DepartmentVo> vos, Integer currId, int type) {
        List<Integer> result = new ArrayList<>();

        List<Integer> subVoIds = vos
                .stream()
                .filter(e -> type == 0 ? e.getId() == currId : e.getParentId() == currId)
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
        SwDepartment department = departments.stream().filter(e -> e.getId() == departmentId).findFirst().orElse(null);
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
        if (!CollectionUtils.isEmpty(departmentList)) {
            return departmentList;
        }
        SwDepartmentExample example = new SwDepartmentExample();
        SwDepartmentExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        departmentList = departmentExtensionMapper.selectByExample(example);
        return departmentList;
    }
}
