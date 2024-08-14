package com.foxconn.sw.data.mapper.extension.acount;

import com.foxconn.sw.data.dto.entity.acount.MenuParams;
import com.foxconn.sw.data.dto.entity.acount.MenuParams2;
import com.foxconn.sw.data.dto.entity.acount.MenuVo;
import com.foxconn.sw.data.entity.SwMenu;
import com.foxconn.sw.data.mapper.auto.SwMenuMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
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
            "<if test='isModuleIndex!=null and isModuleIndex>0' >" +
            "and is_module_index=#{isModuleIndex,jdbcType=INTEGER} " +
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
    List<SwMenu> queryRouters(MenuParams params);

    @Select("<script> " +
            "select sm.*,sp.property_name from sw_menu sm " +
            "inner join sw_property sp on sm.module_no=sp.id " +
            "where 1=1 " +
            "<if test='moduleNo!=null and moduleNo>0' >" +
            "and ( module_no=#{moduleNo,jdbcType=INTEGER} or module_no=0 ) " +
            "</if> " +
            "<if test='parentId!=null and parentId>0' >" +
            "and  parent_id=#{parentId,jdbcType=INTEGER}   " +
            "</if> " +
            "<if test='isModuleIndex!=null' >" +
            "and  is_module_index=#{isModuleIndex,jdbcType=INTEGER}   " +
            "</if> " +
            "<if test='menuName!=null and menuName!=\"\"' >" +
            "and menu_name like CONCAT('%', #{menuName,jdbcType=VARCHAR}, '%') " +
            "</if> " +
            "<if test='status!=null' >" +
            "and  status=#{status,jdbcType=INTEGER}   " +
            "</if> " +
            "order by order_by " +
            " </script> "
    )
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "menu_name", property = "menuName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "menu_url", property = "menuUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "route", property = "route", jdbcType = JdbcType.VARCHAR),
            @Result(column = "module_no", property = "moduleNo", jdbcType = JdbcType.INTEGER),
            @Result(column = "property_name", property = "moduleName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_module_index", property = "isModuleIndex", jdbcType = JdbcType.INTEGER),
            @Result(column = "is_menu", property = "isMenu", jdbcType = JdbcType.INTEGER),
            @Result(column = "parent_id", property = "parentId", jdbcType = JdbcType.INTEGER),
            @Result(column = "order_by", property = "orderBy", jdbcType = JdbcType.INTEGER),
            @Result(column = "status", property = "status", jdbcType = JdbcType.INTEGER),
    })
    List<MenuVo> searchMenus(MenuParams2 params);
}
