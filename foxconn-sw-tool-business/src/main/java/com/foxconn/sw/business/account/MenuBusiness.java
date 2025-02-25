package com.foxconn.sw.business.account;

import com.foxconn.sw.business.converter.SwSysMenuConverter;
import com.foxconn.sw.data.dto.Header;
import com.foxconn.sw.data.dto.entity.acount.MenuBriefVo;
import com.foxconn.sw.data.dto.entity.acount.MenuParams;
import com.foxconn.sw.data.dto.entity.acount.MenuParams2;
import com.foxconn.sw.data.dto.entity.acount.MenuVo;
import com.foxconn.sw.data.entity.SwMenu;
import com.foxconn.sw.data.mapper.extension.acount.SwMenuExtensionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MenuBusiness {

    @Autowired
    SwMenuExtensionMapper sysMenuExtensionMapper;

    public List<MenuBriefVo> routeList(MenuParams params) {
        List<SwMenu> sysMenus = sysMenuExtensionMapper.queryRouters(params);
        return SwSysMenuConverter.toSysMenuDto(sysMenus);
    }


    public List<MenuBriefVo> list(MenuParams params) {
        List<SwMenu> sysMenus = sysMenuExtensionMapper.queryMenus(params);
        return SwSysMenuConverter.toSysMenuDto(sysMenus);
    }

    public List<MenuBriefVo> authList(MenuParams params, Header header) {
        List<SwMenu> sysMenus = sysMenuExtensionMapper.queryMenus(params);
        return SwSysMenuConverter.toSysMenuDto(sysMenus);
    }

    public int save(MenuBriefVo menuBriefVo) {
        SwMenu sysMenu = SwSysMenuConverter.toSysMenu(menuBriefVo);
        return sysMenuExtensionMapper.insertSelective(sysMenu);
    }

    public List<MenuVo> searchMenus(MenuParams2 params) {
        return sysMenuExtensionMapper.searchMenus(params);
    }

    public Integer updateOrInsert(SwMenu menu) {
        if (Objects.isNull(menu.getId()) || menu.getId() <= 0) {
            sysMenuExtensionMapper.insertSelective(menu);
            return menu.getId();
        } else {
            return sysMenuExtensionMapper.updateByPrimaryKeySelective(menu);
        }
    }
}
