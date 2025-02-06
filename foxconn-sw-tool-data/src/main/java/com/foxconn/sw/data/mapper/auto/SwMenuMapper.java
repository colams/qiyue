package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMenu;
import com.foxconn.sw.data.entity.SwMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface SwMenuMapper {
    long countByExample(SwMenuExample example);

    int deleteByExample(SwMenuExample example);

    @Delete({
        "delete from sw_menu",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_menu (menu_name, menu_url, ",
        "route, module_no, ",
        "is_module_index, is_menu, ",
        "parent_id, order_by, ",
        "status, create_time, ",
        "datetime_lastchange)",
        "values (#{menuName,jdbcType=VARCHAR}, #{menuUrl,jdbcType=VARCHAR}, ",
        "#{route,jdbcType=VARCHAR}, #{moduleNo,jdbcType=INTEGER}, ",
        "#{isModuleIndex,jdbcType=INTEGER}, #{isMenu,jdbcType=INTEGER}, ",
        "#{parentId,jdbcType=INTEGER}, #{orderBy,jdbcType=INTEGER}, ",
        "#{status,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwMenu record);

    int insertSelective(SwMenu record);

    List<SwMenu> selectByExampleWithRowbounds(SwMenuExample example, RowBounds rowBounds);

    List<SwMenu> selectByExample(SwMenuExample example);

    @Select({
        "select",
        "id, menu_name, menu_url, route, module_no, is_module_index, is_menu, parent_id, ",
        "order_by, status, create_time, datetime_lastchange",
        "from sw_menu",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwMenuMapper.BaseResultMap")
    SwMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwMenu record, @Param("example") SwMenuExample example);

    int updateByExample(@Param("record") SwMenu record, @Param("example") SwMenuExample example);

    int updateByPrimaryKeySelective(SwMenu record);

    @Update({
        "update sw_menu",
        "set menu_name = #{menuName,jdbcType=VARCHAR},",
          "menu_url = #{menuUrl,jdbcType=VARCHAR},",
          "route = #{route,jdbcType=VARCHAR},",
          "module_no = #{moduleNo,jdbcType=INTEGER},",
          "is_module_index = #{isModuleIndex,jdbcType=INTEGER},",
          "is_menu = #{isMenu,jdbcType=INTEGER},",
          "parent_id = #{parentId,jdbcType=INTEGER},",
          "order_by = #{orderBy,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwMenu record);
}