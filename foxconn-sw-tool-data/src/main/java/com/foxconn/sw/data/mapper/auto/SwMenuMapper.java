package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwMenu;
import com.foxconn.sw.data.entity.SwMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface SwMenuMapper {
    @DeleteProvider(type=SwMenuSqlProvider.class, method="deleteByExample")
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

    @InsertProvider(type=SwMenuSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwMenu record);

    @SelectProvider(type=SwMenuSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="menu_name", property="menuName", jdbcType=JdbcType.VARCHAR),
        @Result(column="menu_url", property="menuUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="route", property="route", jdbcType=JdbcType.VARCHAR),
        @Result(column="module_no", property="moduleNo", jdbcType=JdbcType.INTEGER),
        @Result(column="is_module_index", property="isModuleIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="is_menu", property="isMenu", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="order_by", property="orderBy", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwMenu> selectByExampleWithRowbounds(SwMenuExample example, RowBounds rowBounds);

    @SelectProvider(type=SwMenuSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="menu_name", property="menuName", jdbcType=JdbcType.VARCHAR),
        @Result(column="menu_url", property="menuUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="route", property="route", jdbcType=JdbcType.VARCHAR),
        @Result(column="module_no", property="moduleNo", jdbcType=JdbcType.INTEGER),
        @Result(column="is_module_index", property="isModuleIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="is_menu", property="isMenu", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="order_by", property="orderBy", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwMenu> selectByExample(SwMenuExample example);

    @Select({
        "select",
        "id, menu_name, menu_url, route, module_no, is_module_index, is_menu, parent_id, ",
        "order_by, status, create_time, datetime_lastchange",
        "from sw_menu",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="menu_name", property="menuName", jdbcType=JdbcType.VARCHAR),
        @Result(column="menu_url", property="menuUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="route", property="route", jdbcType=JdbcType.VARCHAR),
        @Result(column="module_no", property="moduleNo", jdbcType=JdbcType.INTEGER),
        @Result(column="is_module_index", property="isModuleIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="is_menu", property="isMenu", jdbcType=JdbcType.INTEGER),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.INTEGER),
        @Result(column="order_by", property="orderBy", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwMenu selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwMenuSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwMenu record, @Param("example") SwMenuExample example);

    @UpdateProvider(type=SwMenuSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwMenu record, @Param("example") SwMenuExample example);

    @UpdateProvider(type=SwMenuSqlProvider.class, method="updateByPrimaryKeySelective")
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