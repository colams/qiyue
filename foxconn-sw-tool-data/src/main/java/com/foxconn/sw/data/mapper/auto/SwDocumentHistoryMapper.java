package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwDocumentHistory;
import com.foxconn.sw.data.entity.SwDocumentHistoryExample;
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
public interface SwDocumentHistoryMapper {
    @SelectProvider(type=SwDocumentHistorySqlProvider.class, method="countByExample")
    long countByExample(SwDocumentHistoryExample example);

    @DeleteProvider(type=SwDocumentHistorySqlProvider.class, method="deleteByExample")
    int deleteByExample(SwDocumentHistoryExample example);

    @Delete({
        "delete from sw_document_history",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sw_document_history (document_id, creator, ",
        "document_name, create_time, ",
        "datetime_lastchange)",
        "values (#{documentId,jdbcType=INTEGER}, #{creator,jdbcType=VARCHAR}, ",
        "#{documentName,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(SwDocumentHistory record);

    @InsertProvider(type=SwDocumentHistorySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(SwDocumentHistory record);

    @SelectProvider(type=SwDocumentHistorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="document_id", property="documentId", jdbcType=JdbcType.INTEGER),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="document_name", property="documentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwDocumentHistory> selectByExampleWithRowbounds(SwDocumentHistoryExample example, RowBounds rowBounds);

    @SelectProvider(type=SwDocumentHistorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="document_id", property="documentId", jdbcType=JdbcType.INTEGER),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="document_name", property="documentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwDocumentHistory> selectByExample(SwDocumentHistoryExample example);

    @Select({
        "select",
        "id, document_id, creator, document_name, create_time, datetime_lastchange",
        "from sw_document_history",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="document_id", property="documentId", jdbcType=JdbcType.INTEGER),
        @Result(column="creator", property="creator", jdbcType=JdbcType.VARCHAR),
        @Result(column="document_name", property="documentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwDocumentHistory selectByPrimaryKey(Integer id);

    @UpdateProvider(type=SwDocumentHistorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwDocumentHistory record, @Param("example") SwDocumentHistoryExample example);

    @UpdateProvider(type=SwDocumentHistorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwDocumentHistory record, @Param("example") SwDocumentHistoryExample example);

    @UpdateProvider(type=SwDocumentHistorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwDocumentHistory record);

    @Update({
        "update sw_document_history",
        "set document_id = #{documentId,jdbcType=INTEGER},",
          "creator = #{creator,jdbcType=VARCHAR},",
          "document_name = #{documentName,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SwDocumentHistory record);
}