package com.foxconn.sw.business.converter;

import com.foxconn.sw.data.dto.entity.acount.MenuBriefVo;
import com.foxconn.sw.data.entity.SwMenu;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class SwSysMenuConverter {

    public static SwMenu toSysMenu(MenuBriefVo menuBriefVo) {
        SwMenu swSysMenu = new SwMenu();
        swSysMenu.setMenuName(menuBriefVo.getMenuName());
        swSysMenu.setParentId(menuBriefVo.getParentId());
        swSysMenu.setMenuUrl(menuBriefVo.getMenuUrl());
        swSysMenu.setRoute(menuBriefVo.getRoute());
        swSysMenu.setIsMenu(menuBriefVo.getIsMenu());
        swSysMenu.setModuleNo(menuBriefVo.getModuleNo());
        swSysMenu.setOrderBy(menuBriefVo.getOrderBy());
        return swSysMenu;
    }

    public static List<MenuBriefVo> toSysMenuDto(List<SwMenu> sysMenus) {
        if (CollectionUtils.isEmpty(sysMenus)) {
            return Lists.newArrayList();
        }
        List<MenuBriefVo> menuBriefVos = new ArrayList<>();
        sysMenus.forEach(e -> {
            MenuBriefVo menuBriefVo = new MenuBriefVo();
            menuBriefVo.setId(e.getId());
            menuBriefVo.setModuleNo(e.getModuleNo());
            menuBriefVo.setIsMenu(e.getIsMenu());
            menuBriefVo.setMenuName(e.getMenuName());
            menuBriefVo.setParentId(e.getParentId());
            menuBriefVo.setMenuUrl(e.getMenuUrl());
            menuBriefVo.setRoute(e.getRoute());
            menuBriefVo.setOrderBy(e.getOrderBy());
            menuBriefVos.add(menuBriefVo);
        });
        return menuBriefVos;
    }
}
