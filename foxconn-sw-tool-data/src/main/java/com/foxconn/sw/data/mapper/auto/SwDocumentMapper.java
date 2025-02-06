package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwDocument;
import com.foxconn.sw.data.entity.SwDocumentExample;
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
public interface SwDocumentMapper {
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
        "resource_id, author, ",
        "creator, file_type, ",
        "content, work_type, ",
        "main_type, sub_type, ",
        "main_part, supplier, ",
        "source, dead_line, ",
        "is_delete, create_time, ",
        "datetime_lastchange)",
        "values (#{documentName,jdbcType=VARCHAR}, #{fileVersion,jdbcType=VARCHAR}, ",
        "#{category,jdbcType=VARCHAR}, #{department,jdbcType=INTEGER}, ",
        "#{project,jdbcType=VARCHAR}, #{secretLevel,jdbcType=INTEGER}, ",
        "#{expireDate,jdbcType=VARCHAR}, #{disableDown,jdbcType=INTEGER}, ",
        "#{resourceId,jdbcType=INTEGER}, #{author,jdbcType=VARCHAR}, ",
        "#{creator,jdbcType=VARCHAR}, #{fileType,jdbcType=INTEGER}, ",
        "#{content,jdbcType=VARCHAR}, #{workType,jdbcType=VARCHAR}, ",
        "#{mainType,jdbcType=VARCHAR}, #{subType,jdbcType=VARCHAR}, ",
        "#{mainPart,jdbcType=VARCHAR}, #{supplier,jdbcType=VARCHAR}, ",
        "#{source,jdbcType=VARCHAR}, #{deadLine,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwDocument record);

    int insertSelective(SwDocument record);

    List<SwDocument> selectByExampleWithRowbounds(SwDocumentExample example, RowBounds rowBounds);

    List<SwDocument> selectByExample(SwDocumentExample example);

    @Select({
        "select",
        "id, document_name, file_version, category, department, project, secret_level, ",
        "expire_date, disable_down, resource_id, author, creator, file_type, content, ",
        "work_type, main_type, sub_type, main_part, supplier, source, dead_line, is_delete, ",
        "create_time, datetime_lastchange",
        "from sw_document",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwDocumentMapper.BaseResultMap")
    SwDocument selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwDocument record, @Param("example") SwDocumentExample example);

    int updateByExample(@Param("record") SwDocument record, @Param("example") SwDocumentExample example);

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
          "author = #{author,jdbcType=VARCHAR},",
          "creator = #{creator,jdbcType=VARCHAR},",
          "file_type = #{fileType,jdbcType=INTEGER},",
          "content = #{content,jdbcType=VARCHAR},",
          "work_type = #{workType,jdbcType=VARCHAR},",
          "main_type = #{mainType,jdbcType=VARCHAR},",
          "sub_type = #{subType,jdbcType=VARCHAR},",
          "main_part = #{mainPart,jdbcType=VARCHAR},",
          "supplier = #{supplier,jdbcType=VARCHAR},",
          "source = #{source,jdbcType=VARCHAR},",
          "dead_line = #{deadLine,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwDocument record);
}