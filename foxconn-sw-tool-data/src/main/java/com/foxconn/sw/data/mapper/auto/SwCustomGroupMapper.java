package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCustomGroup;
import com.foxconn.sw.data.entity.SwCustomGroupExample;
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
public interface SwCustomGroupMapper {
    int deleteByExample(SwCustomGroupExample example);

    @Delete({
        "delete from sw_custom_group",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_custom_group (name, owner, ",
        "group_type, is_private, ",
        "description, is_delete, ",
        "create_time, datetime_lastchange)",
        "values (#{name,jdbcType=VARCHAR}, #{owner,jdbcType=VARCHAR}, ",
        "#{groupType,jdbcType=INTEGER}, #{isPrivate,jdbcType=INTEGER}, ",
        "#{description,jdbcType=VARCHAR}, #{isDelete,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwCustomGroup record);

    int insertSelective(SwCustomGroup record);

    List<SwCustomGroup> selectByExampleWithRowbounds(SwCustomGroupExample example, RowBounds rowBounds);

    List<SwCustomGroup> selectByExample(SwCustomGroupExample example);

    @Select({
        "select",
        "id, name, owner, group_type, is_private, description, is_delete, create_time, ",
        "datetime_lastchange",
        "from sw_custom_group",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwCustomGroupMapper.BaseResultMap")
    SwCustomGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwCustomGroup record, @Param("example") SwCustomGroupExample example);

    int updateByExample(@Param("record") SwCustomGroup record, @Param("example") SwCustomGroupExample example);

    int updateByPrimaryKeySelective(SwCustomGroup record);

    @Update({
        "update sw_custom_group",
        "set name = #{name,jdbcType=VARCHAR},",
          "owner = #{owner,jdbcType=VARCHAR},",
          "group_type = #{groupType,jdbcType=INTEGER},",
          "is_private = #{isPrivate,jdbcType=INTEGER},",
          "description = #{description,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwCustomGroup record);
}