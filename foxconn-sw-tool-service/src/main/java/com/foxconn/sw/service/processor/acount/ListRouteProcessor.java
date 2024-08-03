package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.account.MenuBusiness;
import com.foxconn.sw.data.dto.entity.acount.MenuDTO;
import com.foxconn.sw.data.dto.entity.acount.RouteParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ListRouteProcessor {

    @Autowired
    MenuBusiness menuBusiness;

    public List<MenuDTO> list(RouteParams data) {
        List<MenuDTO> sysMenus = menuBusiness.routeList(data);
        List<MenuDTO> menuTree = buildTree(sysMenus);
        return menuTree;
    }

    public static List<MenuDTO> buildTree(List<MenuDTO> menuDTOList) {
        Map<Integer, MenuDTO> menuMap = new HashMap<>();
        List<MenuDTO> rootMenus = new ArrayList<>();

        // 先将所有菜单放入 Map 中，方便根据 ID 查找
        for (MenuDTO menuDTO : menuDTOList) {
            menuMap.put(menuDTO.getId(), menuDTO);
        }

        // 遍历菜单列表，构建父子关系
        for (MenuDTO menuDTO : menuDTOList) {
            Integer parentId = menuDTO.getParentId();
            if (parentId == null || parentId == 0) {
                rootMenus.add(menuDTO);
            } else {
                MenuDTO parentMenu = menuMap.get(parentId);
                if (parentMenu != null) {
                    if (parentMenu.getChildren() == null) {
                        parentMenu.setChildren(new ArrayList<>());
                    }
                    parentMenu.getChildren().add(menuDTO);
                }
            }
        }

        return rootMenus;
    }

}
