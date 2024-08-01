package com.foxconn.sw.data.mapper.extension.tool;

import com.foxconn.sw.data.entity.SwTools;
import com.foxconn.sw.data.entity.extension.SwToolsExtension;
import com.foxconn.sw.data.mapper.auto.SwToolsMapper;
import com.foxconn.sw.data.dto.entity.tool.ToolSearchParams;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwToolsExtensionMapper extends SwToolsMapper {

    @Select("<script> " +
            "select st.id toolId,\n" +
            "       tool_name,\n" +
            "       tool_icon,\n" +
            "       sp.id,\n" +
            "       sp.property_name as category_name,\n" +
            "       version_no,\n" +
            "       file_path,\n" +
            "       tool_size,\n" +
            "       introduction,\n" +
            "       update_content,\n" +
            "       use_guide,\n" +
            "       operator,\n" +
            "       st.create_time,\n" +
            "       st.datetime_lastchange\n" +
            "from sw_tools st\n" +
            "         inner join sw_property sp on st.property_id = sp.id " +
            "where 1=1 " +
            "<if test='params.keyword!=null and params.keyword!=\"\"' >" +
            " and st.tool_name like CONCAT('%', #{params.keyword,jdbcType=VARCHAR}, '%') " +
            "</if> " +
            "<if test='params.propertyId!=null and params.propertyId>0' >" +
            " and st.property_id=#{params.propertyId,jdbcType=INTEGER} " +
            "</if> " +
            "<if test='params.toolName!=null and params.toolName!=\"\"' >" +
            " and st.tool_name=#{params.toolName,jdbcType=VARCHAR} " +
            "</if> " +
            "ORDER BY st.id " +
            "LIMIT #{start,jdbcType=INTEGER} , #{end,jdbcType=INTEGER} " +
            " </script> "
    )
    @Results({
            @Result(column = "toolId", property = "toolId", jdbcType = JdbcType.INTEGER),
            @Result(column = "tool_name", property = "toolName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "tool_icon", property = "toolIcon", jdbcType = JdbcType.VARCHAR),
            @Result(column = "property_id", property = "propertyId", jdbcType = JdbcType.INTEGER),
            @Result(column = "category_name", property = "categoryName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "version_no", property = "versionNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_path", property = "filePath", jdbcType = JdbcType.VARCHAR),
            @Result(column = "tool_size", property = "toolSize", jdbcType = JdbcType.DOUBLE),
            @Result(column = "introduction", property = "introduction", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_content", property = "updateContent", jdbcType = JdbcType.VARCHAR),
            @Result(column = "use_guide", property = "useGuide", jdbcType = JdbcType.VARCHAR),
            @Result(column = "operator", property = "operator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP)
    })
    List<SwToolsExtension> searchByParams(@Param("start") int start,@Param("end") int end, @Param("params") ToolSearchParams params);

    @Select("<script> " +
            "select count(1) " +
            "from sw_tools st\n " +
            "         inner join sw_property sp on st.property_id = sp.id " +
            "where 1=1 " +
            "<if test='keyword!=null and keyword!=\"\"' >" +
            " and st.tool_name like CONCAT('%', #{keyword,jdbcType=VARCHAR}, '%') " +
            "</if> " +
            "<if test='propertyId!=null and propertyId>0' >" +
            " and st.property_id=#{propertyId,jdbcType=INTEGER} " +
            "</if> " +
            "<if test='toolName!=null and toolName!=\"\"' >" +
            " and st.tool_name=#{toolName,jdbcType=VARCHAR} " +
            "</if> " +
            "ORDER BY st.id " +
            " </script> "
    )
    int getTotalCountByParams(ToolSearchParams searchParams);

    @Insert({
            "insert into sw_tools (id,tool_name, tool_icon, ",
            "property_id, version_no, ",
            "file_path,tool_size, introduction, ",
            "update_content, use_guide, ",
            "operator) ",
            "values (#{id,jdbcType=INTEGER},#{toolName,jdbcType=VARCHAR}, #{toolIcon,jdbcType=VARCHAR}, ",
            "#{propertyId,jdbcType=INTEGER}, #{versionNo,jdbcType=VARCHAR}, ",
            "#{filePath,jdbcType=VARCHAR},#{toolSize,jdbcType=INTEGER}, #{introduction,jdbcType=VARCHAR}, ",
            "#{updateContent,jdbcType=VARCHAR}, #{useGuide,jdbcType=VARCHAR}, ",
            "#{operator,jdbcType=VARCHAR}) ",
            "on duplicate key update ",
            "version_no = values(version_no),",
            "file_path = values(file_path),",
            "tool_size = values(tool_size),",
            "introduction = values(introduction),",
            "update_content = values(update_content),",
            "use_guide = values(use_guide),",
            "operator = values(operator)",
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertUpdate(SwTools record);

}
