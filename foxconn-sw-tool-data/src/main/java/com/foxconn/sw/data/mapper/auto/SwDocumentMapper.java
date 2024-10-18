package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwDocument;
import com.foxconn.sw.data.entity.SwDocumentExample;
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
public interface SwDocumentMapper {
    @SelectProvider(type=SwDocumentSqlProvider.class, method="countByExample")
    long countByExample(SwDocumentExample example);

    @DeleteProvider(type=SwDocumentSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwDocumentExample example);

    @Delete({
        "delete from sw_document",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_document (document_name, file_version, ",
        "category, department, ",
        "project, secret_level, ",
        "expire_date, disable_down, ",
        "resource_id, creator, ",
        "is_delete, create_time, ",
        "datetime_lastchange)",
        "values (#{documentName,jdbcType=VARCHAR}, #{fileVersion,jdbcType=VARCHAR}, ",
        "#{category,jdbcType=VARCHAR}, #{department,jdbcType=INTEGER}, ",
        "#{project,jdbcType=VARCHAR}, #{secretLevel,jdbcType=INTEGER}, ",
        "#{expireDate,jdbcType=VARCHAR}, #{disableDown,jdbcType=INTEGER}, ",
        "#{resourceId,jdbcType=INTEGER}, #{creator,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwDocument record);

    @InsertProvider(type=SwDocumentSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwDocument record);

    @SelectProvider(type=SwDocumentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="document_name", property="documentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_version", property="fileVersion", jdbcType=JdbcType.VARCHAR),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="department", property="department", jdbcType=JdbcType.INTEGER),
        @Result(column="project", property="project", jdbcType=JdbcType.VARCHAR),
        @Result(column="secret_level", property="secretLevel", jdbcType=JdbcType.INTEGER),
        @Result(column="expire_date", property="expireDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="disable_down", property="disableDown", jdbcType=JdbcType.INTEGER),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.INTEGER),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwDocument> selectByExampleWithRowbounds(SwDocumentExample example, RowBounds rowBounds);

    @SelectProvider(type=SwDocumentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="document_name", property="documentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_version", property="fileVersion", jdbcType=JdbcType.VARCHAR),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="department", property="department", jdbcType=JdbcType.INTEGER),
        @Result(column="project", property="project", jdbcType=JdbcType.VARCHAR),
        @Result(column="secret_level", property="secretLevel", jdbcType=JdbcType.INTEGER),
        @Result(column="expire_date", property="expireDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="disable_down", property="disableDown", jdbcType=JdbcType.INTEGER),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.INTEGER),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwDocument> selectByExample(SwDocumentExample example);

    @Select({
        "select",
        "id, document_name, file_version, category, department, project, secret_level, ",
        "expire_date, disable_down, resource_id, creator, is_delete, create_time, datetime_lastchange",
        "from sw_document",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="document_name", property="documentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="file_version", property="fileVersion", jdbcType=JdbcType.VARCHAR),
        @Result(column="category", property="category", jdbcType=JdbcType.VARCHAR),
        @Result(column="department", property="department", jdbcType=JdbcType.INTEGER),
        @Result(column="project", property="project", jdbcType=JdbcType.VARCHAR),
        @Result(column="secret_level", property="secretLevel", jdbcType=JdbcType.INTEGER),
        @Result(column="expire_date", property="expireDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="disable_down", property="disableDown", jdbcType=JdbcType.INTEGER),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.INTEGER),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_delete", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwDocument selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwDocumentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwDocument record, @Param("example") SwDocumentExample example);

    @UpdateProvider(type=SwDocumentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwDocument record, @Param("example") SwDocumentExample example);

    @UpdateProvider(type=SwDocumentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwDocument record);

    @Update({
        "update sw_document",
        "set document_name = #{documentName,jdbcType=VARCHAR},",
          "file_version = #{fileVersion,jdbcType=VARCHAR},",
          "category = #{category,jdbcType=VARCHAR},",
          "department = #{department,jdbcType=INTEGER},",
          "project = #{project,jdbcType=VARCHAR},",
          "secret_level = #{secretLevel,jdbcType=INTEGER},",
          "expire_date = #{expireDate,jdbcType=VARCHAR},",
          "disable_down = #{disableDown,jdbcType=INTEGER},",
          "resource_id = #{resourceId,jdbcType=INTEGER},",
          "creator = #{creator,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwDocument record);
}