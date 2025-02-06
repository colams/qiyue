package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwToolsHistory;
import com.foxconn.sw.data.entity.SwToolsHistoryExample;
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
public interface SwToolsHistoryMapper {
    @SelectProvider(type=SwToolsHistorySqlProvider.class, method="countByExample")
    long countByExample(SwToolsHistoryExample example);

    @DeleteProvider(type=SwToolsHistorySqlProvider.class, method="deleteByExample")
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

    @InsertProvider(type=SwToolsHistorySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwToolsHistory record);

    @SelectProvider(type=SwToolsHistorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tool_id", property="toolId", jdbcType=JdbcType.INTEGER),
        @Result(column="tool_name", property="toolName", jdbcType=JdbcType.VARCHAR),
        @Result(column="tool_icon", property="toolIcon", jdbcType=JdbcType.VARCHAR),
        @Result(column="property_id", property="propertyId", jdbcType=JdbcType.INTEGER),
        @Result(column="version_no", property="versionNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="tool_size", property="toolSize", jdbcType=JdbcType.DOUBLE),
        @Result(column="introduction", property="introduction", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_content", property="updateContent", jdbcType=JdbcType.VARCHAR),
        @Result(column="use_guide", property="useGuide", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwToolsHistory> selectByExampleWithRowbounds(SwToolsHistoryExample example, RowBounds rowBounds);

    @SelectProvider(type=SwToolsHistorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tool_id", property="toolId", jdbcType=JdbcType.INTEGER),
        @Result(column="tool_name", property="toolName", jdbcType=JdbcType.VARCHAR),
        @Result(column="tool_icon", property="toolIcon", jdbcType=JdbcType.VARCHAR),
        @Result(column="property_id", property="propertyId", jdbcType=JdbcType.INTEGER),
        @Result(column="version_no", property="versionNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="tool_size", property="toolSize", jdbcType=JdbcType.DOUBLE),
        @Result(column="introduction", property="introduction", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_content", property="updateContent", jdbcType=JdbcType.VARCHAR),
        @Result(column="use_guide", property="useGuide", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwToolsHistory> selectByExample(SwToolsHistoryExample example);

    @Select({
        "select",
        "id, tool_id, tool_name, tool_icon, property_id, version_no, file_path, tool_size, ",
        "introduction, update_content, use_guide, operator, create_time, datetime_lastchange",
        "from sw_tools_history",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tool_id", property="toolId", jdbcType=JdbcType.INTEGER),
        @Result(column="tool_name", property="toolName", jdbcType=JdbcType.VARCHAR),
        @Result(column="tool_icon", property="toolIcon", jdbcType=JdbcType.VARCHAR),
        @Result(column="property_id", property="propertyId", jdbcType=JdbcType.INTEGER),
        @Result(column="version_no", property="versionNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_path", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="tool_size", property="toolSize", jdbcType=JdbcType.DOUBLE),
        @Result(column="introduction", property="introduction", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_content", property="updateContent", jdbcType=JdbcType.VARCHAR),
        @Result(column="use_guide", property="useGuide", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwToolsHistory selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwToolsHistorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwToolsHistory record, @Param("example") SwToolsHistoryExample example);

    @UpdateProvider(type=SwToolsHistorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwToolsHistory record, @Param("example") SwToolsHistoryExample example);

    @UpdateProvider(type=SwToolsHistorySqlProvider.class, method="updateByPrimaryKeySelective")
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