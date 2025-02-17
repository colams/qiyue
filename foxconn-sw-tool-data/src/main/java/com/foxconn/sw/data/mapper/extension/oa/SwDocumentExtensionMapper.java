package com.foxconn.sw.data.mapper.extension.oa;

import com.foxconn.sw.data.dto.request.document.SearchDocParams;
import com.foxconn.sw.data.entity.SwDocument;
import com.foxconn.sw.data.mapper.auto.SwDocumentMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SwDocumentExtensionMapper extends SwDocumentMapper {


    @Select({"<script>",
            "select * ",
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
            "<if test='params.publisher!=null and params.publisher!=\"\"' >",
            " and creator =#{params.publisher,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.author!=null and params.author!=\"\"' >",
            " and author like CONCAT('%', #{params.author,jdbcType=VARCHAR}, '%') ",
            "</if> ",

            "<if test='params.project!=null and params.project!=\"\"' >",
            " and project =#{params.project,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.phase!=null and params.phase!=\"\"' >",
            " and phase =#{params.phase,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.config!=null and params.config!=\"\"' >",
            " and config like CONCAT('%', #{params.config,jdbcType=VARCHAR}, '%') ",
            "</if> ",

            "<if test='params.mainPart!=null and params.mainPart!=\"\"' >",
            " and main_part =#{params.mainPart,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.supplier!=null and params.supplier!=\"\"' >",
            " and supplier =#{params.supplier,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.issueMode!=null and params.issueMode!=\"\"' >",
            " and issue_mode =#{params.issueMode,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.process!=null and params.process!=\"\"' >",
            " and process =#{params.process,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.stage!=null and params.stage!=\"\"' >",
            " and stage like CONCAT('%', #{params.stage,jdbcType=VARCHAR}, '%') ",
            "</if> ",

            "<if test='params.level!=null and params.level!=\"\"' >",
            " and secret_level =#{params.level,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.canDownload!=null and params.canDownload!=\"\"' >",
            " and disable_down =#{params.canDownload,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.expireDate!=null and params.expireDate!=\"\"' >",
            " and expire_date =#{params.expireDate,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='employeeNo!=null and employeeNo!=\"\"' >",
            " and creator =#{employeeNo,jdbcType=VARCHAR} and file_type=1",
            "</if> ",
            "<if test='employeeNo==null or employeeNo==\"\"' >",
            " and file_type=0",
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
            @Result(column = "secret_level", property = "secretLevel", jdbcType = JdbcType.INTEGER),
            @Result(column = "author", property = "author", jdbcType = JdbcType.VARCHAR),
            @Result(column = "disable_down", property = "disableDown", jdbcType = JdbcType.INTEGER),
            @Result(column = "resource_id", property = "resourceId", jdbcType = JdbcType.INTEGER),
            @Result(column = "source", property = "source", jdbcType = JdbcType.VARCHAR),
            @Result(column = "creator", property = "creator", jdbcType = JdbcType.VARCHAR),
            @Result(column = "file_type", property = "fileType", jdbcType = JdbcType.INTEGER),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "work_type", property = "workType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "main_type", property = "mainType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "sub_type", property = "subType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "main_part", property = "mainPart", jdbcType = JdbcType.VARCHAR),
            @Result(column = "supplier", property = "supplier", jdbcType = JdbcType.VARCHAR),
            @Result(column = "expire_date", property = "expireDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "project", property = "project", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phase", property = "phase", jdbcType = JdbcType.VARCHAR),
            @Result(column = "config", property = "config", jdbcType = JdbcType.VARCHAR),
            @Result(column = "issue_mode", property = "issueMode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "process", property = "process", jdbcType = JdbcType.VARCHAR),
            @Result(column = "stage", property = "stage", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_delete", property = "isDelete", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "datetime_lastchange", property = "datetimeLastchange", jdbcType = JdbcType.TIMESTAMP)
    })
    List<SwDocument> queryDocumentListPages(SearchDocParams params, Integer start, Integer pageSize, String employeeNo);

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
            "<if test='params.publisher!=null and params.publisher!=\"\"' >",
            " and creator =#{params.publisher,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.author!=null and params.author!=\"\"' >",
            " and author like CONCAT('%', #{params.author,jdbcType=VARCHAR}, '%') ",
            "</if> ",

            "<if test='params.project!=null and params.project!=\"\"' >",
            " and project =#{params.project,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.phase!=null and params.phase!=\"\"' >",
            " and phase =#{params.phase,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.config!=null and params.config!=\"\"' >",
            " and config like CONCAT('%', #{params.config,jdbcType=VARCHAR}, '%') ",
            "</if> ",

            "<if test='params.mainPart!=null and params.mainPart!=\"\"' >",
            " and main_part =#{params.mainPart,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.supplier!=null and params.supplier!=\"\"' >",
            " and supplier =#{params.supplier,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.issueMode!=null and params.issueMode!=\"\"' >",
            " and issue_mode =#{params.issueMode,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.process!=null and params.process!=\"\"' >",
            " and process =#{params.process,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.stage!=null and params.stage!=\"\"' >",
            " and stage like CONCAT('%', #{params.stage,jdbcType=VARCHAR}, '%') ",
            "</if> ",

            "<if test='params.level!=null and params.level!=\"\"' >",
            " and secret_level =#{params.level,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.canDownload!=null and params.canDownload!=\"\"' >",
            " and disable_down =#{params.canDownload,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='params.expireDate!=null and params.expireDate!=\"\"' >",
            " and expire_date =#{params.expireDate,jdbcType=VARCHAR} ",
            "</if> ",
            "<if test='employeeNo!=null and employeeNo!=\"\"' >",
            " and creator =#{employeeNo,jdbcType=VARCHAR} and file_type=1",
            "</if> ",
            "<if test='employeeNo==null or employeeNo==\"\"' >",
            " and file_type=0",
            "</if> ",
            "</where>",
            "</script>"
    })
    Long getTotalCountByParams(@Param("params") SearchDocParams params, @Param("employeeNo") String employeeNo);

    @Update({"<script>",
            "update sw_document ",
            "set is_delete = 1 ",
            "where id in ",
            "<foreach collection='documentIDs' item='id' open='(' separator=',' close=')'>",
            "#{id,jdbcType=INTEGER}",
            "</foreach>",
            "</script>"
    })
    int deleteByDocumentIDs(List<Integer> documentIDs);
}
