package com.foxconn.sw.business.account;

import com.foxconn.sw.business.converter.SwSysMenuConverter;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.MenuDTO;
import com.foxconn.sw.data.dto.entity.acount.MenuParams;
import com.foxconn.sw.data.dto.entity.acount.RouteParams;
import com.foxconn.sw.data.entity.SwMenu;
import com.foxconn.sw.data.mapper.extension.acount.SwMenuExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuBusiness {

    @Autowired
    SwMenuExtensionMapper sysMenuExtensionMapper;

    public List<MenuDTO> routeList(RouteParams params) {
        List<SwMenu> sysMenus = sysMenuExtensionMapper.queryRouters(params);
        return SwSysMenuConverter.toSysMenuDto(sysMenus);
    }

    public List<MenuDTO> list(MenuParams params) {
        List<SwMenu> sysMenus = sysMenuExtensionMapper.queryMenus(params);
        return SwSysMenuConverter.toSysMenuDto(sysMenus);
    }

    public List<MenuDTO> authList(MenuParams params, Header header) {
        List<SwMenu> sysMenus = sysMenuExtensionMapper.queryMenus(params);
        return SwSysMenuConverter.toSysMenuDto(sysMenus);
    }

    public int save(MenuDTO menuDto) {
        SwMenu sysMenu = SwSysMenuConverter.toSysMenu(menuDto);
        int result = sysMenuExtensionMapper.insertSelective(sysMenu);
        return result;
    }
}
