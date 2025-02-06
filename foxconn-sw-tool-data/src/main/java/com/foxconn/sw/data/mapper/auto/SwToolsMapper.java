package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwTools;
import com.foxconn.sw.data.entity.SwToolsExample;
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
public interface SwToolsMapper {
    int deleteByExample(SwToolsExample example);

    @Delete({
        "delete from sw_tools",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_tools (tool_name, tool_icon, ",
        "property_id, version_no, ",
        "file_path, resource_id, ",
        "tool_size, introduction, ",
        "update_content, use_guide, ",
        "operator, create_time, ",
        "datetime_lastchange)",
        "values (#{toolName,jdbcType=VARCHAR}, #{toolIcon,jdbcType=VARCHAR}, ",
        "#{propertyId,jdbcType=INTEGER}, #{versionNo,jdbcType=VARCHAR}, ",
        "#{filePath,jdbcType=VARCHAR}, #{resourceId,jdbcType=INTEGER}, ",
        "#{toolSize,jdbcType=DOUBLE}, #{introduction,jdbcType=VARCHAR}, ",
        "#{updateContent,jdbcType=VARCHAR}, #{useGuide,jdbcType=VARCHAR}, ",
        "#{operator,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwTools record);

    int insertSelective(SwTools record);

    List<SwTools> selectByExampleWithRowbounds(SwToolsExample example, RowBounds rowBounds);

    List<SwTools> selectByExample(SwToolsExample example);

    @Select({
        "select",
        "id, tool_name, tool_icon, property_id, version_no, file_path, resource_id, tool_size, ",
        "introduction, update_content, use_guide, operator, create_time, datetime_lastchange",
        "from sw_tools",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwToolsMapper.BaseResultMap")
    SwTools selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwTools record, @Param("example") SwToolsExample example);

    int updateByExample(@Param("record") SwTools record, @Param("example") SwToolsExample example);

    int updateByPrimaryKeySelective(SwTools record);

    @Update({
        "update sw_tools",
        "set tool_name = #{toolName,jdbcType=VARCHAR},",
          "tool_icon = #{toolIcon,jdbcType=VARCHAR},",
          "property_id = #{propertyId,jdbcType=INTEGER},",
          "version_no = #{versionNo,jdbcType=VARCHAR},",
          "file_path = #{filePath,jdbcType=VARCHAR},",
          "resource_id = #{resourceId,jdbcType=INTEGER},",
          "tool_size = #{toolSize,jdbcType=DOUBLE},",
          "introduction = #{introduction,jdbcType=VARCHAR},",
          "update_content = #{updateContent,jdbcType=VARCHAR},",
          "use_guide = #{useGuide,jdbcType=VARCHAR},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwTools record);
}