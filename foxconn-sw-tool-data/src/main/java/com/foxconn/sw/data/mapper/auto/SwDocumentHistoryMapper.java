package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwDocumentHistory;
import com.foxconn.sw.data.entity.SwDocumentHistoryExample;
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
public interface SwDocumentHistoryMapper {
    int deleteByExample(SwDocumentHistoryExample example);

    @Delete({
        "delete from sw_document_history",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_document_history (document_id, document_name, ",
        "resource_id, create_time, ",
        "datetime_lastchange, creator)",
        "values (#{documentId,jdbcType=INTEGER}, #{documentName,jdbcType=VARCHAR}, ",
        "#{resourceId,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP}, #{creator,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwDocumentHistory record);

    int insertSelective(SwDocumentHistory record);

    List<SwDocumentHistory> selectByExampleWithRowbounds(SwDocumentHistoryExample example, RowBounds rowBounds);

    List<SwDocumentHistory> selectByExample(SwDocumentHistoryExample example);

    @Select({
        "select",
        "id, document_id, document_name, resource_id, create_time, datetime_lastchange, ",
        "creator",
        "from sw_document_history",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("com.foxconn.sw.data.mapper.auto.SwDocumentHistoryMapper.BaseResultMap")
    SwDocumentHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwDocumentHistory record, @Param("example") SwDocumentHistoryExample example);

    int updateByExample(@Param("record") SwDocumentHistory record, @Param("example") SwDocumentHistoryExample example);

    int updateByPrimaryKeySelective(SwDocumentHistory record);

    @Update({
        "update sw_document_history",
        "set document_id = #{documentId,jdbcType=INTEGER},",
          "document_name = #{documentName,jdbcType=VARCHAR},",
          "resource_id = #{resourceId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP},",
          "creator = #{creator,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwDocumentHistory record);
}