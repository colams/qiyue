package com.foxconn.sw.data.mapper.extension.tool;

import com.foxconn.sw.data.entity.extension.SwToolsExtension;
import com.foxconn.sw.data.mapper.auto.SwToolsHistoryMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwToolsHistoryExtensionMapper extends SwToolsHistoryMapper {

    @Select({"select sth.id historyId,\n" +
            "       sth.tool_id toolId,\n" +
            "       tool_name,\n" +
            "       tool_icon,\n" +
            "       property_id,\n" +
            "       sp.property_name as category_name,\n" +
            "       version_no,\n" +
            "       file_path,\n" +
            "       tool_size,\n" +
            "       introduction,\n" +
            "       update_content,\n" +
            "       use_guide,\n" +
            "       operator,\n" +
            "       sth.create_time,\n" +
            "       sth.datetime_lastchange\n" +
            "from sw_tools_history sth\n" +
            "         inner join sw_property sp on sth.property_id = sp.id\n" +
            "where sth.tool_id = #{toolID,jdbcType=INTEGER} " +
            "ORDER BY sth.id desc " +
            "LIMIT #{start,jdbcType=INTEGER} , #{end,jdbcType=INTEGER} "
    })
    @Results({
            @Result(column = "toolId", property = "toolId", jdbcType = JdbcType.INTEGER),
            @Result(column = "historyId", property = "historyId", jdbcType = JdbcType.INTEGER),
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
    List<SwToolsExtension> searchByToolId(@Param("start") int start, @Param("end") int end, @Param("toolID") Integer tool_id);

    @Select({"select count(*) " +
            "from sw_tools_history sth\n" +
            "         inner join sw_property sp on sth.property_id = sp.id\n" +
            "where sth.tool_id = #{tool_id,jdbcType=INTEGER}"})
    Long countByToolID(Integer tool_id);


}
