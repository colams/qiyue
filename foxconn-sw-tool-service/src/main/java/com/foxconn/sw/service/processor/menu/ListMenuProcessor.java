package com.foxconn.sw.service.processor.menu;

import com.foxconn.sw.business.account.MenuBusiness;
import com.foxconn.sw.data.dto.entity.acount.MenuBriefVo;
import com.foxconn.sw.data.dto.entity.acount.MenuParams;
import com.foxconn.sw.data.dto.entity.acount.MenuParams2;
import com.foxconn.sw.data.dto.entity.acount.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ListMenuProcessor {

    @Autowired
    MenuBusiness menuBusiness;

    public List<MenuBriefVo> list(MenuParams data) {
        List<MenuBriefVo> sysMenus = menuBusiness.list(data);
        List<MenuBriefVo> menuTree = buildTree(sysMenus);
        return menuTree;
    }

    public List<MenuVo> searchMenus(MenuParams2 params) {
        List<MenuVo> vo = menuBusiness.searchMenus(params);
        List<MenuVo> treeVo = buildMenuVoTree(vo);
        return treeVo;
    }


    public static List<MenuVo> buildMenuVoTree(List<MenuVo> vos) {
        Map<Integer, MenuVo> menuMap = new HashMap<>();

        // 先将所有菜单放入 Map 中，方便根据 ID 查找
        for (MenuVo menuBriefVo : vos) {
            menuMap.put(menuBriefVo.getId(), menuBriefVo);
        }

        // 构建菜单树
        List<MenuVo> rootMenus = buildTree(menuMap);

        return rootMenus;
    }

    private static List<MenuVo> buildTree(Map<Integer, MenuVo> menuMap) {
        List<MenuVo> rootMenus = new ArrayList<>();

        for (MenuVo menu : menuMap.values()) {
            Integer parentId = menu.getParentId();
            if (parentId == null || parentId == 0) {
                rootMenus.add(menu);
                buildSubTree(menu, menuMap);
            }
        }

        return rootMenus;
    }

    private static void buildSubTree(MenuVo parentMenu, Map<Integer, MenuVo> menuMap) {
        if (parentMenu.getChildren() == null) {
            parentMenu.setChildren(new ArrayList<>());
        }

        for (MenuVo menu : menuMap.values()) {
            Integer parentId = menu.getParentId();
            if (parentId!= null && parentId == parentMenu.getId()) {
                parentMenu.getChildren().add(menu);
                buildSubTree(menu, menuMap);
            }
        }
    }


    private static List<MenuBriefVo> buildTree(List<MenuBriefVo> menuBriefVoList) {
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
