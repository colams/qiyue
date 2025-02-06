package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCollaborationDetailLog;
import com.foxconn.sw.data.entity.SwCollaborationDetailLogExample;
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
public interface SwCollaborationDetailLogMapper {
    @DeleteProvider(type=SwCollaborationDetailLogSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwCollaborationDetailLogExample example);

    @Delete({
        "delete from sw_collaboration_detail_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sw_collaboration_detail_log (detail_id, row_index, ",
        "col_index, operator, ",
        "remark, create_time, ",
        "datetime_lastchange)",
        "values (#{detailId,jdbcType=BIGINT}, #{rowIndex,jdbcType=INTEGER}, ",
        "#{colIndex,jdbcType=INTEGER}, #{operator,jdbcType=VARCHAR}, ",
        "#{remark,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SwCollaborationDetailLog record);

    @InsertProvider(type=SwCollaborationDetailLogSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SwCollaborationDetailLog record);

    @SelectProvider(type=SwCollaborationDetailLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="detail_id", property="detailId", jdbcType=JdbcType.BIGINT),
        @Result(column="row_index", property="rowIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="col_index", property="colIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCollaborationDetailLog> selectByExampleWithRowbounds(SwCollaborationDetailLogExample example, RowBounds rowBounds);

    @SelectProvider(type=SwCollaborationDetailLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="detail_id", property="detailId", jdbcType=JdbcType.BIGINT),
        @Result(column="row_index", property="rowIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="col_index", property="colIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SwCollaborationDetailLog> selectByExample(SwCollaborationDetailLogExample example);

    @Select({
        "select",
        "id, detail_id, row_index, col_index, operator, remark, create_time, datetime_lastchange",
        "from sw_collaboration_detail_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="detail_id", property="detailId", jdbcType=JdbcType.BIGINT),
        @Result(column="row_index", property="rowIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="col_index", property="colIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP)
    })
    SwCollaborationDetailLog selectByPrimaryKey(Long id);

    @UpdateProvider(type=SwCollaborationDetailLogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwCollaborationDetailLog record, @Param("example") SwCollaborationDetailLogExample example);

    @UpdateProvider(type=SwCollaborationDetailLogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwCollaborationDetailLog record, @Param("example") SwCollaborationDetailLogExample example);

    @UpdateProvider(type=SwCollaborationDetailLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwCollaborationDetailLog record);

    @Update({
        "update sw_collaboration_detail_log",
        "set detail_id = #{detailId,jdbcType=BIGINT},",
          "row_index = #{rowIndex,jdbcType=INTEGER},",
          "col_index = #{colIndex,jdbcType=INTEGER},",
          "operator = #{operator,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SwCollaborationDetailLog record);
}