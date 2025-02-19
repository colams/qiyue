package com.foxconn.sw.service.processor.menu;

import com.foxconn.sw.business.account.MenuBusiness;
import com.foxconn.sw.data.dto.request.system.CreateMenuParams;
import com.foxconn.sw.data.entity.SwMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateMenuProcessor {

    @Autowired
    MenuBusiness menuBusiness;

    public Integer createMenu(CreateMenuParams data) {
        SwMenu menu = new SwMenu();
        menu.setId(data.getId());
        menu.setMenuName(data.getMenuName());
        menu.setMenuUrl(data.getMenuUrl());
        menu.setRoute(data.getRoute());
        menu.setModuleNo(data.getModuleNo());
        menu.setIsModuleIndex(data.getIsModuleIndex());
        menu.setIsMenu(data.getIsMenu());
        menu.setParentId(data.getParentId());
        menu.setOrderBy(0);
        menu.setStatus(1);
        return menuBusiness.updateOrInsert(menu);
    }
}
