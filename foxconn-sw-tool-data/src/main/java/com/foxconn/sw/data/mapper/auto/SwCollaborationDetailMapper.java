package com.foxconn.sw.data.mapper.auto;

import com.foxconn.sw.data.entity.SwCollaborationDetail;
import com.foxconn.sw.data.entity.SwCollaborationDetailExample;
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
public interface SwCollaborationDetailMapper {
    @DeleteProvider(type=SwCollaborationDetailSqlProvider.class, method="deleteByExample")
    int deleteByExample(SwCollaborationDetailExample example);

    @Delete({
        "delete from sw_collaboration_detail",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sw_collaboration_detail (scu_id, row_index, ",
        "col_index, item, ",
        "item_value, create_time, ",
        "datetime_lastchange, spare_value)",
        "values (#{scuId,jdbcType=BIGINT}, #{rowIndex,jdbcType=INTEGER}, ",
        "#{colIndex,jdbcType=INTEGER}, #{item,jdbcType=VARCHAR}, ",
        "#{itemValue,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{datetimeLastchange,jdbcType=TIMESTAMP}, #{spareValue,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(SwCollaborationDetail record);

    @InsertProvider(type=SwCollaborationDetailSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(SwCollaborationDetail record);

    @SelectProvider(type=SwCollaborationDetailSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="scu_id", property="scuId", jdbcType=JdbcType.BIGINT),
        @Result(column="row_index", property="rowIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="col_index", property="colIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_value", property="itemValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="spare_value", property="spareValue", jdbcType=JdbcType.VARCHAR)
    })
    List<SwCollaborationDetail> selectByExampleWithRowbounds(SwCollaborationDetailExample example, RowBounds rowBounds);

    @SelectProvider(type=SwCollaborationDetailSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="scu_id", property="scuId", jdbcType=JdbcType.BIGINT),
        @Result(column="row_index", property="rowIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="col_index", property="colIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_value", property="itemValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="spare_value", property="spareValue", jdbcType=JdbcType.VARCHAR)
    })
    List<SwCollaborationDetail> selectByExample(SwCollaborationDetailExample example);

    @Select({
        "select",
        "id, scu_id, row_index, col_index, item, item_value, create_time, datetime_lastchange, ",
        "spare_value",
        "from sw_collaboration_detail",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="scu_id", property="scuId", jdbcType=JdbcType.BIGINT),
        @Result(column="row_index", property="rowIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="col_index", property="colIndex", jdbcType=JdbcType.INTEGER),
        @Result(column="item", property="item", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_value", property="itemValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="datetime_lastchange", property="datetimeLastchange", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="spare_value", property="spareValue", jdbcType=JdbcType.VARCHAR)
    })
    SwCollaborationDetail selectByPrimaryKey(Long id);

    @UpdateProvider(type=SwCollaborationDetailSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SwCollaborationDetail record, @Param("example") SwCollaborationDetailExample example);

    @UpdateProvider(type=SwCollaborationDetailSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SwCollaborationDetail record, @Param("example") SwCollaborationDetailExample example);

    @UpdateProvider(type=SwCollaborationDetailSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SwCollaborationDetail record);

    @Update({
        "update sw_collaboration_detail",
        "set scu_id = #{scuId,jdbcType=BIGINT},",
          "row_index = #{rowIndex,jdbcType=INTEGER},",
          "col_index = #{colIndex,jdbcType=INTEGER},",
          "item = #{item,jdbcType=VARCHAR},",
          "item_value = #{itemValue,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "datetime_lastchange = #{datetimeLastchange,jdbcType=TIMESTAMP},",
          "spare_value = #{spareValue,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SwCollaborationDetail record);
}