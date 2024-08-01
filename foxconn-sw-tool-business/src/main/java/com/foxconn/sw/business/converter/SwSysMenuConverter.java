package com.foxconn.sw.business.converter;

import com.foxconn.sw.data.dto.entity.acount.MenuDTO;
import com.foxconn.sw.data.entity.SwMenu;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class SwSysMenuConverter {

    public static SwMenu toSysMenu(MenuDTO menuDto) {
        SwMenu swSysMenu = new SwMenu();
        swSysMenu.setMenuName(menuDto.getMenuName());
        swSysMenu.setParentId(menuDto.getParentId());
        swSysMenu.setMenuUrl(menuDto.getMenuUrl());
        swSysMenu.setRoute(menuDto.getRoute());
        swSysMenu.setIsMenu(menuDto.getIsMenu());
        swSysMenu.setModuleNo(menuDto.getModuleNo());
        swSysMenu.setOrderBy(menuDto.getOrderBy());
        return swSysMenu;
    }

    public static List<MenuDTO> toSysMenuDto(List<SwMenu> sysMenus) {
        if (CollectionUtils.isEmpty(sysMenus)) {
            return Lists.newArrayList();
        }
        List<MenuDTO> menuDTOS = new ArrayList<>();
        sysMenus.forEach(e -> {
            MenuDTO menuDTO = new MenuDTO();
            menuDTO.setId(e.getId());
            menuDTO.setIsMenu(e.getIsMenu());
            menuDTO.setMenuName(e.getMenuName());
            menuDTO.setParentId(e.getParentId());
            menuDTO.setMenuUrl(e.getMenuUrl());
            menuDTO.setRoute(e.getRoute());
            menuDTO.setOrderBy(e.getOrderBy());
            menuDTOS.add(menuDTO);
        });
        return menuDTOS;
    }
}
