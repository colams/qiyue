package com.foxconn.sw.business.system;

import com.foxconn.sw.data.dto.entity.system.DepartmentVo;
import com.foxconn.sw.data.mapper.extension.system.DepartmentExtensionMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DepartmentBusiness {

    @Autowired
    private DepartmentExtensionMapper departmentExtensionMapper;


    private static List<DepartmentVo> departmentVos = new ArrayList<>();

    private static List<DepartmentVo> treeDepartmentVos = new ArrayList<>();


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
        Map<Integer, DepartmentVo> menuMap = vos.stream().collect(Collectors.toMap(DepartmentVo::getId, e -> e));
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
}
