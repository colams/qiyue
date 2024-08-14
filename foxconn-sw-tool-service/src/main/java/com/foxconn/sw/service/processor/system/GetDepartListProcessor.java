package com.foxconn.sw.service.processor.system;

import com.foxconn.sw.business.system.DepartmentBusiness;
import com.foxconn.sw.data.dto.entity.system.DepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GetDepartListProcessor {

    @Autowired
    DepartmentBusiness departmentBusiness;

    public List<DepartmentVo> getDepartList() {
        List<DepartmentVo> vos = departmentBusiness.getDepartList();
        return buildDepartmentVoTree(vos);
    }


    public static List<DepartmentVo> buildDepartmentVoTree(List<DepartmentVo> vos) {
        Map<Integer, DepartmentVo> menuMap = new HashMap<>();

        // 先将所有菜单放入 Map 中，方便根据 ID 查找
        for (DepartmentVo menuBriefVo : vos) {
            menuMap.put(menuBriefVo.getId(), menuBriefVo);
        }

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
            if (parentId!= null && parentId == parentMenu.getId()) {
                parentMenu.getChildren().add(menu);
                buildSubTree(menu, menuMap);
            }
        }
    }

}
