package com.foxconn.sw.data.mapper.extension.oa;

import com.foxconn.sw.data.dto.request.document.SearchDocParams;
import com.foxconn.sw.data.entity.SwDocument;
import com.foxconn.sw.data.mapper.auto.SwDocumentMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwDocumentExtensionMapper extends SwDocumentMapper {


    @Select({"<script>",
            "select ",
            "id, document_name, file_version, category, department, project, secret_level, ",
            "expire_date, disable_down, resource_id, author, creator, file_type, content, ",
            "work_type, main_type, sub_type, main_part, supplier, source, dead_line, is_delete, ",
            "create_time, datetime_lastchange",
            "from sw_document",
            "<where>",
            "<if test='params.documentName!=null and params.documentName!=\"\"' >",
            " and document_name like CONCAT('%', #{params.documentName,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "<if test='params.mainType!=null and params.mainType!=\"\"' >",
            " and main_type =#{params.mainType,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.subType!=null and params.subType!=\"\"' >",
            " and sub_type =#{params.subType,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.mainPart!=null and params.mainPart!=\"\"' >",
            " and main_part =#{params.mainPart,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.supplier!=null and params.supplier!=\"\"' >",
            " and supplier =#{params.supplier,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.source!=null and params.source!=\"\"' >",
            " and source =#{params.source,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.deadLine!=null and params.deadLine!=\"\"' >",
//            " and source =#{params.source,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.level!=null and params.level!=\"\"' >",
            " and secret_level =#{params.level,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.level!=null and params.level!=\"\"' >",
            " and secret_level =#{params.level,jdbcType=VARCHAR} ",
            "</if> ",
            "</where>",
            "LIMIT #{start,jdbcType=INTEGER} , #{pageSize,jdbcType=INTEGER} ",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "document_name", property = "documentName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_version", property = "fileVersion", jdbcType = JdbcType.VARCHAR),
            @Result(column = "category", property = "category", jdbcType = JdbcType.VARCHAR),
            @Result(column = "department", property = "department", jdbcType = JdbcType.INTEGER),
            @Result(column = "project", property = "project", jdbcType = JdbcType.VARCHAR),
            @Result(column = "secret_level", property = "secretLevel", jdbcType = JdbcType.INTEGER),
            @Result(column = "expire_date", property = "expireDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "disable_down", property = "disableDown", jdbcType = JdbcType.INTEGER),
            @Result(column = "resource_id", property = "resourceId", jdbcType = JdbcType.INTEGER),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_type", property = "fileType", jdbcType = JdbcType.INTEGER),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "work_type", property = "workType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "main_type", property = "mainType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "sub_type", property = "subType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "main_part", property = "mainPart", jdbcType = JdbcType.VARCHAR),
            @Result(column = "supplier", property = "supplier", jdbcType = JdbcType.VARCHAR),
            @Result(column = "source", property = "source", jdbcType = JdbcType.VARCHAR),
            @Result(column = "dead_line", property = "deadLine", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP)
    })
    List<SwDocument> queryDocumentListPages(SearchDocParams params, Integer start, Integer pageSize);

    @Select({"<script>",
            "select count(1)",
            "from sw_document ",
            "<where>",
            "<if test='params.documentName!=null and params.documentName!=\"\"' >",
            " and document_name like CONCAT('%', #{params.documentName,jdbcType=VARCHAR}, '%') ",
            "</if> ",
            "<if test='params.mainType!=null and params.mainType!=\"\"' >",
            " and main_type =#{params.mainType,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.subType!=null and params.subType!=\"\"' >",
            " and sub_type =#{params.subType,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.mainPart!=null and params.mainPart!=\"\"' >",
            " and main_part =#{params.mainPart,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.supplier!=null and params.supplier!=\"\"' >",
            " and supplier =#{params.supplier,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.source!=null and params.source!=\"\"' >",
            " and source =#{params.source,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.deadLine!=null and params.deadLine!=\"\"' >",
//            " and source =#{params.source,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.level!=null and params.level!=\"\"' >",
            " and secret_level =#{params.level,jdbcType=VARCHAR} ",
            "</if> ",
            "</where>",
            "</script>"
    })
    Long getTotalCountByParams(@Param("params") SearchDocParams params);
}
