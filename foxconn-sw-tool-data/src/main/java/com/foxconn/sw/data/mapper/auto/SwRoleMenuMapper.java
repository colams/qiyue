package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwRoleMenu;
import com.foxconn.sw.data.entity.SwRoleMenuExample;
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
public interface SwRoleMenuMapper {
    long countByExample(SwRoleMenuExample example);

    int deleteByExample(SwRoleMenuExample example);

    @Delete({
        "delete from sw_role_menu",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_role_menu (role_id, menu_id, ",
        "create_time, datetime_lastchange)",
        "values (#{roleId,jdbcType=INTEGER}, #{menuId,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwRoleMenu record);

    int insertSelective(SwRoleMenu record);

    List<SwRoleMenu> selectByExampleWithRowbounds(SwRoleMenuExample example, RowBounds rowBounds);

    List<SwRoleMenu> selectByExample(SwRoleMenuExample example);

    @Select({
        "select",
        "id, role_id, menu_id, create_time, datetime_lastchange",
        "from sw_role_menu",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwRoleMenuMapper.BaseResultMap")
    SwRoleMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwRoleMenu record, @Param("example") SwRoleMenuExample example);

    int updateByExample(@Param("record") SwRoleMenu record, @Param("example") SwRoleMenuExample example);

    int updateByPrimaryKeySelective(SwRoleMenu record);

    @Update({
        "update sw_role_menu",
        "set role_id = #{roleId,jdbcType=INTEGER},",
          "menu_id = #{menuId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwRoleMenu record);
}