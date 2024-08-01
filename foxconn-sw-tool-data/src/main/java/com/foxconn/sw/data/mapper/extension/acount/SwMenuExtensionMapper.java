package com.foxconn.sw.data.mapper.extension.acount;

import com.foxconn.sw.data.dto.entity.acount.MenuParams;
import com.foxconn.sw.data.dto.entity.acount.RouteParams;
import com.foxconn.sw.data.entity.SwMenu;
import com.foxconn.sw.data.mapper.auto.SwMenuMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwMenuExtensionMapper extends SwMenuMapper {

    @Select("<script> " +
            "select * from sw_menu " +
            "where status=1 and is_menu=1 " +
            "<if test='menuName!=null and menuName!=\"\"' >" +
            "and menu_name like CONCAT('%', #{menuName,jdbcType=VARCHAR}, '%') " +
            "</if> " +
            "<if test='menuId!=null and menuId>0' >" +
            "and id=#{menuId,jdbcType=INTEGER} " +
            "</if> " +
            "<if test='parentId!=null and parentId>0' >" +
            "and parent_id=#{parentId,jdbcType=INTEGER} " +
            "</if> " +
            "<if test='moduleNo!=null and moduleNo>0' >" +
            "and module_no=#{moduleNo,jdbcType=INTEGER} " +
            "</if> " +
            "order by order_by " +
            " </script> "
    )
    List<SwMenu> queryMenus(MenuParams params);

    @Select("<script> " +
            "select * from sw_menu " +
            "where status=1 " +
            "<if test='moduleNo!=null and moduleNo>0' >" +
            "and ( module_no=#{moduleNo,jdbcType=INTEGER} or module_no=0 ) " +
            "</if> " +
            "order by order_by " +
            " </script> "
    )
    List<SwMenu> queryRouters(RouteParams params);
}
