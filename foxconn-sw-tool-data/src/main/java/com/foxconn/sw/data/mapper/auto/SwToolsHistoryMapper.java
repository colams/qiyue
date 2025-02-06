package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwToolsHistory;
import com.foxconn.sw.data.entity.SwToolsHistoryExample;
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
public interface SwToolsHistoryMapper {
    int deleteByExample(SwToolsHistoryExample example);

    @Delete({
        "delete from sw_tools_history",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_tools_history (tool_id, tool_name, ",
        "tool_icon, property_id, ",
        "version_no, file_path, ",
        "tool_size, introduction, ",
        "update_content, use_guide, ",
        "operator, create_time, ",
        "datetime_lastchange)",
        "values (#{toolId,jdbcType=INTEGER}, #{toolName,jdbcType=VARCHAR}, ",
        "#{toolIcon,jdbcType=VARCHAR}, #{propertyId,jdbcType=INTEGER}, ",
        "#{versionNo,jdbcType=VARCHAR}, #{filePath,jdbcType=VARCHAR}, ",
        "#{toolSize,jdbcType=DOUBLE}, #{introduction,jdbcType=VARCHAR}, ",
        "#{updateContent,jdbcType=VARCHAR}, #{useGuide,jdbcType=VARCHAR}, ",
        "#{operator,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwToolsHistory record);

    int insertSelective(SwToolsHistory record);

    List<SwToolsHistory> selectByExampleWithRowbounds(SwToolsHistoryExample example, RowBounds rowBounds);

    List<SwToolsHistory> selectByExample(SwToolsHistoryExample example);

    @Select({
        "select",
        "id, tool_id, tool_name, tool_icon, property_id, version_no, file_path, tool_size, ",
        "introduction, update_content, use_guide, operator, create_time, datetime_lastchange",
        "from sw_tools_history",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwToolsHistoryMapper.BaseResultMap")
    SwToolsHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwToolsHistory record, @Param("example") SwToolsHistoryExample example);

    int updateByExample(@Param("record") SwToolsHistory record, @Param("example") SwToolsHistoryExample example);

    int updateByPrimaryKeySelective(SwToolsHistory record);

    @Update({
        "update sw_tools_history",
        "set tool_id = #{toolId,jdbcType=INTEGER},",
          "tool_name = #{toolName,jdbcType=VARCHAR},",
          "tool_icon = #{toolIcon,jdbcType=VARCHAR},",
          "property_id = #{propertyId,jdbcType=INTEGER},",
          "version_no = #{versionNo,jdbcType=VARCHAR},",
          "file_path = #{filePath,jdbcType=VARCHAR},",
          "tool_size = #{toolSize,jdbcType=DOUBLE},",
          "introduction = #{introduction,jdbcType=VARCHAR},",
          "update_content = #{updateContent,jdbcType=VARCHAR},",
          "use_guide = #{useGuide,jdbcType=VARCHAR},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwToolsHistory record);
}