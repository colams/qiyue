package com.foxconn.sw.service.processor.acount;

import com.foxconn.sw.business.account.MenuBusiness;
import com.foxconn.sw.data.dto.entity.acount.MenuBriefVo;
import com.foxconn.sw.data.dto.entity.acount.MenuParams;
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

    public List<MenuBriefVo> list(MenuParams data) {
        List<MenuBriefVo> sysMenus = menuBusiness.routeList(data);
        List<MenuBriefVo> menuTree = buildTree(sysMenus);
        return menuTree;
    }

    public static List<MenuBriefVo> buildTree(List<MenuBriefVo> menuBriefVoList) {
        Map<Integer, MenuBriefVo> menuMap = new HashMap<>();
        List<MenuBriefVo> rootMenus = new ArrayList<>();

        // 先将所有菜单放入 Map 中，方便根据 ID 查找
        for (MenuBriefVo menuBriefVo : menuBriefVoList) {
            menuMap.put(menuBriefVo.getId(), menuBriefVo);
        }

        // 遍历菜单列表，构建父子关系
        for (MenuBriefVo menuBriefVo : menuBriefVoList) {
            Integer parentId = menuBriefVo.getParentId();
            if (parentId == null || parentId == 0) {
                rootMenus.add(menuBriefVo);
            } else {
                MenuBriefVo parentMenu = menuMap.get(parentId);
                if (parentMenu != null) {
                    if (parentMenu.getChildren() == null) {
                        parentMenu.setChildren(new ArrayList<>());
                    }
                    parentMenu.getChildren().add(menuBriefVo);
                }
            }
        }

        return rootMenus;
    }

}
