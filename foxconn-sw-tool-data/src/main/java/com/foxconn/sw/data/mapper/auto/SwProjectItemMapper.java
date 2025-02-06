package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwProjectItem;
import com.foxconn.sw.data.entity.SwProjectItemExample;
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
public interface SwProjectItemMapper {
    int deleteByExample(SwProjectItemExample example);

    @Delete({
        "delete from sw_project_item",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_project_item (project_id, module_type, ",
        "update_by, detail_type, ",
        "project_item, project_value, ",
        "spare_value, operator, ",
        "is_delete, create_time, ",
        "datetime_lastchange)",
        "values (#{projectId,jdbcType=INTEGER}, #{moduleType,jdbcType=VARCHAR}, ",
        "#{updateBy,jdbcType=VARCHAR}, #{detailType,jdbcType=VARCHAR}, ",
        "#{projectItem,jdbcType=VARCHAR}, #{projectValue,jdbcType=VARCHAR}, ",
        "#{spareValue,jdbcType=VARCHAR}, #{operator,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwProjectItem record);

    int insertSelective(SwProjectItem record);

    List<SwProjectItem> selectByExampleWithRowbounds(SwProjectItemExample example, RowBounds rowBounds);

    List<SwProjectItem> selectByExample(SwProjectItemExample example);

    @Select({
        "select",
        "id, project_id, module_type, update_by, detail_type, project_item, project_value, ",
        "spare_value, operator, is_delete, create_time, datetime_lastchange",
        "from sw_project_item",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwProjectItemMapper.BaseResultMap")
    SwProjectItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwProjectItem record, @Param("example") SwProjectItemExample example);

    int updateByExample(@Param("record") SwProjectItem record, @Param("example") SwProjectItemExample example);

    int updateByPrimaryKeySelective(SwProjectItem record);

    @Update({
        "update sw_project_item",
        "set project_id = #{projectId,jdbcType=INTEGER},",
          "module_type = #{moduleType,jdbcType=VARCHAR},",
          "update_by = #{updateBy,jdbcType=VARCHAR},",
          "detail_type = #{detailType,jdbcType=VARCHAR},",
          "project_item = #{projectItem,jdbcType=VARCHAR},",
          "project_value = #{projectValue,jdbcType=VARCHAR},",
          "spare_value = #{spareValue,jdbcType=VARCHAR},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwProjectItem record);
}